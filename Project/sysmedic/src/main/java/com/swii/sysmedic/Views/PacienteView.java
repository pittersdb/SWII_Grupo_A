/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.PacienteFacade;
import com.swii.sysmedic.entities.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author fabian
 */
public class PacienteView {
    
    @EJB
    private PacienteFacade pacienteFacade;
    private Paciente paciente = new Paciente();
    private List<Paciente> all = new ArrayList<Paciente>();
    
    private static PacienteView instance;
    /**
     * Creates a new instance of PacienteView
     */
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
        instance = this;
    }
    
    public PacienteView(){
        this.pacienteFacade = new PacienteFacade();      
    }

    public static PacienteView getInstance() {
        return instance;
    }
            
    
    
    public PacienteFacade getPacienteFacade() {
        return pacienteFacade;
    }

    public void setPacienteFacade(PacienteFacade pacienteFacade) {
        this.pacienteFacade = pacienteFacade;
    }

    public List<Paciente> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
            
        }
        return all;
    }

    public void setAll(List<Paciente> all) {
        this.all = all;
    }
    
    public List<Paciente> allFromDB(){
        return this.pacienteFacade.findAll();
    }

    public Paciente getPaciente() {              
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public void InstancePaciente(){
        this.paciente = new Paciente();        
    }
    
    public List<String> matchCi(String query) {
        
        List<String> results = new ArrayList<String>();
        paciente = this.pacienteFacade.GetPacienteByCi(query);
        if(paciente != null){      
            results.add(paciente.getCi());
        }else{
             paciente = new Paciente(0, "", "","", null, "", "", "");
        }
       
         
        return results;
    }
    
     public void onCiSelect(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         if(paciente == null) paciente = new Paciente(0, "", "","", null, "", "", "");
    }
     
    public void LoadPaciente(String ci){
        this.paciente = this.pacienteFacade.GetPacienteByCi(ci);
        
    }
     
     public void Insert(){
        boolean existPaciente =  this.pacienteFacade.GetPacienteByCi(paciente.getCi()) != null;
        try{
            if(!existPaciente){
                this.pacienteFacade.create(paciente);
                this.all.add(new Paciente(paciente));
                this.Clear();
            }
            else{
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El paciente con el numero de cedula "+paciente.getCi()+" ya existe, por favor elija otro."));
            }
                           
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);            
        }
    }
    
    public void Update(){
        try{
            Paciente originalpaciente = this.pacienteFacade.UpdateWithConstraints(paciente);
            int index = this.all.indexOf(originalpaciente);
            this.Clear();
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(PacienteView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void Delete(int id){
        try{
            Paciente pacienteToDelete = this.pacienteFacade.find(id);
            this.pacienteFacade.remove( pacienteToDelete);
            this.all.remove(pacienteToDelete);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(PacienteView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void Clear(){
        paciente.setNombres(null);
        paciente.setApellidos(null);
        paciente.setCi(null);
        paciente.setDirecion(null);
        paciente.setFechaNacimiento(null);
        paciente.setLugarProcedencia(null);
        
    }
    
}
