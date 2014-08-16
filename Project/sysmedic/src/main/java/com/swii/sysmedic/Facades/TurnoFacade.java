/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Turno;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
public class TurnoFacade extends AbstractFacade<Turno> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private CitaFacade citaFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TurnoFacade() {
        super(Turno.class);
    }
    
    public Turno Assign(Cita cita){
          TypedQuery<Integer> query = em.createNamedQuery("Turno.findLastOrden", Integer.class);          
          Integer maxOrder = query.getSingleResult();        
          Turno turno = new Turno();
          turno.setCita(cita);
          if(maxOrder != null){
              turno.setOrden(maxOrder + 1);
               System.out.println("MAX ORDER "+ maxOrder); 
          }else{
              turno.setOrden(1);
              System.out.println("MAX ORDER NULL"); 
          }
          create(turno);
          cita.setEstado(Cita.Estado.Esperando.toString());
          citaFacade.edit(cita);
          return turno; 
    }
    
    public void CancelTurno(Turno turno, boolean cancelCita){
        if(cancelCita){
            Cita cita = turno.getCita();
            cita.setEstado(Cita.Estado.Cancelado.toString());            
            citaFacade.edit(cita);
        }
       this.remove(turno);   
    }
    
}
