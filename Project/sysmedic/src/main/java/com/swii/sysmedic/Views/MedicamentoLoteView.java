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
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.postgresql.jdbc2.EscapedFunctions;

/**
 *
 * @author software
 */
public class MedicamentoLoteView {

    @EJB
    private MedicamentoLoteFacade medicamentoLoteFacade;

    private MedicamentoLote lote = new MedicamentoLote();
    private int selectedMedicamento, selectedMedicamentoDev, selectedLoteDev;
    private int selectedOrder;
    private List<MedicamentoLote> all = new ArrayList<>();
    private List<MedicamentoLote> allLotes = new ArrayList<>();
    private List<MedicamentoLote> allCaducado = new ArrayList<>();
    private List<MedicamentoLote> allStock = new ArrayList<>();
    /**
     * Creates a new instance of MedicamentoLoteView
     */
    public MedicamentoLoteView() {
        this.medicamentoLoteFacade = new MedicamentoLoteFacade();
    }
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
//        Collections.sort(allStock,new Comparator<MedicamentoLote>(){
//                     @Override
//                     public int compare(MedicamentoLote m1, MedicamentoLote m2){
//                         return m1.getMedicamento().getNombre().compareTo(m2.getMedicamento().getNombre());
//                     }});
        UpdateMedicamentosCaducados();
    }
    
    public void UpdateMedicamentosCaducados(){
        //Date date = new Date();
        List<MedicamentoLote> allt = new ArrayList<MedicamentoLote>();
        Iterator<MedicamentoLote> it;
        
        allt.addAll(all);
        it = allt.iterator();
        MedicamentoLote lo = new MedicamentoLote();
        while(it.hasNext()){
            lo = it.next();
            if(lo.getFechaCaducidad().before(getToday())){
                this.medicamentoLoteFacade.updateEstadoA("c", lo.getId());
            }
        }
    }
    
    public Date getToday() {
        return new Date();
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
            Collections.sort(allLotes,new Comparator<MedicamentoLote>(){
                @Override
                 public int compare(MedicamentoLote m1, MedicamentoLote m2){
                     int temp;
                     if (m1.getCodigoLote() < m2.getCodigoLote()){temp = -1;}
                     else if (m1.getCodigoLote() > m2.getCodigoLote()){temp = 1;}
                     else {temp = 0;}
                     return temp;
                 }});
            return allLotes;
        } else {
            return new ArrayList<>();
        }

    }
    
    public List<MedicamentoLote> getAllCaducado() {
        if(allCaducado.isEmpty()){
            allCaducado.addAll(medicamentoLoteFacade.getLotesbyEstado("c"));
            allCaducado.addAll(medicamentoLoteFacade.getLotesbyEstado("b"));
        }
        return allCaducado;
    }
    
    public List<MedicamentoLote> allForStock() {
        //this.allStock.clear();
        return this.medicamentoLoteFacade.getLotesbyEstado("d");
    }
    
    public List<MedicamentoLote> getAllStock() {
        if(allStock.isEmpty()){
            allStock.addAll(allForStock());
        }
        return allStock;
    }
    
    public void UpdateAllLotes(){
        this.allLotes.clear();
        this.allLotes.addAll(this.medicamentoLoteFacade.getLotesbyMedicamento(getSelectedMedicamentoDev()));
        Collections.sort(allLotes,new Comparator<MedicamentoLote>(){
                @Override
                 public int compare(MedicamentoLote m1, MedicamentoLote m2){
                     int temp;
                     if (m1.getCodigoLote() < m2.getCodigoLote()){temp = -1;}
                     else if (m1.getCodigoLote() > m2.getCodigoLote()){temp = 1;}
                     else {temp = 0;}
                     return temp;
                 }});
    }
    
    public void OrderAllFecha(){
        Collections.sort(allStock,new Comparator<MedicamentoLote>(){
                @Override
                 public int compare(MedicamentoLote m1, MedicamentoLote m2){
                     return m1.getFechaIngreso().compareTo(m2.getFechaIngreso());
                 }});
    }

    public void OrderAllMedicamento(){
        Collections.sort(allStock,new Comparator<MedicamentoLote>(){
                 @Override
                 public int compare(MedicamentoLote m1, MedicamentoLote m2){
                     return m1.getMedicamento().getNombre().compareTo(m2.getMedicamento().getNombre());
                 }});
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
            this.lote.setCodigoLote(medicamentoLoteFacade.getNewCodigoLote(selectedMedicamento));
            this.lote.setEstado("d");
            this.lote.setMedicamento(new Medicamento(selectedMedicamento));
            
            this.medicamentoLoteFacade.SaveLote(lote);
            allStock.add(lote);
            all.add(lote);
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void Devolver(int id){
        try{
            MedicamentoLote loteToReturn = this.medicamentoLoteFacade.find(id);
            this.medicamentoLoteFacade.updateEstadoA("b", loteToReturn.getId());
            //this.medicamentoLoteFacade.remove(loteToReturn);
            allStock.remove(loteToReturn);
            this.all.remove(loteToReturn);
            allLotes.remove(loteToReturn);
            allCaducado.add(loteToReturn);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    

}
