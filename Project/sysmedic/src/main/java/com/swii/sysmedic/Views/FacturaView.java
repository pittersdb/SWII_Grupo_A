/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.ClienteFacade;
import com.swii.sysmedic.Facades.FacturaFacade;
import com.swii.sysmedic.entities.Cliente;
import com.swii.sysmedic.entities.Factura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author fabian
 */
public class FacturaView {

    @EJB
    private FacturaFacade facturaFacade;
    @EJB
    private ClienteFacade clienteFacade;
 
    private Factura factura = new Factura();
    private Cliente cliente = new Cliente();
    
    private List<Factura> all = new ArrayList<Factura>();
    
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
    }
    
    public FacturaView() {
        this.facturaFacade = new FacturaFacade();
        this.clienteFacade = new ClienteFacade();
    }
    
    public Factura getUltimaFactura(){
        Factura fact = new Factura();
        List facturaList = getAll();
        
        int tamaño = facturaList.size() - 1;
        
        fact = this.facturaFacade.find(tamaño);
        
        return fact;
    }
    
    public Factura getFactura(){
        return this.factura;
    }
    
    public Cliente getCliente(){
        return this.cliente;
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
    
    public void Insert(){
        //this.clienteFacade.Insert(cliente);
        try{
            if(!this.clienteFacade.existsCliente(cliente.getRuc())){
                this.clienteFacade.Insert(cliente);
                this.factura.setCliente(cliente);
                this.facturaFacade.Insert(factura);
            }
            else{
                this.factura.setCliente(cliente);
                this.facturaFacade.Insert(factura);
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    } 
    
    private void Clear(){
        cliente.setId(null);
        cliente.setNombres(null);
        cliente.setApellidos(null);
        cliente.setRuc(null);
        cliente.setTelefono(null);
        cliente.setDirecion(null);
        
        factura.setId(null);
        factura.setNumero(0);
        factura.setFechaPago(null);
        factura.setObservacion(null);
        factura.setFormaPago(null);
        factura.setDescuentoTotal(0);
        factura.setIva(0);
        factura.setTotal(0);
        factura.setFechaAutorizacionSri(factura.getFechaAutorizacionSri());
        factura.setFechaCaducidadSri(factura.getFechaCaducidadSri());
        factura.setCliente(null);        
    }
}
