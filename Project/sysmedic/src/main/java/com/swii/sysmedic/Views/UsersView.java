/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.UsersFacade;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.jasig.cas.client.authentication.*;
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
}
