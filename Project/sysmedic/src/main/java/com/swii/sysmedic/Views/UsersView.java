/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicoFacade;
import com.swii.sysmedic.Facades.UsersFacade;
import com.swii.sysmedic.entities.Especialidad;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
/**
 *
 * @author LUCAS
 */

public class UsersView {
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private MedicoFacade medicFacade;
    private Users user = new Users();
    private String selectedRol;
    private int selectedEspecialidad;
    private List<Users> all = new ArrayList<Users>();
    
    /**
     * Creates a new instance of UsersView
     */
    
    @PostConstruct
    public void init() {
        all.addAll(allFromDB());
        Collections.sort(all);
    }
    
    public UsersView() {
        this.usersFacade = new UsersFacade();
    }
    
    public int getSelectedEspecialidad() {
        return selectedEspecialidad;
    }
    
    public void setSelectedEspecialidad(int selectedEspecialidad) {
        this.selectedEspecialidad = selectedEspecialidad;
    }
    
    public void LoadUser(String nickname){
        this.user = this.usersFacade.GetUser(nickname);
        this.selectedRol = this.user.getRol();
        if(selectedRol.equals("m")){
            selectedEspecialidad = this.medicFacade.find(this.user.getId()).getEspecialidad().getId();
        }
    }
    
    public Users getUser(){
        return this.user;
    }
    
    public String getUserName(int id){
        //int ident = Integer.parseInt(id);
        Users u = this.usersFacade.find(id);
        return u.getName() + " " +u.getApellidos();
    }
    
    public String getSelectedRol() {
        return selectedRol;
    }
    
    public void setSelectedRol(String selectedRol) {
        this.selectedRol = selectedRol;
    }
    
    public void Test(String test){
        System.out.println("TEST: "+test);
    }
    
    public void Save(){
        boolean existsUser =  this.usersFacade.GetUser(user.getNickname()) != null;
        try{
            if(!existsUser){
                user.setRol(selectedRol);
                user.setEnabled((short)1);
                if(!selectedRol.equalsIgnoreCase("m")){
                    this.usersFacade.create(user);
                }else{
                    this.usersFacade.create(user);
                    try{
                        Medico newMedico = new Medico();
                        newMedico.setId(user.getId());
                        newMedico.setEspecialidad(new Especialidad(selectedEspecialidad));
                        newMedico.setUsers(user);
                        this.medicFacade.create(newMedico);
                    }catch(Exception eMedic){
                        this.usersFacade.remove(user);
                    }
                }
                this.all.add(new Users(user));
                this.Clear();
            }else{
                FacesContext.getCurrentInstance().validationFailed();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "El usuario "+user.getNickname()+" ya existe, por favor elija otro."));
                
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void Update(){
        try{
            Users originalUser = this.usersFacade.UpdateWithConstraints(user, selectedRol, selectedEspecialidad, medicFacade);
            int index = this.all.indexOf(originalUser);
            this.all.get(index).set(originalUser);
            this.Clear();
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void Delete(int id){
        try{
            Users userToDelete = this.usersFacade.find(id);
            this.usersFacade.remove( userToDelete);
            this.all.remove(userToDelete);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void destroyWorld(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Empty fields!", "Insert something in at least one input!"));
        
        System.out.println("HOLA");
    }
    
    public String Hola(){
        return "hola";
    }
    
    public String Name(){
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //return this.usersFacade.GetUser(facesContext.getExternalContext().getRemoteUser()).getName();
        //return "Hola";
        
        UserDetails userDetails = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return this.usersFacade.GetUser(userDetails.getUsername()).getName();
        }else {
            return "none";
        }
        
        
    }
    
    public List<Users> getAll() {
        if(all.isEmpty()){
            all.addAll(allFromDB());
            Collections.sort(all);
        }
        return all;
    }
    
    public void setAll(List<Users> all) {
        this.all = all;
    }
    
    public List<Users> allFromDB(){
        return this.usersFacade.findAll();
    }
    
    public void LogOut(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        try {
            String serverName = System.getProperty("server.https.name");
            externalContext.redirect( serverName + "/cas-server-webapp/logout" + "?destination="+serverName+"/sysmedic");
        } catch (IOException ex) {
            Logger.getLogger(UsersView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void ShowSessionAttributes(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = req.getSession();
        Enumeration keys = session.getAttributeNames();
        System.out.println("Atributos: ");
        while (keys.hasMoreElements())
        {
            String key = (String)keys.nextElement();
            System.out.println(key + ": " + session.getAttribute(key) + "<br>");
        }
    }
    
    private void Clear(){
        user.setName(null);
        user.setApellidos(null);
        user.setNickname(null);
        user.setPassword(null);
        user.setDireccion(null);
        user.setTelefono(null);
    }
}
