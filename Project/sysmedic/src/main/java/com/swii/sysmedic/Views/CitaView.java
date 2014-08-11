/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.CitaFacade;
import com.swii.sysmedic.entities.Cita;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    
    private String ciPaciente;
    private String selectedEstado;
    private Date fechaInf;
    private Date fechaSup;
    
    
    private List<Cita> resultSet = new ArrayList<Cita>();
    
    /**
     * Creates a new instance of CitaView
     */
    public CitaView() {
        cita.setFechaGeneracion(Calendar.getInstance().getTime());
        cita.setFechaConsultaActual(Calendar.getInstance().getTime());
        fechaInf = Calendar.getInstance().getTime();
        fechaSup = Calendar.getInstance().getTime();
    }
    
    @PostConstruct
    public void init() {
    }

    public String getCiPaciente() {
        return ciPaciente;
    }

    public void setCiPaciente(String ciPaciente) {
        this.ciPaciente = ciPaciente;
    }
    
    
    
    
    public String getTest() {
        return test;
    }
    
    public void setTest(String test) {
        this.test = test;
    }
    
    public String getSelectedEstado() {
        return selectedEstado;
    }
    
    public void setSelectedEstado(String selectedEstado) {
        this.selectedEstado = selectedEstado;
    }
    
    
    
    public Cita getCita() {
        return cita;
    }
    
    public void setCita(Cita cita) {
        this.cita = cita;
    }
    
    public Date getFechaInf() {
        return fechaInf;
    }
    
    public void setFechaInf(Date fechaInf) {
        this.fechaInf = fechaInf;
    }
    
    public Date getFechaSup() {
        return fechaSup;
    }
    
    public void setFechaSup(Date fechaSup) {
        this.fechaSup = fechaSup;
    }
    
    public List<Cita> getResultSet() {
        return resultSet;
    }
    
    public void setResultSet(List<Cita> resultSet) {
        this.resultSet = resultSet;
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
    
    public void Buscar(){
        try{            
            if( this.fechaSup.before(this.fechaInf)) {
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "La fecha final es menor a la inicial. Ingrese un rango valido correcto"));
            }else{
                System.out.println("SELECTED ESTADO: "+ fechaInf);
                if(selectedEstado.equals(Cita.Estado.Ninguno.toString())) selectedEstado = null;
                
                this.resultSet = this.citaFacade.GeneralSearching(PacienteView.getInstance().getPaciente(), MedicoView.getInstance().getMedico(), fechaInf, fechaSup, selectedEstado);
                
                System.out.println("QUERY CITA: ");
                for (Cita cita1 : resultSet) {
                    System.out.println("cita: "+ cita1.getId());
                }
            }
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
