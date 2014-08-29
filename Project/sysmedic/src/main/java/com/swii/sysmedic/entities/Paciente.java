/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabian
 */
@Entity
@Table(name = "\"SysMedic\".paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findById", query = "SELECT p FROM Paciente p WHERE p.id = :id"),
    @NamedQuery(name = "Paciente.findByNombres", query = "SELECT p FROM Paciente p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Paciente.findByApellidos", query = "SELECT p FROM Paciente p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Paciente.findByCi", query = "SELECT p FROM Paciente p WHERE p.ci = :ci"),
    @NamedQuery(name = "Paciente.findByFechaNacimiento", query = "SELECT p FROM Paciente p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Paciente.findByLugarProcedencia", query = "SELECT p FROM Paciente p WHERE p.lugarProcedencia = :lugarProcedencia"),
    @NamedQuery(name = "Paciente.findConsultas", query = "SELECT c FROM Consulta c WHERE c.cita.paciente = :paciente"),
    @NamedQuery(name = "Paciente.findByDirecion", query = "SELECT p FROM Paciente p WHERE p.direcion = :direcion")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ci")
    private String ci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lugar_procedencia")
    private String lugarProcedencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direcion") 
    private String direcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private Collection<Cita> citaCollection; 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", orphanRemoval = true)
    private Collection<PacienteAntecedente> pacienteAntecedenteCollection;

    public Paciente() {
    }
    
    public Paciente(Paciente paciente){
        this.id = paciente.getId();
        this.nombres = paciente.getNombres();
        this.apellidos = paciente.getApellidos();
        this.ci = paciente.getCi();
        this.direcion = paciente.getDirecion();
        this.lugarProcedencia = paciente.getLugarProcedencia();
        this.fechaNacimiento = paciente.getFechaNacimiento();
        this.sexo = paciente.getSexo();
    }
    
    public void set(Paciente paciente){
        this.id = paciente.getId();
        this.nombres = paciente.getNombres();
        this.apellidos = paciente.getApellidos();
        this.ci = paciente.getCi();
        this.direcion = paciente.getDirecion();
        this.lugarProcedencia = paciente.getLugarProcedencia();
        this.fechaNacimiento = paciente.getFechaNacimiento();
        this.sexo = paciente.getSexo();
    }
    
    public void Clear(){
        this.id = null;
        this.nombres = null;
        this.apellidos = null;
        this.ci = null;
        this.direcion = null;
        this.lugarProcedencia = null;
        this.fechaNacimiento = null;
        this.sexo = null;
    }

    public Paciente(Integer id) {
        this.id = id;
    }

    public Paciente(Integer id, String nombres, String apellidos, String ci, Date fechaNacimiento, String sexo, String lugarProcedencia, String direcion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.lugarProcedencia = lugarProcedencia;
        this.direcion = direcion;
    }
    
    public void set(Paciente paciente) {
        this.id = paciente.getId();
        this.nombres = paciente.getNombres();
        this.apellidos = paciente.getApellidos();
        this.ci = paciente.getCi();
        this.fechaNacimiento = paciente.getFechaNacimiento();
        this.sexo = paciente.getSexo();
        this.lugarProcedencia = paciente.getLugarProcedencia();
        this.direcion = paciente.getDirecion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLugarProcedencia() {
        return lugarProcedencia;
    }

    public void setLugarProcedencia(String lugarProcedencia) {
        this.lugarProcedencia = lugarProcedencia;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @XmlTransient
    public Collection<PacienteAntecedente> getPacienteAntecedenteCollection() {
        return pacienteAntecedenteCollection;
    }

    public void setPacienteAntecedenteCollection(Collection<PacienteAntecedente> pacienteAntecedenteCollection) {
        this.pacienteAntecedenteCollection = pacienteAntecedenteCollection;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.swii.sysmedic.entities.Paciente[ id=" + id + " ]";
    }
    
}
