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
public class PacienteAntecedentePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "paciente_id")
    private int pacienteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "antecedente_id")
    private int antecedenteId;

    public PacienteAntecedentePK() {
    }

    public PacienteAntecedentePK(int pacienteId, int antecedenteId) {
        this.pacienteId = pacienteId;
        this.antecedenteId = antecedenteId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getAntecedenteId() {
        return antecedenteId;
    }

    public void setAntecedenteId(int antecedenteId) {
        this.antecedenteId = antecedenteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pacienteId;
        hash += (int) antecedenteId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacienteAntecedentePK)) {
            return false;
        }
        PacienteAntecedentePK other = (PacienteAntecedentePK) object;
        if (this.pacienteId != other.pacienteId) {
            return false;
        }
        if (this.antecedenteId != other.antecedenteId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.PacienteAntecedentePK[ pacienteId=" + pacienteId + ", antecedenteId=" + antecedenteId + " ]";
    }
    
}
