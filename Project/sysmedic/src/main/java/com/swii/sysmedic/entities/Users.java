/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LUCAS
 */
@Entity
@Table(name = "\"SysMedic\".\"Users\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByNickname", query = "SELECT u FROM Users u WHERE lower(u.nickname) = lower(:nickname)"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByApellidos", query = "SELECT u FROM Users u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Users.findByDireccion", query = "SELECT u FROM Users u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Users.findByTelefono", query = "SELECT u FROM Users u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Users.findByRol", query = "SELECT u FROM Users u WHERE u.rol = :rol"),
    @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled")})
public class Users implements Serializable, Comparable<Object>{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "rol")
    private String rol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled")
    private short enabled;
    @OneToMany(mappedBy = "users") 
    private Collection<Medico> medicoCollection;
    @Transient
    private int especialidad;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

      public Users(Integer id, String nickname, String password, String name, String apellidos, String direccion, String telefono, String rol, short enabled) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.enabled = enabled;
    }
    
    public Users(Users source) {
        this.id = source.getId();
        this.nickname = source.getNickname();
        this.password = source.getPassword();
        this.name = source.getName();
        this.apellidos = source.getApellidos();
        this.direccion = source.getDireccion();
        this.telefono = source.getTelefono();
        this.rol = source.getRol();
        this.enabled = source.getEnabled();
    }
    
    public void set(Users source){
        if(source.getId() != null && source.getId() != 0)
            this.id = source.getId();
        if(source.getNickname() != null)
            this.nickname = source.getNickname();
        if(source.getPassword() != null)
            this.password = source.getPassword();
        if(source.getName() != null)
            this.name = source.getName();
        if(source.getApellidos() != null)
            this.apellidos = source.getApellidos();
        if(source.getDireccion() != null)
            this.direccion = source.getDireccion();
        if(source.getTelefono() != null)
            this.telefono = source.getTelefono();
        if(source.getRol() != null)
            this.rol = source.getRol();
        this.enabled = source.getEnabled();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getCompleteName(){
        return this.getName() + " " + this.getApellidos();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
        public String getRolName(){
        if(rol.equalsIgnoreCase("a"))
            return "Administrador";
        if(rol.equalsIgnoreCase("S"))
            return "Secretario";
        if(rol.equalsIgnoreCase("m"))
            return "Medico";
        return "No rol";
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    @XmlTransient
    public Collection<Medico> getMedicoCollection() {
        return medicoCollection;
    }

    public void setMedicoCollection(Collection<Medico> medicoCollection) {
        this.medicoCollection = medicoCollection;
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", nickname=" + nickname + ", password=" + password + ", name=" + name + ", apellidos=" + apellidos + ", direccion=" + direccion + ", telefono=" + telefono + ", rol=" + rol + ", enabled=" + enabled + '}';
    }
//
//
    @Override
    public int compareTo(Object o) {
        Users user1 = this;
        Users user2 = (Users)o;        
        return user1.getId().compareTo(user2.getId());
    }
    
}
