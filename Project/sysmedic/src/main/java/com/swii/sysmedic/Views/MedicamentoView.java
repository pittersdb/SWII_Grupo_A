/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicamentoFacade;
import com.swii.sysmedic.entities.Medicamento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author software
 */

public class MedicamentoView {
    @EJB
    private MedicamentoFacade medicamentoFacade;
    private Medicamento medicamento = new Medicamento();
    private List<Medicamento> all = new ArrayList<Medicamento>();
    /**
     * Creates a new instance of MedicamentoView
     */
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
    }
    
    public MedicamentoView() {
        this.medicamentoFacade = new MedicamentoFacade();
    }
    
    public Medicamento getMedicamento(){
        return this.medicamento;
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
        boolean existsMedicamento = this.medicamentoFacade.GetMedicamento(medicamento.getId()) != null;

        try{
            if(!existsMedicamento){
                this.medicamentoFacade.create(medicamento);
                this.all.add(new Medicamento(medicamento));
                this.Clear();
            }else{
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El medicamento "+medicamento.getNombre()+" ya existe, por favor elija otro."));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(MedicamentoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void Clear(){
        medicamento.setNombre(null);
        medicamento.setDescripcion(null);
    }
}
