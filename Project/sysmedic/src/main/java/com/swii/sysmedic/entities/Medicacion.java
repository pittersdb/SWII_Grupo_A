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
@Table(name = "\"SysMedic\".medicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicacion.findAll", query = "SELECT m FROM Medicacion m"),
    @NamedQuery(name = "Medicacion.findByConsultaId", query = "SELECT m FROM Medicacion m WHERE m.medicacionPK.consultaId = :consultaId"),
    @NamedQuery(name = "Medicacion.findByMedicamentoId", query = "SELECT m FROM Medicacion m WHERE m.medicacionPK.medicamentoId = :medicamentoId"),
    @NamedQuery(name = "Medicacion.findByInstruccion", query = "SELECT m FROM Medicacion m WHERE m.instruccion = :instruccion")})
public class Medicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicacionPK medicacionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "instruccion")
    private String instruccion;
    @JoinColumn(name = "medicamento_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medicamento medicamento;
    @JoinColumn(name = "consulta_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consulta consulta;
    
    public Medicacion() {
    }
    
    public Medicacion(MedicacionPK medicacionPK) {
        this.medicacionPK = medicacionPK;
    }
    
    public Medicacion(MedicacionPK medicacionPK, String instruccion) {
        this.medicacionPK = medicacionPK;
        this.instruccion = instruccion;
    }
    
    public Medicacion(int consultaId, int medicamentoId) {
        this.medicacionPK = new MedicacionPK(consultaId, medicamentoId);
    }
    
    public Medicacion(Medicacion medicacion) {
        this.medicacionPK = medicacion.getMedicacionPK();
        this.consulta = medicacion.getConsulta();
        this.instruccion = medicacion.getInstruccion();
        this.medicamento = medicacion.getMedicamento();
    }
    
    
    public MedicacionPK getMedicacionPK() {
        return medicacionPK;
    }
    
    public void setMedicacionPK(MedicacionPK medicacionPK) {
        this.medicacionPK = medicacionPK;
    }
    
    public String getInstruccion() {
        return instruccion;
    }
    
    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }
    
    public Medicamento getMedicamento() {
        return medicamento;
    }
    
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
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
        hash += (medicacionPK != null ? medicacionPK.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicacion)) {
            return false;
        }
        Medicacion other = (Medicacion) object;
        if ((this.medicacionPK == null && other.medicacionPK != null) || (this.medicacionPK != null && !this.medicacionPK.equals(other.medicacionPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Medicacion[ medicacionPK=" + medicacionPK + " ]";
    }
    
    public void Clear(){
        this.medicacionPK = null;
        this.instruccion = null;
        this.medicamento = null;
        this. consulta = null;
    }
    
}
