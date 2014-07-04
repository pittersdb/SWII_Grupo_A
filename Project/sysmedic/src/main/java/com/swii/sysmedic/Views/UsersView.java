/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.UsersFacade;
import com.swii.sysmedic.entities.Users;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
    
    public String Name(){
        FacesContext facesContext = FacesContext.getCurrentInstance();       
        return this.usersFacade.GetUser(facesContext.getExternalContext().getRemoteUser()).getName();
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
}
