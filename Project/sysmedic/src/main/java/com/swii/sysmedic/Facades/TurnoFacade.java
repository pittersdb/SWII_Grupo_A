/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Medico;
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
    @EJB
    private ConsultaFacade consultaFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TurnoFacade() {
        super(Turno.class);
    }
    
    public List<Turno> GetAllTurnosByMedico(Medico medico){
        TypedQuery<Turno> query = em.createNamedQuery("Turno.findByMedico", Turno.class);     
        query.setParameter("medico", medico);
        List<Turno> result = query.getResultList();
        for(Turno turn : result){
            turn.getCita().setConsulta(this.consultaFacade.GetByCita(turn.getCita()));
        }
        return result;
    }
    
    public Turno Assign(Cita cita){
          TypedQuery<Integer> query = em.createNamedQuery("Turno.findLastOrden", Integer.class);     
          query.setParameter("medico", cita.getMedico());
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
          turno.getCita().setConsulta(this.consultaFacade.GetByCita(turno.getCita()));
          create(turno);
          cita.setEstado(Cita.Estado.Esperando.toString());
          citaFacade.edit(cita);
          return turno; 
    }
    
    public void CancelTurno(Turno turno, boolean cancelCita){
         Cita cita = turno.getCita();
        if(cancelCita){           
            cita.setEstado(Cita.Estado.Cancelado.toString());                        
        }else{
            cita.setEstado(Cita.Estado.Pendiente.toString());          
        }
        citaFacade.edit(cita);
        cita.setConsulta(this.consultaFacade.GetByCita(turno.getCita()));
       this.remove(turno);   
    }
    
    public Turno FinishCitaAndNextTurno(Turno currentTurno, List<Turno> turnos){
        Cita currentCita = currentTurno.getCita();
        currentCita.setEstado(Cita.Estado.Terminado.toString());
        this.citaFacade.edit(currentCita);
        
        this.remove(currentTurno);
        turnos.remove(currentTurno);
        
        if(!turnos.isEmpty())
            return turnos.get(0);
        else
            return null;        
    }
    
//    public void Posporner(List<Turno> turnos){
//        if(turnos.isEmpty() || turnos.size() == 1) return;    
//        pospuestosCounter++;        
//        if(pospuestosCounter == turnos.size())
//            pospuestosCounter= 1;
//        Turno turnoToPosposed = turnos.get(0);        
//        turnos.set(0, turnos.get(pospuestosCounter));        
//        turnos.set(pospuestosCounter, turnoToPosposed);       
//        
//        turnos.get(0).setRealOrden(0);
//        turnos.get(pospuestosCounter).setRealOrden(pospuestosCounter);
//        turnos.get(pospuestosCounter).setEstado(Turno.Estado.Postergado.toString());
//        
//        this.edit(turnos.get(0));
//        this.edit(turnos.get(pospuestosCounter));
//    }
    
}
