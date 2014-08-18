/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicoFacade;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author LUCAS
 */
public class MedicoView {
    
    @EJB
    private MedicoFacade medicFacade;
    private Medico medico = new Medico();
    private Medico   medicoDeTurnos = new Medico();
    private List<Medico> all = new ArrayList<Medico>();
    
    private static MedicoView instance;
    
    /**
     * Creates a new instance of MedicoView
     */
    public MedicoView() {
    }
    
      @PostConstruct
    public void init() {
        instance = this;
    }
    
   public static MedicoView getInstance() {
        return instance;
    }

    public Medico getMedico() {
        return medico;
    }
    
    public Medico getMedico(int id) {
        return this.medicFacade.find(id);        
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Medico getMedicoDeTurnos() {
        return medicoDeTurnos;
    }

    public void setMedicoDeTurnos(Medico medicoDeTurnos) {
        this.medicoDeTurnos = medicoDeTurnos;
    }

    public List<Medico> getAll() {
        if(all.isEmpty())
            all = this.medicFacade.findAll();
        return all;
    }

    public void setAll(List<Medico> all) {
        this.all = all;
    }
    
    
    
//    
//    public List<String> matchCi(String query) {
//        
//        //List<Paciente> results = this.pacienteFacade.GetPacientesByCi(query);
//        List<Medico> medics = this.medicFacade.GetMedicoByName(query, query);        
//        List<String> results = new ArrayList<String>();        
//        for (Medico m : medics) {
//            results.add(m.getName() + " " +m.getApellidos());
//        }      
//        
//        return results;
//    }
    
    public void onSelect(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
        System.out.println("SELECT MEDIC");
    }
    
     public List<Medico> completeMedico(String query) {
         System.out.println("COMPLETE:");
        List<Medico> medics = this.medicFacade.GetMedicoByName(query, query);             
        return medics;
    }
}
