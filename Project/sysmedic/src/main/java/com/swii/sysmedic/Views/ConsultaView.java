/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.CitaFacade;
import com.swii.sysmedic.Facades.ConsultaFacade;
import com.swii.sysmedic.Facades.MedicacionFacade;
import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.Medicacion;
import com.swii.sysmedic.entities.MedicacionPK;
import com.swii.sysmedic.entities.Turno;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
        try{            
            if(!isReadyToStart(turno)){
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "Debe primero ingresar las MEDICIONES para poder iniciar la consulta"));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public boolean isReadyToStart(Turno turno){
        return  turno != null && turno.getCita().getConsulta() != null  && turno.getCita().getConsulta().getId() != null;
    }
    
    public void ModifyInstruccionMedicacion(Turno turno, Medicacion medicacion){
        try{            
            //COnsidering that medicacion is inside the collecton, we can update the collection
             Consulta consultaToPrescribe = turno.getCita().getConsulta();             
             this.consultaFacade.EditMedicacion(consultaToPrescribe);                                      
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void AddInstruccionMedicacion(Turno turno, Medicacion medicacion){
         try{            
             
             Consulta consultaToPrescribe = turno.getCita().getConsulta();
             
             if(consultaToPrescribe.getMedicacionCollection() == null)
                 consultaToPrescribe.setMedicacionCollection(new ArrayList<Medicacion>());
            
             medicacion.setMedicacionPK(new MedicacionPK(consultaToPrescribe.getId(), medicacion.getMedicamento().getId()));
             medicacion.setConsulta(consultaToPrescribe);                 
             
             this.consultaFacade.AddMedicacion(consultaToPrescribe,  new Medicacion(medicacion));
             
             medicacion.Clear();
             
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void DeleteInstruccionMedicacion(Turno turno, Medicacion medicacion){
        try{            
            //COnsidering that medicacion is inside the collecton, we can update the collection
             Consulta consultaToPrescribe = turno.getCita().getConsulta();           
             this.consultaFacade.DeleteMedicacion(consultaToPrescribe, medicacion);                                      
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ConsultaView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
