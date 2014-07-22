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
public class MedicacionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "consulta_id")
    private int consultaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "medicamento_id")
    private int medicamentoId;

    public MedicacionPK() {
    }

    public MedicacionPK(int consultaId, int medicamentoId) {
        this.consultaId = consultaId;
        this.medicamentoId = medicamentoId;
    }

    public int getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(int medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) consultaId;
        hash += (int) medicamentoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicacionPK)) {
            return false;
        }
        MedicacionPK other = (MedicacionPK) object;
        if (this.consultaId != other.consultaId) {
            return false;
        }
        if (this.medicamentoId != other.medicamentoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.MedicacionPK[ consultaId=" + consultaId + ", medicamentoId=" + medicamentoId + " ]";
    }
    
}
