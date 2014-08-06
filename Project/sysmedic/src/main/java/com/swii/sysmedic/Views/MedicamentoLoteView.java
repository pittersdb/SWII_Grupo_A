/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicamentoFacade;
import com.swii.sysmedic.Facades.MedicamentoLoteFacade;
import com.swii.sysmedic.entities.MedicamentoLote;
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
    
    /**
     * Creates a new instance of MedicamentoLoteView
     */
    public MedicamentoLoteView() {
        this.medicamentoLoteFacade = new MedicamentoLoteFacade();
    }
    
    @PostConstruct
    public void init() {

    }
    
    public void Save(){
        try{
            if(!this.medicamentoLoteFacade.existsLote(lote.getCodigoLote())){
                this.medicamentoLoteFacade.SaveLote(lote);
//                this.all.add(new Medicamento(medicamento));
//                this.Clear();
            }else{
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El lote "+lote.getCodigoLote() +" del medicamento "+lote.getMedicamento().getNombre()+" ya existe, por favor elija otro."));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
