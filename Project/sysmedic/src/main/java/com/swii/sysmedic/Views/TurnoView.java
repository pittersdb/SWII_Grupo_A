/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.TurnoFacade;
import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Turno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author LUCAS
 */
public class TurnoView {

    
    @EJB
    private TurnoFacade turnoFacade;    
    private Turno turno = new Turno();
    private List<Turno> resultSet = new ArrayList<Turno>();
    private Turno current;
     
    
    /**
     * Creates a new instance of TurnoView
     */
    public TurnoView() {
         
    }
    
    @PostConstruct
    public void init() {
        if(this.resultSet.isEmpty())
            this.resultSet.addAll(this.turnoFacade.findAll());
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public List<Turno> getResultSet() {
        if(this.resultSet.isEmpty())
            this.resultSet.addAll(this.turnoFacade.findAll());
        return resultSet;
    }

    public void setResultSet(List<Turno> resultSet) {
        this.resultSet = resultSet;
    }

    public Turno getCurrent() {
        if(current == null){
            if(this.resultSet != null ){
                if(!this.resultSet.isEmpty()){
                    current = this.resultSet.get(0);
                }
            }
         }
        return current;
    }

    public void setCurrent(Turno current) {
        this.current = current;
    }
  
    public void Assign(Cita cita){
          try{
            Turno turnoToAssign = this.turnoFacade.Assign(cita);
            this.resultSet.add(turnoToAssign);
            if( !this.resultSet.isEmpty())
                this.setCurrent(this.resultSet.get(0));
            else
                this.setCurrent(null);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void CancelCita(Turno turn){
          try{
            this.turnoFacade.CancelTurno(turn,true);
            this.resultSet.remove(turn);
            if( !this.resultSet.isEmpty())
                this.setCurrent(this.resultSet.get(0));
            else
                this.setCurrent(null);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void FinishAndNext(){
          try{
              Turno nextTurno = this.turnoFacade.FinishCitaAndNextTurno(current, resultSet);
              this.setCurrent(nextTurno);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
