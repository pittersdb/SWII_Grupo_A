/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.DetalleFacturaConsultaFacade;
import com.swii.sysmedic.entities.DetalleFacturaConsulta;
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
public class DetalleFacturaConsultaView {
    
    @EJB
    private DetalleFacturaConsultaFacade detalleFacturaConsultaFacade;
    private DetalleFacturaConsulta detalleFacturaConsulta = new DetalleFacturaConsulta();
    private List<DetalleFacturaConsulta> all = new ArrayList<DetalleFacturaConsulta>();
    
    //private static PacienteView instance;
    /**
     * Creates a new instance of PacienteView
     */
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
        //instance = this;
    }
    
    public DetalleFacturaConsultaView(){
        this.detalleFacturaConsultaFacade = new DetalleFacturaConsultaFacade();      
    }

//    public static PacienteView getInstance() {
//        return instance;
//    }
            
    
    
    public DetalleFacturaConsultaFacade getDetalleFacturaConsultaFacade() {
        return detalleFacturaConsultaFacade;
    }

    public void setDetalleFacturaConsultaFacade(DetalleFacturaConsultaFacade detalleFacturaConsultaFacade) {
        this.detalleFacturaConsultaFacade = detalleFacturaConsultaFacade;
    }

    public List<DetalleFacturaConsulta> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
            
        }
        return all;
    }

    public void setAll(List<DetalleFacturaConsulta> all) {
        this.all = all;
    }
    
    public List<DetalleFacturaConsulta> allFromDB(){
        return this.detalleFacturaConsultaFacade.findAll();
    }

    public DetalleFacturaConsulta getdetalleFacturaConsulta() {              
        return detalleFacturaConsulta;
    }

    public void setDetalleFacturaConsulta(DetalleFacturaConsulta detalleFacturaConsulta) {
        this.detalleFacturaConsulta = detalleFacturaConsulta;
    }
    
    public void InstanceDetalleFacturaConsulta(){
        this.detalleFacturaConsulta = new DetalleFacturaConsulta();        
    }
    
    public void onCiSelect(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         //if(paciente == null) paciente = new Paciente(0, "", "","", null, "", "", "");
    }
     
   
     
//     public void Insert(){
//        boolean existPaciente =  this.pacienteFacade.GetPacienteByCi(paciente.getCi()) != null;
//        try{
//            if(!existPaciente){
//                this.pacienteFacade.create(paciente);
//                this.all.add(new Paciente(paciente));
//                this.Clear();
//            }
//            else{
//                FacesContext.getCurrentInstance().validationFailed();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El paciente con el numero de cedula "+paciente.getCi()+" ya existe, por favor elija otro."));
//            }
//                           
//        }catch(Exception e){
//            FacesContext.getCurrentInstance().validationFailed();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
//            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);            
//        }
//    }
//    
//    public void Update(){
//        try{
//            Paciente originalpaciente = this.pacienteFacade.UpdateWithConstraints(paciente);
//            int index = this.all.indexOf(originalpaciente);
//            this.Clear();
//        }catch(Exception e){
//            FacesContext.getCurrentInstance().validationFailed();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
//            Logger.getLogger(PacienteView.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
//    
//    public void Delete(int id){
//        try{
//            Paciente pacienteToDelete = this.pacienteFacade.find(id);
//            this.pacienteFacade.remove( pacienteToDelete);
//            this.all.remove(pacienteToDelete);
//        }catch(Exception e){
//            FacesContext.getCurrentInstance().validationFailed();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
//            Logger.getLogger(PacienteView.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
//    
//    private void Clear(){
//        paciente.setNombres(null);
//        paciente.setApellidos(null);
//        paciente.setCi(null);
//        paciente.setDirecion(null);
//        paciente.setFechaNacimiento(null);
//        paciente.setLugarProcedencia(null);
//        
//    }
//    
}
