/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author fabian
 */
@Stateless
public class MedicoFacade extends AbstractFacade<Medico> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicoFacade() {
        super(Medico.class);
    }
    
    public List<Medico> GetMedicoByName(String name, String apellidos) {
        TypedQuery<Medico> query = em.createNamedQuery("Medico.findByName", Medico.class);
        query.setParameter("name", "%"+name.toLowerCase()+"%");
        query.setParameter("apellidos", "%"+apellidos.toLowerCase()+"%");
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList();
    }
    
    public Medico GetMedicoByUser(Users user) {
        TypedQuery<Medico> query = em.createNamedQuery("Medico.findByUser", Medico.class);
        query.setParameter("user", user);
        List<Medico> matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return matches.get(0);
    }
    
}
