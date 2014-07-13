/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.UsersFacade;
import com.swii.sysmedic.entities.Users;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
@Named(value = "User")
@RequestScoped
public class UsersView {
    @EJB
    private UsersFacade usersFacade;

    /**
     * Creates a new instance of UsersView
     */
    public UsersView() {
        this.usersFacade = new UsersFacade();
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
    
    public List<Users> all(){
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
}
