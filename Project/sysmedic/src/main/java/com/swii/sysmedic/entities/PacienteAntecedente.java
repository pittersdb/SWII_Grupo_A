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
@Table(name = "paciente_antecedente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PacienteAntecedente.findAll", query = "SELECT p FROM PacienteAntecedente p"),
    @NamedQuery(name = "PacienteAntecedente.findByPacienteId", query = "SELECT p FROM PacienteAntecedente p WHERE p.pacienteAntecedentePK.pacienteId = :pacienteId"),
    @NamedQuery(name = "PacienteAntecedente.findByAntecedenteId", query = "SELECT p FROM PacienteAntecedente p WHERE p.pacienteAntecedentePK.antecedenteId = :antecedenteId"),
    @NamedQuery(name = "PacienteAntecedente.findByValor", query = "SELECT p FROM PacienteAntecedente p WHERE p.valor = :valor")})
public class PacienteAntecedente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PacienteAntecedentePK pacienteAntecedentePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paciente paciente;
    @JoinColumn(name = "antecedente_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Antecedente antecedente;

    public PacienteAntecedente() {
    }

    public PacienteAntecedente(PacienteAntecedentePK pacienteAntecedentePK) {
        this.pacienteAntecedentePK = pacienteAntecedentePK;
    }

    public PacienteAntecedente(PacienteAntecedentePK pacienteAntecedentePK, String valor) {
        this.pacienteAntecedentePK = pacienteAntecedentePK;
        this.valor = valor;
    }

    public PacienteAntecedente(int pacienteId, int antecedenteId) {
        this.pacienteAntecedentePK = new PacienteAntecedentePK(pacienteId, antecedenteId);
    }

    public PacienteAntecedentePK getPacienteAntecedentePK() {
        return pacienteAntecedentePK;
    }

    public void setPacienteAntecedentePK(PacienteAntecedentePK pacienteAntecedentePK) {
        this.pacienteAntecedentePK = pacienteAntecedentePK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacienteAntecedentePK != null ? pacienteAntecedentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacienteAntecedente)) {
            return false;
        }
        PacienteAntecedente other = (PacienteAntecedente) object;
        if ((this.pacienteAntecedentePK == null && other.pacienteAntecedentePK != null) || (this.pacienteAntecedentePK != null && !this.pacienteAntecedentePK.equals(other.pacienteAntecedentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.PacienteAntecedente[ pacienteAntecedentePK=" + pacienteAntecedentePK + " ]";
    }
    
}
