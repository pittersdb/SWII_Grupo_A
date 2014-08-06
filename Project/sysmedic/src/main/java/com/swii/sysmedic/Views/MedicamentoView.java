/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicamentoFacade;
import com.swii.sysmedic.entities.Medicamento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author fabian
 */

public class MedicamentoView {
    @EJB
    private MedicamentoFacade medicamentoFacade;

    private Medicamento medicamento = new Medicamento();
    private List<Medicamento> all = new ArrayList<Medicamento>();
    private String selectedItem; // +getter +setter
    private List<SelectItem> availableMedicamentos; // +getter (no setter necessary)

    /**
     * Creates a new instance of MedicamentoView
     */
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
        availableMedicamentos = medicamentoFacade.list();
    }
    
    public MedicamentoView() {
        this.medicamentoFacade = new MedicamentoFacade();
    }
    
    public Medicamento getMedicamento(){
        return this.medicamento;
    }
    
    public String getSelectedMedicamento() {
        return selectedItem;
    }

    public void setSelectMedicamento(String selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    public List<SelectItem> getAvailableMedicamentos() {
        return availableMedicamentos;
    }
    
    public List<Medicamento> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
        }
        return all;
    }
    
    public List<Medicamento> allFromDB(){
        return this.medicamentoFacade.findAll();
    }
    
    public void Save(){
        try{
            if(!this.medicamentoFacade.existsMedicamento(medicamento.getNombre())){
                this.medicamentoFacade.Save(medicamento);
                this.all.add(new Medicamento(medicamento));
                this.Clear();
            }else{
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El medicamento "+medicamento.getNombre()+" ya existe, por favor elija otro."));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void Clear(){
        medicamento.setNombre(null);
        medicamento.setDescripcion(null);
    }
}
