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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author fabian
 */

public class MedicamentoView {
    @EJB
    private MedicamentoFacade medicamentoFacade;
    @EJB
    private Medicamento medicamento;
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
    
}
