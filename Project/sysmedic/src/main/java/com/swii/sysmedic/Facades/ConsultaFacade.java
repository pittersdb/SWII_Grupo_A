/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.Paciente;
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
public class ConsultaFacade extends AbstractFacade<Consulta> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaFacade() {
        super(Consulta.class);
    }
    
    public Consulta GetConsultaByIdPaciente(Paciente paciente) {
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.findPacienteById", Consulta.class);
        
//        paciente = paciente!=null ? paciente:new Paciente();
//        boolean nullPaciente = paciente.getId()==null;
//        query.setParameter("nullPaciente", nullPaciente);
        query.setParameter("paciente", paciente);
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public Consulta GetConsultaById(int id) {
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.findById", Consulta.class);
        
//        paciente = paciente!=null ? paciente:new Paciente();
//        boolean nullPaciente = paciente.getId()==null;
//        query.setParameter("nullPaciente", nullPaciente);
        query.setParameter("id", id);
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
}
