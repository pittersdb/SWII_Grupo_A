/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicamentoFacade;
import com.swii.sysmedic.Facades.MedicamentoLoteFacade;
import com.swii.sysmedic.entities.Medicamento;
import com.swii.sysmedic.entities.MedicamentoLote;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author software
 */
public class MedicamentoLoteView {

    @EJB
    private MedicamentoLoteFacade medicamentoLoteFacade;

    private MedicamentoLote lote = new MedicamentoLote();
    private int selectedMedicamento, selectedMedicamentoDev;
    private List<MedicamentoLote> all = new ArrayList<MedicamentoLote>();
    
    /**
     * Creates a new instance of MedicamentoLoteView
     */
    public MedicamentoLoteView() {
        this.medicamentoLoteFacade = new MedicamentoLoteFacade();
    }
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
    }
    
    public List<MedicamentoLote> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
        }
        return all;
    }
    
    public void setAll(List<MedicamentoLote> all) {
        this.all = all;
    }
    
    public List<MedicamentoLote> allFromDB(){
        return this.medicamentoLoteFacade.findAll();
    }
    
    public int getSelectedMedicamento(){
        return this.selectedMedicamento;
    }
    
    public void setSelectedMedicamento(int selectedMedicamento){
        this.selectedMedicamento = selectedMedicamento;
    }
    
    public int getSelectedMedicamentoDev(){
        return this.selectedMedicamentoDev;
    }
    
    public void setSelectedMedicamentoDev(int selectedMedicamento){
        this.selectedMedicamentoDev = selectedMedicamento;
    }
    
    public MedicamentoLote getMedicamentoLote() {              
        return lote;
    }

    public void setMedicamentoLote(MedicamentoLote lote) {
        this.lote = lote;
    }
    
    public void Save(){
        try{
            System.out.println("This is the selectedMedicamento: " + selectedMedicamento);
            long newCodigo = this.medicamentoLoteFacade.getNewCodigoLote(selectedMedicamento);
            this.lote.setCodigoLote(newCodigo);
            this.lote.setEstado("d");
            this.lote.setMedicamento(new Medicamento(selectedMedicamento));
            this.all.add(new MedicamentoLote(lote));
            
            this.medicamentoLoteFacade.SaveLote(lote);
//                this.all.add(new Medicamento(medicamento));
//                this.Clear();
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
