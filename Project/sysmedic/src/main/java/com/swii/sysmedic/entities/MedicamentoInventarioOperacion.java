/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".medicamento_inventario_operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoInventarioOperacion.findAll", query = "SELECT m FROM MedicamentoInventarioOperacion m"),
    @NamedQuery(name = "MedicamentoInventarioOperacion.findByMedicamentoLoteId", query = "SELECT m FROM MedicamentoInventarioOperacion m WHERE m.medicamentoInventarioOperacionPK.medicamentoLoteId = :medicamentoLoteId"),
    @NamedQuery(name = "MedicamentoInventarioOperacion.findByUsersId", query = "SELECT m FROM MedicamentoInventarioOperacion m WHERE m.medicamentoInventarioOperacionPK.usersId = :usersId"),
    @NamedQuery(name = "MedicamentoInventarioOperacion.findByOperacion", query = "SELECT m FROM MedicamentoInventarioOperacion m WHERE m.operacion = :operacion"),
    @NamedQuery(name = "MedicamentoInventarioOperacion.findByCantidadInvolucrada", query = "SELECT m FROM MedicamentoInventarioOperacion m WHERE m.cantidadInvolucrada = :cantidadInvolucrada")})
public class MedicamentoInventarioOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicamentoInventarioOperacionPK medicamentoInventarioOperacionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "operacion")
    private String operacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_involucrada")
    private int cantidadInvolucrada;
    @JoinColumn(name = "medicamento_lote_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedicamentoLote medicamentoLote;
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public MedicamentoInventarioOperacion() {
    }

    public MedicamentoInventarioOperacion(MedicamentoInventarioOperacionPK medicamentoInventarioOperacionPK) {
        this.medicamentoInventarioOperacionPK = medicamentoInventarioOperacionPK;
    }

    public MedicamentoInventarioOperacion(MedicamentoInventarioOperacionPK medicamentoInventarioOperacionPK, String operacion, int cantidadInvolucrada) {
        this.medicamentoInventarioOperacionPK = medicamentoInventarioOperacionPK;
        this.operacion = operacion;
        this.cantidadInvolucrada = cantidadInvolucrada;
    }

    public MedicamentoInventarioOperacion(int medicamentoLoteId, int usersId) {
        this.medicamentoInventarioOperacionPK = new MedicamentoInventarioOperacionPK(medicamentoLoteId, usersId);
    }

    public MedicamentoInventarioOperacionPK getMedicamentoInventarioOperacionPK() {
        return medicamentoInventarioOperacionPK;
    }

    public void setMedicamentoInventarioOperacionPK(MedicamentoInventarioOperacionPK medicamentoInventarioOperacionPK) {
        this.medicamentoInventarioOperacionPK = medicamentoInventarioOperacionPK;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getCantidadInvolucrada() {
        return cantidadInvolucrada;
    }

    public void setCantidadInvolucrada(int cantidadInvolucrada) {
        this.cantidadInvolucrada = cantidadInvolucrada;
    }

    public MedicamentoLote getMedicamentoLote() {
        return medicamentoLote;
    }

    public void setMedicamentoLote(MedicamentoLote medicamentoLote) {
        this.medicamentoLote = medicamentoLote;
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
        hash += (medicamentoInventarioOperacionPK != null ? medicamentoInventarioOperacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoInventarioOperacion)) {
            return false;
        }
        MedicamentoInventarioOperacion other = (MedicamentoInventarioOperacion) object;
        if ((this.medicamentoInventarioOperacionPK == null && other.medicamentoInventarioOperacionPK != null) || (this.medicamentoInventarioOperacionPK != null && !this.medicamentoInventarioOperacionPK.equals(other.medicamentoInventarioOperacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.MedicamentoInventarioOperacion[ medicamentoInventarioOperacionPK=" + medicamentoInventarioOperacionPK + " ]";
    }
    
}
