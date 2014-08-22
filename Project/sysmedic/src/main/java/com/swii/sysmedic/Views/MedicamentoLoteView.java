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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author software
 */
public class MedicamentoLoteView {

    @EJB
    private MedicamentoLoteFacade medicamentoLoteFacade;

    private MedicamentoLote lote = new MedicamentoLote();
    private int selectedMedicamento, selectedMedicamentoDev, selectedLoteDev;
    private List<MedicamentoLote> all = new ArrayList<MedicamentoLote>();
    private List<MedicamentoLote> allLotes = new ArrayList<MedicamentoLote>();
    /**
     * Creates a new instance of MedicamentoLoteView
     */
    public MedicamentoLoteView() {
        this.medicamentoLoteFacade = new MedicamentoLoteFacade();
    }
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
        //allLotes.addAll(medicamentoLoteFacade.getLotesbyMedicamento(selectedMedicamentoDev));
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
    
    public List<MedicamentoLote> getAllLotes() {

        if (selectedMedicamentoDev != -1) {
            allLotes.removeAll(all);
            allLotes.addAll(medicamentoLoteFacade.getLotesbyMedicamento(selectedMedicamentoDev));
            return allLotes;
        } else {
            return new ArrayList<MedicamentoLote>();
        }

        //return allLotes;
    }
    
    public void UpdateAllLotes(){
        this.allLotes.clear();
        this.allLotes.addAll(this.medicamentoLoteFacade.getLotesbyMedicamento(getSelectedMedicamentoDev()));
    }
    
    public void setAllLotes(List<MedicamentoLote> allLotes) {
        this.allLotes = allLotes;
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
    
    public int getSelectedLoteDev(){
        return this.selectedLoteDev;
    }
    
    public void setSelectedLoteDev(int selectedLote){
        this.selectedLoteDev = selectedLote;
    }
    
    public MedicamentoLote getMedicamentoLote() {              
        return lote;
    }

    public void setMedicamentoLote(MedicamentoLote lote) {
        this.lote = lote;
    }
    
    public void medicamentoLoteDevChanged(ValueChangeEvent e){
        
        if ((int) e.getNewValue() != selectedMedicamentoDev) {
            selectedLoteDev = -1;
        }
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
            //this.UpdateAllLotes();
//                this.Clear();
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void Devolver(int id){
        try{
            MedicamentoLote loteToReturn = this.medicamentoLoteFacade.find(id);
            this.medicamentoLoteFacade.updateEstado(loteToReturn.getId());
            //this.medicamentoLoteFacade.remove(loteToReturn);
            this.all.remove(loteToReturn);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
