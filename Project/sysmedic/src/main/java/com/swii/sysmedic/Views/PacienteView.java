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
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author LUCAS
 */
public class PacienteView {
    
    @EJB
    private PacienteFacade pacienteFacade;
    private Paciente paciente = new Paciente();
    
    /**
     * Creates a new instance of PacienteView
     */
    public PacienteView() {
    }

    public Paciente getPaciente() {              
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public List<String> matchCi(String query) {
        
       //List<Paciente> results = this.pacienteFacade.GetPacientesByCi(query);
        List<String> results = new ArrayList<String>();
        Paciente pacienteByCi = this.pacienteFacade.GetPacienteByCi(query);
        if(pacienteByCi != null){      
             paciente = pacienteByCi; 
            results.add(pacienteByCi.getCi());
        }else{
             paciente = new Paciente(0, "", "", null, "", "", "");
        }
       
         
        return results;
    }
    
     public void onCiSelect(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
    }
     
     public void onCiSelect(AjaxBehaviorEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         paciente = new Paciente(0, "", "", null, "", "", "");
    }
    
    
    
    
}
