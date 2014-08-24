/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import com.swii.sysmedic.entities.Medicacion;

/**
 *
 * @author LUCAS
 */
public class MedicacionView {
    
    
    private Medicacion medicacion = new Medicacion();
    
    /**
     * Creates a new instance of MedicacionView
     */
    public MedicacionView() {
    }

    public Medicacion getMedicacion() {
        if(medicacion == null)
            medicacion = new Medicacion();
        return medicacion;
    }

    public void setMedicacion(Medicacion medicacion) {
        this.medicacion = medicacion;
    }
    
    
}
