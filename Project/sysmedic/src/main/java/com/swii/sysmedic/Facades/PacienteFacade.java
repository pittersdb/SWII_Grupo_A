/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Paciente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author fabian
 */
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }
    
    @Transactional
    public Paciente UpdateWithConstraints(Paciente paciente){
        Paciente originalPaciente = paciente;
        
        return originalPaciente;
    }
    
    public Paciente GetPacienteByCi(String ci) {
        TypedQuery<Paciente> query = em.createNamedQuery("Paciente.findByCi", Paciente.class);
        query.setParameter("ci", ci.toLowerCase());
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
      
    @Override
    public List<Paciente> findAll() {
        List<Paciente> contacts = em.createNamedQuery("Paciente.findAll", Paciente.class).getResultList();
        return contacts;
    }
}
