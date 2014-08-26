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
@Table(name = "\"SysMedic\".tarifario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifario.findAll", query = "SELECT t FROM Tarifario t"),
    @NamedQuery(name = "Tarifario.findById", query = "SELECT t FROM Tarifario t WHERE t.id = :id"),
    @NamedQuery(name = "Tarifario.findByNombreServicio", query = "SELECT t FROM Tarifario t WHERE (lower(t.nombreServicio) LIKE :nombreServicio)"),
    @NamedQuery(name = "Tarifario.findByPrecio", query = "SELECT t FROM Tarifario t WHERE t.precio = :precio")})
public class Tarifario implements Serializable {//(lower(m.users.name) LIKE :name OR lower(m.users.apellidos) LIKE :apellidos)
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_servicio")
    private String nombreServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifario")
    private Collection<DetalleFacturaConsulta> detalleFacturaConsultaCollection;

    public Tarifario() {
    }

    public Tarifario(Integer id) {
        this.id = id;
    }

    public Tarifario(Integer id, String nombreServicio, double precio) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<DetalleFacturaConsulta> getDetalleFacturaConsultaCollection() {
        return detalleFacturaConsultaCollection;
    }

    public void setDetalleFacturaConsultaCollection(Collection<DetalleFacturaConsulta> detalleFacturaConsultaCollection) {
        this.detalleFacturaConsultaCollection = detalleFacturaConsultaCollection;
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
        if (!(object instanceof Tarifario)) {
            return false;
        }
        Tarifario other = (Tarifario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Tarifario[ id=" + id + " ]";
    }
    
}
