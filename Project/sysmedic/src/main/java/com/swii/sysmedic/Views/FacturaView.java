/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.CitaFacade;
import com.swii.sysmedic.Facades.ClienteFacade;
import com.swii.sysmedic.Facades.ConsultaFacade;
import com.swii.sysmedic.Facades.DetalleFacturaConsultaFacade;
import com.swii.sysmedic.Facades.EspecialidadFacade;
import com.swii.sysmedic.Facades.FacturaFacade;
import com.swii.sysmedic.Facades.MedicoFacade;
import com.swii.sysmedic.Facades.PacienteFacade;
import com.swii.sysmedic.Facades.TarifarioFacade;
import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Cliente;
import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.DetalleFacturaConsulta;
import com.swii.sysmedic.entities.Especialidad;
import com.swii.sysmedic.entities.Factura;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Paciente;
import com.swii.sysmedic.entities.Tarifario;
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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author fabian
 */
public class FacturaView {

    @EJB
    private FacturaFacade facturaFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private PacienteFacade pacienteFacade;
//    @EJB
    //private DetalleFacturaConsultaFacade detalleFacturaConsultaFacade;
    @EJB
    private TarifarioFacade tarifarioFacade;
    @EJB
    private ConsultaFacade consultaFacade;
    @EJB
    private MedicoFacade medicoFacade;
    @EJB
    private CitaFacade citaFacade;
    @EJB
    private EspecialidadFacade especialidadFacade;
//
    private Cita cita = new Cita();
    private Medico medico = new Medico();
    private Especialidad especialidad = new Especialidad();
    private Factura factura = new Factura();
    private Cliente cliente = new Cliente();
    private Paciente paciente = new Paciente();
    private Consulta consulta = new Consulta();
    private Tarifario tarifario = new Tarifario();
    
    private List<Factura> all = new ArrayList<Factura>();
    private Double descuento_Consulta ;

    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
    }
    
    public FacturaView() {
        this.facturaFacade = new FacturaFacade();
        this.clienteFacade = new ClienteFacade();
        this.pacienteFacade = new PacienteFacade();
        this.consultaFacade = new ConsultaFacade();
        this.citaFacade = new CitaFacade();
        this.medicoFacade = new MedicoFacade();
        this.especialidadFacade = new EspecialidadFacade();
    }
    
    public List<String> matchCi(String query) {
        
        List<String> results = new ArrayList<String>();
        paciente = this.pacienteFacade.GetPacienteByCi(query);
        
        //consulta = this.consultaFacade.GetConsultaByIdPaciente(paciente); 
        consulta = this.consultaFacade.GetConsultaById(1); 
        cita = this.citaFacade.GetCitaById(consulta.getCita().getId());
        medico = this.medicoFacade.GetMedicoById(cita.getMedico().getId());
        especialidad = this.especialidadFacade.GetEspecialidadById(medico.getEspecialidad().getId());
        //tarifario = this.tarifarioFacade.GetTarifarioByEspecialidad(especialidad.getNombre());
        
        if(paciente != null){      
            results.add(paciente.getCi());
        }else{
             paciente = new Paciente(0, "", "","", null, "", "", "");
        }
       
         
        return results;
    }
    
     public void onCiSelect(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         if(paciente == null) paciente = new Paciente(0, "", "","", null, "", "", "");
    }
     
    public void onCita(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         //if(cita == null) cita = new Cita(0, null, null, "");
         if(consulta == null) consulta = new Consulta(0, 0, 0, 0, 0, "", "");
    }
    
    public void onEspecialidad(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         if(paciente == null) paciente = new Paciente(0, "", "","", null, "", "", "");
    }
    
    public void onTarifario(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         if(paciente == null) paciente = new Paciente(0, "", "","", null, "", "", "");
    }
    
    public void onFactura(SelectEvent event) {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
         System.out.println("SELECT PACIENTE");
         if(paciente == null) paciente = new Paciente(0, "", "","", null, "", "", "");
    }
       
    public Integer getProximoNumero(){
        int numero, tamaño ;
        Factura fact = new Factura();
        List facturaList = getAll();
        
        tamaño = facturaList.size() - 1;        
        fact = this.facturaFacade.find(tamaño);
        
        return numero = fact.getNumero() + 1;
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
    
    public TarifarioFacade getTarifarioFacade() {
        return tarifarioFacade;
    }

    public void setTarifarioFacade(TarifarioFacade tarifarioFacade) {
        this.tarifarioFacade = tarifarioFacade;
    }

    public Tarifario getTarifario() {
        return tarifario;
    }

    public void setTarifario(Tarifario tarifario) {
        this.tarifario = tarifario;
    }

    public FacturaFacade getFacturaFacade() {
        return facturaFacade;
    }

    public void setFacturaFacade(FacturaFacade facturaFacade) {
        this.facturaFacade = facturaFacade;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public PacienteFacade getPacienteFacade() {
        return pacienteFacade;
    }

    public void setPacienteFacade(PacienteFacade pacienteFacade) {
        this.pacienteFacade = pacienteFacade;
    }

    public ConsultaFacade getConsultaFacade() {
        return consultaFacade;
    }

    public void setConsultaFacade(ConsultaFacade consultaFacade) {
        this.consultaFacade = consultaFacade;
    }

    public MedicoFacade getMedicoFacade() {
        return medicoFacade;
    }

    public void setMedicoFacade(MedicoFacade medicoFacade) {
        this.medicoFacade = medicoFacade;
    }

    public CitaFacade getCitaFacade() {
        return citaFacade;
    }

    public void setCitaFacade(CitaFacade citaFacade) {
        this.citaFacade = citaFacade;
    }

    public EspecialidadFacade getEspecialidadFacade() {
        return especialidadFacade;
    }

    public void setEspecialidadFacade(EspecialidadFacade especialidadFacade) {
        this.especialidadFacade = especialidadFacade;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public void Insert(){
        //this.clienteFacade.Insert(cliente);
        try{
            if(!this.clienteFacade.existsCliente(cliente.getRuc())){
                this.clienteFacade.Insert(cliente);
                this.factura.setCliente(cliente);
                this.facturaFacade.Insert(factura);
                this.Clear();
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
