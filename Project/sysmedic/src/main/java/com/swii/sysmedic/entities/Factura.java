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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findById", query = "SELECT f FROM Factura f WHERE f.id = :id"),
    @NamedQuery(name = "Factura.findByNumero", query = "SELECT f FROM Factura f WHERE f.numero = :numero"),
    @NamedQuery(name = "Factura.findByFechaHoraPago", query = "SELECT f FROM Factura f WHERE f.fechaHoraPago = :fechaHoraPago"),
    @NamedQuery(name = "Factura.findByObservacion", query = "SELECT f FROM Factura f WHERE f.observacion = :observacion"),
    @NamedQuery(name = "Factura.findByFormaPago", query = "SELECT f FROM Factura f WHERE f.formaPago = :formaPago"),
    @NamedQuery(name = "Factura.findByDescuentoTotal", query = "SELECT f FROM Factura f WHERE f.descuentoTotal = :descuentoTotal"),
    @NamedQuery(name = "Factura.findByIva", query = "SELECT f FROM Factura f WHERE f.iva = :iva"),
    @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total"),
    @NamedQuery(name = "Factura.findByFechaAutorizacionSri", query = "SELECT f FROM Factura f WHERE f.fechaAutorizacionSri = :fechaAutorizacionSri"),
    @NamedQuery(name = "Factura.findByFechaCaducidadSri", query = "SELECT f FROM Factura f WHERE f.fechaCaducidadSri = :fechaCaducidadSri")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "forma_pago")
    private String formaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_total")
    private double descuentoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private double iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_autorizacion_sri")
    @Temporal(TemporalType.DATE)
    private Date fechaAutorizacionSri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_caducidad_sri")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidadSri;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "factura")
    private DetalleFacturaConsulta detalleFacturaConsulta;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private Collection<DetalleFacturaMedicamento> detalleFacturaMedicamentoCollection;

    public Factura() {
    }

    public Factura(Integer id) {
        this.id = id;
    }

    public Factura(Integer id, int numero, Date fechaHoraPago, String observacion, String formaPago, double descuentoTotal, double iva, double total, Date fechaAutorizacionSri, Date fechaCaducidadSri) {
        this.id = id;
        this.numero = numero;
        this.fechaHoraPago = fechaHoraPago;
        this.observacion = observacion;
        this.formaPago = formaPago;
        this.descuentoTotal = descuentoTotal;
        this.iva = iva;
        this.total = total;
        this.fechaAutorizacionSri = fechaAutorizacionSri;
        this.fechaCaducidadSri = fechaCaducidadSri;
    }
    
    public Factura(Factura source){
        this.id = source.getId();
        this.numero = source.getNumero();
        this.fechaHoraPago = source.getFechaHoraPago();
        this.observacion = source.getObservacion();
        this.formaPago = source.getFormaPago();
        this.descuentoTotal = source.getDescuentoTotal();
        this.iva = source.getIva();
        this.total = source.getTotal();
        this.fechaAutorizacionSri = source.getFechaAutorizacionSri();
        this.fechaCaducidadSri = source.getFechaCaducidadSri();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaHoraPago() {
        return fechaHoraPago;
    }

    public void setFechaHoraPago(Date fechaHoraPago) {
        this.fechaHoraPago = fechaHoraPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaAutorizacionSri() {
        return fechaAutorizacionSri;
    }

    public void setFechaAutorizacionSri(Date fechaAutorizacionSri) {
        this.fechaAutorizacionSri = fechaAutorizacionSri;
    }

    public Date getFechaCaducidadSri() {
        return fechaCaducidadSri;
    }

    public void setFechaCaducidadSri(Date fechaCaducidadSri) {
        this.fechaCaducidadSri = fechaCaducidadSri;
    }

    public DetalleFacturaConsulta getDetalleFacturaConsulta() {
        return detalleFacturaConsulta;
    }

    public void setDetalleFacturaConsulta(DetalleFacturaConsulta detalleFacturaConsulta) {
        this.detalleFacturaConsulta = detalleFacturaConsulta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public Collection<DetalleFacturaMedicamento> getDetalleFacturaMedicamentoCollection() {
        return detalleFacturaMedicamentoCollection;
    }

    public void setDetalleFacturaMedicamentoCollection(Collection<DetalleFacturaMedicamento> detalleFacturaMedicamentoCollection) {
        this.detalleFacturaMedicamentoCollection = detalleFacturaMedicamentoCollection;
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
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Factura[ id=" + id + " ]";
    }
    
}
