/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.CitaFacade;
import javax.ejb.EJB;


/**
 *
 * @author fabian
 */

public class CitaView {

    @EJB
    private CitaFacade citaFacade;
    
    /**
     * Creates a new instance of CitaView
     */
    public CitaView() {
    }
   
    
}
