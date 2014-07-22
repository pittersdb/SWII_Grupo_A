/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fabian
 */
@Embeddable
public class MedicamentoInventarioOperacionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "medicamento_lote_id")
    private int medicamentoLoteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "users_id")
    private int usersId;

    public MedicamentoInventarioOperacionPK() {
    }

    public MedicamentoInventarioOperacionPK(int medicamentoLoteId, int usersId) {
        this.medicamentoLoteId = medicamentoLoteId;
        this.usersId = usersId;
    }

    public int getMedicamentoLoteId() {
        return medicamentoLoteId;
    }

    public void setMedicamentoLoteId(int medicamentoLoteId) {
        this.medicamentoLoteId = medicamentoLoteId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) medicamentoLoteId;
        hash += (int) usersId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoInventarioOperacionPK)) {
            return false;
        }
        MedicamentoInventarioOperacionPK other = (MedicamentoInventarioOperacionPK) object;
        if (this.medicamentoLoteId != other.medicamentoLoteId) {
            return false;
        }
        if (this.usersId != other.usersId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.MedicamentoInventarioOperacionPK[ medicamentoLoteId=" + medicamentoLoteId + ", usersId=" + usersId + " ]";
    }
    
}
