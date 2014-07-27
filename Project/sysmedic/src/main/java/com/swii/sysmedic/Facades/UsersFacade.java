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
import javax.transaction.Transactional;

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
    
    @Transactional
    public Users UpdateWithConstraints(Users user, String selectedRol, int selectedEspecialidad,  MedicoFacade medicFacade){
        user.setRol(selectedRol);
            user.setEnabled((short)1);
            Users originalUser = this.GetUser(user.getNickname());
            originalUser.set(user);
            //System.out.println("USER: "+originalUser.toString());
            this.edit(originalUser);
            
            Medico medico = medicFacade.find(user.getId());
            if(medico != null && !user.getRol().equals("m")){
                medicFacade.remove(medico); 
            }
            if(user.getRol().equals("m")){
                if(medico == null){
                    medico = new Medico();
                    medico.setId(user.getId());
                    medico.setEspecialidad(new Especialidad(selectedEspecialidad));
                    medico.setUsers(user);
                    medicFacade.create(medico);
                }else{
                    medico.setEspecialidad(new Especialidad(selectedEspecialidad));
                    medicFacade.edit(medico);
                }
            }       
            return originalUser;
    }
    
    public Users GetUser(String nickName) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByNickname", Users.class);
        query.setParameter("nickname", nickName.toLowerCase());
        List users = query.getResultList();
        if(users == null || users.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public List<Users> findAll() {
        List<Users> contacts = em.createNamedQuery("Users.findAll", Users.class).getResultList();
        return contacts;
    }
    
}
