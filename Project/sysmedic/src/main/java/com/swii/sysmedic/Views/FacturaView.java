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
    private DetalleFacturaConsulta dfc = new DetalleFacturaConsulta();

    public DetalleFacturaConsulta getDfc() {
        return dfc;
    }

    public void setDfc(DetalleFacturaConsulta dfc) {
        this.dfc = dfc;
    }
    
    private List<Factura> all = new ArrayList<Factura>();
    private int descuento_Consulta =0;
    private Double total_Consulta = 0.0;

    public Double getTotal_Consulta() {
        return total_Consulta;
    }

    public void setTotal_Consulta(Double total_Consulta) {
        this.total_Consulta = total_Consulta;
    }

    public int getDescuento_Consulta() {
        return descuento_Consulta;
    }

    public void setDescuento_Consulta(int descuento_Consulta) {
        this.descuento_Consulta = descuento_Consulta;
    }

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
        this.tarifarioFacade = new TarifarioFacade();
    }
    
    public List<String> matchCi(String query) {
        
        List<String> results = new ArrayList<String>();
        paciente = this.pacienteFacade.GetPacienteByCi(query);
        
        consulta = this.consultaFacade.GetConsultaByIdPaciente(paciente); 
        //consulta = this.consultaFacade.GetConsultaById(1); 
        cita = consulta.getCita();
        medico = consulta.getCita().getMedico();
        especialidad = medico.getEspecialidad();
        tarifario = this.tarifarioFacade.GetTarifarioByEspecialidad(especialidad.getNombre());
        
        int descuento = (int) (tarifario.getPrecio()*0.20);
        double total = tarifario.getPrecio() - descuento;
        this.setDescuento_Consulta(descuento);
        this.setTotal_Consulta(total);
        
        this.dfc.setDFC(descuento, total, tarifario, consulta);
         
        if(paciente != null){      
            results.add(paciente.getCi());
        }else{
             paciente = new Paciente(0, "", "","", null, "", "", "");
        }
        return results;
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
    
//    
    
    public void Insert(){
        //this.clienteFacade.Insert(cliente);
        
        try{
            this.facturaFacade.Insert(cliente, factura, dfc);
            this.Clear();
            
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
