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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m"),
    @NamedQuery(name = "Medico.findById", query = "SELECT m FROM Medico m WHERE m.id = :id"),
    @NamedQuery(name = "Medico.findByName", query = "SELECT m FROM Medico m JOIN Users u WHERE m.id = u.id AND (lower(u.name) LIKE :name OR lower(u.apellidos) LIKE :apellidos)")})
public class Medico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
    private Collection<Cita> citaCollection;
    @JoinColumn(name = "especialidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Especialidad especialidad;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Medico() {
    }

    public Medico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
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
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Medico[ id=" + id + " ]";
    }
    
}
