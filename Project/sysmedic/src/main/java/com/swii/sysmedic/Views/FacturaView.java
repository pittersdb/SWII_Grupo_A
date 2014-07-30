/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.FacturaFacade;
import com.swii.sysmedic.entities.Factura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author fabian
 */
public class FacturaView {

    @EJB
    private FacturaFacade facturaFacade;
    @EJB
    private Factura factura;
    private List<Factura> all = new ArrayList<Factura>();
    
    /**
     * Creates a new instance of FacturaView
     */
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
    }
    
    public FacturaView() {
        this.facturaFacade = new FacturaFacade();
    }
    
    public Factura getFactura(){
        return this.factura;
    }
    
    public List<Factura> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
        }
        return all;
    }
    
    public List<Factura> allFromDB(){
        return this.facturaFacade.findAll();
    }
}
