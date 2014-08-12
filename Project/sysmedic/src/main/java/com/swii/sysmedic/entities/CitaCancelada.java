/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabian
 */
@Entity
@Table(name =  "\"SysMedic\".cita_cancelada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaCancelada.findAll", query = "SELECT c FROM CitaCancelada c"),
    @NamedQuery(name = "CitaCancelada.findById", query = "SELECT c FROM CitaCancelada c WHERE c.id = :id"),
    @NamedQuery(name = "CitaCancelada.findByFechaConsultaEsperada", query = "SELECT c FROM CitaCancelada c WHERE c.fechaConsultaEsperada = :fechaConsultaEsperada"),
    @NamedQuery(name = "CitaCancelada.findByFechaCancelacion", query = "SELECT c FROM CitaCancelada c WHERE c.fechaCancelacion = :fechaCancelacion")})
public class CitaCancelada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_consulta_esperada")
    @Temporal(TemporalType.DATE)
    private Date fechaConsultaEsperada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCancelacion;
    @JoinColumn(name = "cita_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cita cita;

    public CitaCancelada() {
    }

    public CitaCancelada(Integer id) {
        this.id = id;
    }

    public CitaCancelada(Integer id, Date fechaConsultaEsperada, Date fechaCancelacion) {
        this.id = id;
        this.fechaConsultaEsperada = fechaConsultaEsperada;
        this.fechaCancelacion = fechaCancelacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaConsultaEsperada() {
        return fechaConsultaEsperada;
    }

    public void setFechaConsultaEsperada(Date fechaConsultaEsperada) {
        this.fechaConsultaEsperada = fechaConsultaEsperada;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
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
        if (!(object instanceof CitaCancelada)) {
            return false;
        }
        CitaCancelada other = (CitaCancelada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.CitaCancelada[ id=" + id + " ]";
    }
    
}
