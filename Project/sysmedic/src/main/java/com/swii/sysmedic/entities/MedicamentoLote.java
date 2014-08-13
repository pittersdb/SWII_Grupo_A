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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".\"medicamento_lote\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoLote.findAll", query = "SELECT m FROM MedicamentoLote m"),
    @NamedQuery(name = "MedicamentoLote.findById", query = "SELECT m FROM MedicamentoLote m WHERE m.id = :id"),
    @NamedQuery(name = "MedicamentoLote.findByCodigoLote", query = "SELECT m FROM MedicamentoLote m WHERE m.codigoLote = :codigoLote"),
    @NamedQuery(name = "MedicamentoLote.findByFechaElaboracion", query = "SELECT m FROM MedicamentoLote m WHERE m.fechaElaboracion = :fechaElaboracion"),
    @NamedQuery(name = "MedicamentoLote.findByFechaCaducidad", query = "SELECT m FROM MedicamentoLote m WHERE m.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "MedicamentoLote.findByFechaIngreso", query = "SELECT m FROM MedicamentoLote m WHERE m.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "MedicamentoLote.findByCantidadDisponible", query = "SELECT m FROM MedicamentoLote m WHERE m.cantidadDisponible = :cantidadDisponible"),
    @NamedQuery(name = "MedicamentoLote.findByPrecio", query = "SELECT m FROM MedicamentoLote m WHERE m.precio = :precio"),
    @NamedQuery(name = "MedicamentoLote.findByEstado", query = "SELECT m FROM MedicamentoLote m WHERE m.estado = :estado"),
    @NamedQuery(name = "MedicamentoLote.findByMedicamento", query = "SELECT m FROM MedicamentoLote m WHERE m.medicamento.id = :medicamentoId"),
    @NamedQuery(name = "MedicamentoLote.findByMedicamentoAndLote", query = "SELECT m FROM MedicamentoLote m WHERE m.medicamento.id = :medicamentoId AND m.codigoLote = :codigoLote")
})
public class MedicamentoLote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_lote")
    private long codigoLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_elaboracion")
    @Temporal(TemporalType.DATE)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamentoLote")
    private Collection<DetalleFacturaMedicamento> detalleFacturaMedicamentoCollection;
    @JoinColumn(name = "medicamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medicamento medicamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicamentoLote")
    private Collection<MedicamentoInventarioOperacion> medicamentoInventarioOperacionCollection;

    public MedicamentoLote() {
    }

    public MedicamentoLote(MedicamentoLote source) {
        this.id = source.getId();
        this.codigoLote = source.getCodigoLote();
        this.fechaElaboracion = source.getFechaElaboracion();
        this.fechaCaducidad = source.getFechaCaducidad();
        this.fechaIngreso = source.getFechaIngreso();
        this.cantidadDisponible = source.getCantidadDisponible();
        this.precio = source.getPrecio();
        this.estado = source.getEstado();
    }
    
    public MedicamentoLote(Integer id) {
        this.id = id;
    }

    public MedicamentoLote(Integer id, long codigoLote, Date fechaElaboracion, Date fechaCaducidad, Date fechaIngreso, int cantidadDisponible, double precio, String estado) {
        this.id = id;
        this.codigoLote = codigoLote;
        this.fechaElaboracion = fechaElaboracion;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaIngreso = fechaIngreso;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(long codigoLote) {
        this.codigoLote = codigoLote;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<DetalleFacturaMedicamento> getDetalleFacturaMedicamentoCollection() {
        return detalleFacturaMedicamentoCollection;
    }

    public void setDetalleFacturaMedicamentoCollection(Collection<DetalleFacturaMedicamento> detalleFacturaMedicamentoCollection) {
        this.detalleFacturaMedicamentoCollection = detalleFacturaMedicamentoCollection;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @XmlTransient
    public Collection<MedicamentoInventarioOperacion> getMedicamentoInventarioOperacionCollection() {
        return medicamentoInventarioOperacionCollection;
    }

    public void setMedicamentoInventarioOperacionCollection(Collection<MedicamentoInventarioOperacion> medicamentoInventarioOperacionCollection) {
        this.medicamentoInventarioOperacionCollection = medicamentoInventarioOperacionCollection;
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
        if (!(object instanceof MedicamentoLote)) {
            return false;
        }
        MedicamentoLote other = (MedicamentoLote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.MedicamentoLote[ id=" + id + " ]";
    }
    
}
