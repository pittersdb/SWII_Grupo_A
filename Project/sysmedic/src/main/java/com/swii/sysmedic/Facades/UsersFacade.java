/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Especialidad;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Users;


import java.security.MessageDigest; 
import java.util.List;
import static java.util.Objects.hash;
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
        else{
            Users userTarget = query.getResultList().get(0);
            if(userTarget.getMedico() == null && userTarget.getRol().equals("m"))
                userTarget.setMedico(this.medicoFacade.GetMedicoByUser(userTarget));
                
            return query.getResultList().get(0);
        }
    }
    
    public Users LoadCompleteUser(String nickname){
        Users user = GetUser(nickname);
        if(user.getRol().equals("m")){
            user.setEspecialidad( this.medicoFacade.GetMedicoByUser(user).getEspecialidad().getId());
        }
        return user;
    }
    
    public boolean existsUser(String nickname){
        return GetUser(nickname) != null;
    }
    
    public void Save(Users user, String selectedRol, int selectedEspecialidad){
        user.setRol(selectedRol);
        user.setEnabled((short)1);
        //user.setPassword(encriptarMD5(user.getPassword()));
        create(user);
        
         if(user.getRol().equalsIgnoreCase("m")){
            Medico newMedico = new Medico();
            newMedico.setEspecialidad(new Especialidad(selectedEspecialidad));
            newMedico.setUsers(user);
            this.medicoFacade.create(newMedico);
         }
        
    }
    
    /** 
    * Encripta un String con el algoritmo MD5. 
    * @return El algoritmo encriptado 
    * @param palabra 
    */ 
    public String encriptarMD5(String palabra){ 
        String pe=""; 
        try { 
            pe = Integer.toString(hash(palabra)); 
        } 
        catch (Exception e) { 
            throw new Error("Error: Al encriptar el password"); 
        } 
        return pe; 
    }
    
    public Users UpdateWithConstraints(Users user, String selectedRol, int selectedEspecialidad){
        user.setRol(selectedRol);
        user.setEnabled((short)1);
        Users originalUser = this.GetUser(user.getNickname());
        originalUser.set(user);
        this.edit(originalUser);
        
        Medico medico = medicoFacade.GetMedicoByUser(user);
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
