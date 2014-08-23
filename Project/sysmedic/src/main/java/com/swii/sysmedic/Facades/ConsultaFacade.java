/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.Turno;
import javax.ejb.EJB;
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

    @EJB
    private TurnoFacade turnoFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaFacade() {
        super(Consulta.class);
    }
    
    public void SaveMediciones(Turno turno){
        Consulta consulta = turno.getCita().getConsulta();
        consulta.setCita(turno.getCita());
        if(!consulta.getCita().getEstado().equals(Cita.Estado.Terminado.toString())){
            if(consulta.getId() != null){
                this.edit(consulta);
                return; 
            }
        }
       this.create(consulta);
    }
    
    public void SaveConsulta(Consulta consulta){
        this.edit(consulta);
    }
    
    public Consulta GetByCita(Cita cita){
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.findByCita", Consulta.class);     
        query.setParameter("cita", cita);
        try{
            return query.getSingleResult();    
        }catch(javax.persistence.NoResultException e){
            return new Consulta();
        }
    }
    
}
