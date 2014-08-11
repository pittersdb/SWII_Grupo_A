/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Paciente;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author fabian
 */
@Stateless
public class CitaFacade extends AbstractFacade<Cita> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }
    
    public List<Cita> GeneralSearching(Paciente paciente, Medico medico, Date fechaInf, Date fechaSup, String estado) {
        TypedQuery<Cita> query = em.createNamedQuery("Cita.generalFinder", Cita.class);
        
    
        paciente = paciente != null ? paciente : new Paciente();
        medico = medico != null ? medico : new Medico();
                
        boolean nullPaciente = paciente.getId() == null;
        boolean nullMedico = medico.getId() == null;
        
        query.setParameter("nullPaciente", nullPaciente );
        query.setParameter("nullMedico", nullMedico );
        query.setParameter("paciente", paciente );
        query.setParameter("medico", medico);
        query.setParameter("fechaInf", fechaInf, TemporalType.DATE);
        query.setParameter("fechaSup", fechaSup, TemporalType.DATE);
        query.setParameter("estado", estado); 
        
        return query.getResultList();
    }
       
    
}
