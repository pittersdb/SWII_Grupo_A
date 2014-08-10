/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.CitaFacade;
import com.swii.sysmedic.entities.Cita;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author fabian
 */

public class CitaView {
    
    @EJB
    private CitaFacade citaFacade;
    
    private Cita cita = new Cita();
    private String test;
    
    /**
     * Creates a new instance of CitaView
     */
    public CitaView() {
        cita.setFechaGeneracion(Calendar.getInstance().getTime());
        cita.setFechaConsultaActual(Calendar.getInstance().getTime());
    }
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
    }
    
    
    
    public Cita getCita() {
        return cita;
    }
    
    public void setCita(Cita cita) {
        this.cita = cita;
    }
    
    
    
    
    public void Save(){
        //System.out.println("CITA HAS BEEN SAVED: " + PacienteView.getInstance().getPaciente().getId() + ", "+ MedicoView.getInstance());
        try{ 
            this.cita.setEstado(Cita.Estado.Pendiente.toString());
            this.cita.setMedico(MedicoView.getInstance().getMedico());
            this.cita.setPaciente(PacienteView.getInstance().getPaciente());
            this.cita.setUsers(UsersView.getLoggedUser());
            
            this.citaFacade.create(cita);
            
            this.Clear();
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(CitaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    void Clear(){
        this.cita.setId(0);
        this.cita.setEstado("");
        this.cita.setMedico(null);
        this.cita.setPaciente(null);
        this.cita.setUsers(null);
        this.cita.setFechaGeneracion(Calendar.getInstance().getTime());
        this.cita.setFechaConsultaActual(Calendar.getInstance().getTime());
        
       MedicoView.getInstance().setMedico(null);
        PacienteView.getInstance().setPaciente(null);
    }
}
