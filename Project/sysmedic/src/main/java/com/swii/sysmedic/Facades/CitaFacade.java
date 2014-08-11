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
        
        Integer idPaciente = paciente != null ? paciente.getId(): null;
        Integer idMedico = medico != null ?  medico.getId(): null;
        
        query.setParameter("idPaciente", idPaciente );
        query.setParameter("idMedico", idMedico);
        query.setParameter("fechaInf", fechaInf, TemporalType.DATE);
        query.setParameter("fechaSup", fechaSup, TemporalType.DATE);
        query.setParameter("estado", estado);
        
        return query.getResultList();
    }
       
    
}
