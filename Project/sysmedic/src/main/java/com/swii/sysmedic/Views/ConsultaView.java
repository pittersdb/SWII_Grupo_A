/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.ConsultaFacade;
import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.Turno;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

/**
 *
 * @author LUCAS
 */
public class ConsultaView {

    @EJB
    private ConsultaFacade consultaFacade;
    private Consulta consulta = new Consulta();
    
    /**
     * Creates a new instance of ConsultaView
     */
    public ConsultaView() {
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    
    public void SaveMediciones(Turno turno){
        try{
            this.consultaFacade.SaveMediciones(turno);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
     public void SaveConsulta(){
        try{
            this.consultaFacade.SaveConsulta(this.getConsulta());
            this.setConsulta(new Consulta());
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void StartConsulta(Turno turno){
        if(!isReadyToStart(turno)){
                 FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "Debe primero ingresar las MEDICIONES para poder iniciar la consulta"));
        }
    }
    
    
    public boolean isReadyToStart(Turno turno){
        return  turno != null && turno.getCita().getConsulta() != null  && turno.getCita().getConsulta().getId() != null;
    }
    
    public void onToggle(ToggleEvent event) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
//        FacesContext.getCurrentInstance().addMessage(null, message);
        this.isToggled = event.getVisibility() == Visibility.VISIBLE;
    }
    
    private boolean isToggled;
    
    public boolean isToggled(){
        return isToggled;
    }
}
