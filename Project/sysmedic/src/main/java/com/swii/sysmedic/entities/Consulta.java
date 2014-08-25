/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabian
 */
@Entity
@Table(name =  "\"SysMedic\".consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c"),
    @NamedQuery(name = "Consulta.findById", query = "SELECT c FROM Consulta c WHERE c.id = :id"),
    @NamedQuery(name = "Consulta.findByPeso", query = "SELECT c FROM Consulta c WHERE c.peso = :peso"),
    @NamedQuery(name = "Consulta.findByTalla", query = "SELECT c FROM Consulta c WHERE c.talla = :talla"),
    @NamedQuery(name = "Consulta.findByGlucosa", query = "SELECT c FROM Consulta c WHERE c.glucosa = :glucosa"),
    @NamedQuery(name = "Consulta.findByPresionArterial", query = "SELECT c FROM Consulta c WHERE c.presionArterial = :presionArterial"),
    @NamedQuery(name = "Consulta.findBySintomatologia", query = "SELECT c FROM Consulta c WHERE c.sintomatologia = :sintomatologia"),
    @NamedQuery(name = "Consulta.findByPrescripcionMedica", query = "SELECT c FROM Consulta c WHERE c.prescripcionMedica = :prescripcionMedica"),
//    @NamedQuery(name = "Consulta.findPacienteById", query = ""
//                        + "SELECT c FROM Consulta c"
//                        + "WHERE ("
//                        + "((:nullPaciente = TRUE ) OR (:nullPaciente = FALSE AND c.paciente =:paciente))"
//                        + ")"),
    @NamedQuery(name = "Consulta.findPacienteById", query = "SELECT c FROM Consulta c WHERE c.cita.paciente = :paciente AND c.cita.estado = 't'"),
    @NamedQuery(name = "Consulta.findByObservaciones", query = "SELECT c FROM Consulta c WHERE c.observaciones = :observaciones")})
public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private double peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "talla")
    private double talla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "glucosa")
    private double glucosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "presion_arterial")
    private double presionArterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "sintomatologia")
    private String sintomatologia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "prescripcion_medica")
    private String prescripcionMedica;
    @Size(max = 5000)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta")
    private Collection<DetalleFacturaConsulta> detalleFacturaConsultaCollection;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paciente paciente;
    @JoinColumn(name = "proxima_cita_id", referencedColumnName = "id")
    @ManyToOne
    private Cita cita;
    @JoinColumn(name = "cita_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cita cita1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta")
    private Collection<Medicacion> medicacionCollection;

    public Consulta() {
    }

    public Consulta(Integer id) {
        this.id = id;
    }

    public Consulta(Integer id, double peso, double talla, double glucosa, double presionArterial, String sintomatologia, String prescripcionMedica) {
        this.id = id;
        this.peso = peso;
        this.talla = talla;
        this.glucosa = glucosa;
        this.presionArterial = presionArterial;
        this.sintomatologia = sintomatologia;
        this.prescripcionMedica = prescripcionMedica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(double glucosa) {
        this.glucosa = glucosa;
    }

    public double getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(double presionArterial) {
        this.presionArterial = presionArterial;
    }

    public String getSintomatologia() {
        return sintomatologia;
    }

    public void setSintomatologia(String sintomatologia) {
        this.sintomatologia = sintomatologia;
    }

    public String getPrescripcionMedica() {
        return prescripcionMedica;
    }

    public void setPrescripcionMedica(String prescripcionMedica) {
        this.prescripcionMedica = prescripcionMedica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<DetalleFacturaConsulta> getDetalleFacturaConsultaCollection() {
        return detalleFacturaConsultaCollection;
    }

    public void setDetalleFacturaConsultaCollection(Collection<DetalleFacturaConsulta> detalleFacturaConsultaCollection) {
        this.detalleFacturaConsultaCollection = detalleFacturaConsultaCollection;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Cita getCita1() {
        return cita1;
    }

    public void setCita1(Cita cita1) {
        this.cita1 = cita1;
    }

    @XmlTransient
    public Collection<Medicacion> getMedicacionCollection() {
        return medicacionCollection;
    }

    public void setMedicacionCollection(Collection<Medicacion> medicacionCollection) {
        this.medicacionCollection = medicacionCollection;
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
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Consulta[ id=" + id + " ]";
    }
    
}
