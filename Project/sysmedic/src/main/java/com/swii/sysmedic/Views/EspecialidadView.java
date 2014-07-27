/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.EspecialidadFacade;
import com.swii.sysmedic.entities.Especialidad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author LUCAS
 */
@Named(value = "Especialidad")
@RequestScoped
public class EspecialidadView {
    
    @EJB
    private EspecialidadFacade especialidadFacade;
    private List<Especialidad> all = new ArrayList<Especialidad>();
    
    /**
     * Creates a new instance of EspecialidadView
     */
    public EspecialidadView() {
        this.especialidadFacade = new EspecialidadFacade();
    }
    
    public List<Especialidad> getAll() {
        if(all.isEmpty())
            all = this.especialidadFacade.findAll();
        return all;
    }
    
    public void setAll(List<Especialidad> all) {
        this.all = all;
    }
    
    
    
}
