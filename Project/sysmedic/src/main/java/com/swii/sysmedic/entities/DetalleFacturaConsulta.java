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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".detalle_factura_consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFacturaConsulta.findAll", query = "SELECT d FROM DetalleFacturaConsulta d"),
    @NamedQuery(name = "DetalleFacturaConsulta.findById", query = "SELECT d FROM DetalleFacturaConsulta d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleFacturaConsulta.findByDescuento", query = "SELECT d FROM DetalleFacturaConsulta d WHERE d.descuento = :descuento"),
    @NamedQuery(name = "DetalleFacturaConsulta.findByTotal", query = "SELECT d FROM DetalleFacturaConsulta d WHERE d.total = :total")})
public class DetalleFacturaConsulta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento")
    private int descuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;
    @JoinColumn(name = "tarifario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tarifario tarifario;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Factura factura;
    @JoinColumn(name = "consulta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Consulta consulta;

    public DetalleFacturaConsulta() {
    }

    public DetalleFacturaConsulta(Integer id) {
        this.id = id;
    }

    public DetalleFacturaConsulta(Integer id, int descuento, double total) {
        this.id = id;
        this.descuento = descuento;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Tarifario getTarifario() {
        return tarifario;
    }

    public void setTarifario(Tarifario tarifario) {
        this.tarifario = tarifario;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
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
        if (!(object instanceof DetalleFacturaConsulta)) {
            return false;
        }
        DetalleFacturaConsulta other = (DetalleFacturaConsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.DetalleFacturaConsulta[ id=" + id + " ]";
    }
    
}
