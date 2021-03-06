/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findById", query = "SELECT c FROM Cita c WHERE c.id = :id"),
    @NamedQuery(name = "Cita.findByFechaGeneracion", query = "SELECT c FROM Cita c WHERE c.fechaGeneracion = :fechaGeneracion"),
    @NamedQuery(name = "Cita.findByFechaConsultaActual", query = "SELECT c FROM Cita c WHERE c.fechaConsultaActual = :fechaConsultaActual"),
    @NamedQuery(name = "Cita.generalFinder", query = "" 
            + "SELECT c FROM Cita c "
            + "WHERE ("
            //+ "((:idPaciente IS NULL) OR (:idPaciente IS NOT NULL AND c.paciente.id = CAST(:idPaciente INTEGER))) AND"
            + "((:nullPaciente = TRUE) OR (:nullPaciente = FALSE AND c.paciente = :paciente)) AND"             
            + "((:nullMedico = TRUE) OR (:nullMedico = FALSE AND c.medico = :medico)) AND"   
            + "(c.fechaConsultaActual BETWEEN :fechaInf AND :fechaSup )  AND "
            + "((:estado IS NULL) OR (:estado IS NOT NULL AND c.estado = :estado ) )" 
            + ")"),
    @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado")})
public class Cita implements Serializable {
    
    public enum Estado {
        Postergado("p"), 
        Cancelado("c"), 
        Terminado("t"), 
        Pendiente("d"),
        Esperando("e"),
        EnProgreso("g"),
        Pagado("a"),
        Ninguno("n");
        
        
        private final String text;
        
        /**
         * @param text
         */
        private Estado(final String text) {
            this.text = text;
        }
        
        /* (non-Javadoc)
        * @see java.lang.Enum#toString()
        */
        @Override
        public String toString() {
            return text;
        }
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_generacion")
    @Temporal(TemporalType.DATE)
    private Date fechaGeneracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_consulta_actual")
    @Temporal(TemporalType.DATE)
    private Date fechaConsultaActual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToOne(mappedBy = "proximaCita")
    private Consulta consultaProximaCita;
//    @OneToOne(optional = false, mappedBy = "cita")
    @Transient
    private Consulta consulta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cita")
    private Collection<CitaCancelada> citaCanceladaCollection;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paciente paciente;
    @JoinColumn(name = "medico_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medico medico;
    @JoinColumn(name = "generador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users users;
    
    public Cita() {
    }
    
    public Cita(Integer id) {
        this.id = id;
    }
    
    public Cita(Integer id, Date fechaGeneracion, Date fechaConsultaActual, String estado) {
        this.id = id;
        this.fechaGeneracion = fechaGeneracion;
        this.fechaConsultaActual = fechaConsultaActual;
        this.estado = estado;
    }
    
    public void set(Cita cita) {
        this.id = cita.getId();
        this.fechaGeneracion = cita.getFechaGeneracion();
        this.fechaConsultaActual = cita.getFechaGeneracion();
        this.estado = cita.getEstado();
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }
    
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    
    public Date getFechaConsultaActual() {
        return fechaConsultaActual;
    }
    
    public void setFechaConsultaActual(Date fechaConsultaActual) {
        this.fechaConsultaActual = fechaConsultaActual;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getEstadoName(){
        if(estado.equalsIgnoreCase(Cita.Estado.Postergado.toString()))
            return "Postergado";
        if(estado.equalsIgnoreCase(Cita.Estado.Cancelado.toString()))
            return "Cancelado";
        if(estado.equalsIgnoreCase(Cita.Estado.Pendiente.toString()))
            return "Pendiente";
        if(estado.equalsIgnoreCase(Cita.Estado.Terminado.toString()))
            return "Terminado";
         if(estado.equalsIgnoreCase(Cita.Estado.Esperando.toString()))
            return "Esperando";
        return "Ninguno";
    }
    
    @XmlTransient
    public Consulta getConsultaProximaCita() {
        return consultaProximaCita;
    }
    
    public void setConsultaProximaCita(Consulta consultaProximaCita) {
        this.consultaProximaCita = consultaProximaCita;
    }
    
    public Consulta getConsulta() {
//        if(consulta == null){
//            consulta = new Consulta();
//        }            
        return consulta; 
    }
    
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    @XmlTransient
    public Collection<CitaCancelada> getCitaCanceladaCollection() {
        return citaCanceladaCollection;
    }
    
    public void setCitaCanceladaCollection(Collection<CitaCancelada> citaCanceladaCollection) {
        this.citaCanceladaCollection = citaCanceladaCollection;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Medico getMedico() {
        return medico;
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public Users getUsers() {
        return users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Cita[ id=" + id + " ]";
    }
    
}
