/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Users;
import java.util.List;
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
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
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
