/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "detalle_factura_medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFacturaMedicamento.findAll", query = "SELECT d FROM DetalleFacturaMedicamento d"),
    @NamedQuery(name = "DetalleFacturaMedicamento.findById", query = "SELECT d FROM DetalleFacturaMedicamento d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleFacturaMedicamento.findByCantidad", query = "SELECT d FROM DetalleFacturaMedicamento d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleFacturaMedicamento.findByPrecioUnitario", query = "SELECT d FROM DetalleFacturaMedicamento d WHERE d.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "DetalleFacturaMedicamento.findByDescuento", query = "SELECT d FROM DetalleFacturaMedicamento d WHERE d.descuento = :descuento"),
    @NamedQuery(name = "DetalleFacturaMedicamento.findByPrecioTotal", query = "SELECT d FROM DetalleFacturaMedicamento d WHERE d.precioTotal = :precioTotal")})
public class DetalleFacturaMedicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unitario")
    private double precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento")
    private int descuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_total")
    private double precioTotal;
    @JoinColumn(name = "medicamento_lote_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MedicamentoLote medicamentoLote;
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Factura factura;

    public DetalleFacturaMedicamento() {
    }

    public DetalleFacturaMedicamento(Integer id) {
        this.id = id;
    }

    public DetalleFacturaMedicamento(Integer id, int cantidad, double precioUnitario, int descuento, double precioTotal) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.precioTotal = precioTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public MedicamentoLote getMedicamentoLote() {
        return medicamentoLote;
    }

    public void setMedicamentoLote(MedicamentoLote medicamentoLote) {
        this.medicamentoLote = medicamentoLote;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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
        if (!(object instanceof DetalleFacturaMedicamento)) {
            return false;
        }
        DetalleFacturaMedicamento other = (DetalleFacturaMedicamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.DetalleFacturaMedicamento[ id=" + id + " ]";
    }
    
}
