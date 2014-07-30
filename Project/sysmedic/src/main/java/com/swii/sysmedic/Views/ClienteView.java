/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.ClienteFacade;
import com.swii.sysmedic.entities.Cliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author fabian
 */
public class ClienteView {
    
    @EJB
    private ClienteFacade clienteFacade;
    private Cliente cliente = new Cliente();
    private List<Cliente> all = new ArrayList<Cliente>();
    
    
    /**
     * Creates a new instance of PacienteView
     */
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
        
    }
    
    public ClienteView(){
        this.clienteFacade = new ClienteFacade();        
    }
            
    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public List<Cliente> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
            
        }
        return all;
    }

    public void setAll(List<Cliente> all) {
        this.all = all;
    }
    
    public List<Cliente> allFromDB(){
        return this.clienteFacade.findAll();
    }

    public Cliente getCliente() {              
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void LoadCliente(String ruc){
        this.cliente = this.clienteFacade.GetCliente(ruc);
        
    }
     
     public void Insert(){
        boolean existCliente =  this.clienteFacade.GetCliente(cliente.getRuc()) != null;
        try{
            if(!existCliente){
                this.clienteFacade.create(cliente);
                this.all.add(new Cliente(cliente));
                this.Clear();
            }
            else{
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El Cliente con el numero de cedula "+cliente.getRuc()+" ya existe, por favor elija otro."));
            }
                           
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);            
        }
    }
    
    public void Update(){
        try{
            Cliente originalcliente = this.clienteFacade.UpdateWithConstraints(cliente);
            int index = this.all.indexOf(originalcliente);
            this.Clear();
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(ClienteView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void Delete(int id){
        try{
            Cliente clienteToDelete = this.clienteFacade.find(id);
            this.clienteFacade.remove( clienteToDelete);
            this.all.remove(clienteToDelete);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(PacienteView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void Clear(){
        cliente.setNombres(null);
        cliente.setApellidos(null);
        cliente.setRuc(null);
        cliente.setDirecion(null);
        cliente.setId(null);
        cliente.setTelefono(null);        
    }
    
}
