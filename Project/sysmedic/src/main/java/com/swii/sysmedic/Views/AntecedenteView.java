/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.AntecedenteFacade;
import com.swii.sysmedic.entities.AntecedentesGroup;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;


/**
 *
 * @author LUCAS
 */
public class AntecedenteView {
    
     @EJB
    private AntecedenteFacade antecedenteFacade;
    
    private AntecedentesGroup antecedentesGroup;
    
    /**
     * Creates a new instance of AntecedenteView
     */
    public AntecedenteView() {
    }
    
    @PostConstruct
    public void init() {
        antecedentesGroup = new AntecedentesGroup(antecedenteFacade);
    }
    
    public void Instatiate(){
        antecedentesGroup = new AntecedentesGroup(antecedenteFacade);
    }

    public AntecedentesGroup getAntecedentesGroup() {
        return antecedentesGroup;
    }

    public void setAntecedentesGroup(AntecedentesGroup antecedentesGroup) {
        this.antecedentesGroup = antecedentesGroup;
    }
    
    
}
