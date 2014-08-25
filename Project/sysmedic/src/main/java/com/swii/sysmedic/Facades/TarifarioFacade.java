/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Tarifario;
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
public class TarifarioFacade extends AbstractFacade<Tarifario> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarifarioFacade() {
        super(Tarifario.class);
    }
    
    public Tarifario GetTarifarioByEspecialidad(String nombreServicio){
        TypedQuery<Tarifario> query = em.createNamedQuery("Consulta.findByNombreServicio", Tarifario.class);
        
//        paciente = paciente!=null ? paciente:new Paciente();
//        boolean nullPaciente = paciente.getId()==null;
//        query.setParameter("nullPaciente", nullPaciente);
        query.setParameter("nombreServicio", nombreServicio);
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
}
