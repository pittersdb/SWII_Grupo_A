/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Especialidad;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author LUCAS
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @EJB
    private MedicoFacade medicoFacade;
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UsersFacade() {
        super(Users.class);
        
    }
    
    //DATABASE custom operations
    
    public Users GetUser(String nickName) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByNickname", Users.class);
        query.setParameter("nickname", nickName.toLowerCase());
        List users = query.getResultList();
        if(users == null || users.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public Users LoadCompleteUser(String nickname){
        Users user = GetUser(nickname);
        if(user.getRol().equals("m")){
            user.setEspecialidad( this.medicoFacade.find(user.getId()).getEspecialidad().getId());
        }
        return user;
    }
    
    public boolean existsUser(String nickname){
        return GetUser(nickname) != null;
    }
    
    public void Save(Users user, String selectedRol){
        user.setRol(selectedRol);
        user.setEnabled((short)1);
        create(user);
        
    }
    
    public void SaveAsMedic(Users user, int selectedEspecialidad){
        if(user.getRol().equalsIgnoreCase("m")){
            try{
                Medico newMedico = new Medico();
                newMedico.setId(user.getId());
                newMedico.setEspecialidad(new Especialidad(selectedEspecialidad));
                newMedico.setUsers(user);
                this.medicoFacade.create(newMedico);
            }catch(Exception eMedic){
                remove(user);
            }
        }
    }
    
    public Users UpdateWithConstraints(Users user, String selectedRol, int selectedEspecialidad){
        user.setRol(selectedRol);
        user.setEnabled((short)1);
        Users originalUser = this.GetUser(user.getNickname());
        originalUser.set(user);
        this.edit(originalUser);
        
        Medico medico = medicoFacade.find(user.getId());
        if(medico != null && !user.getRol().equals("m")){
            medicoFacade.remove(medico);
        }
        if(user.getRol().equals("m")){
            if(medico == null){
                medico = new Medico();
                medico.setId(user.getId());
                medico.setEspecialidad(new Especialidad(selectedEspecialidad));
                medico.setUsers(user);
                medicoFacade.create(medico);
            }else{
                medico.setEspecialidad(new Especialidad(selectedEspecialidad));
                medicoFacade.edit(medico);
            }
        }
        return originalUser;
    }
    
    @Override
    public List<Users> findAll() {
        List<Users> contacts = em.createNamedQuery("Users.findAll", Users.class).getResultList();
        return contacts;
    }  
    
}
