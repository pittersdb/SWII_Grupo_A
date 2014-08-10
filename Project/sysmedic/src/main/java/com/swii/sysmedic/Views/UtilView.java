/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Views;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author LUCAS
 */
public class UtilView {

    /**
     * Creates a new instance of UtilView
     */
    public UtilView() {
    }
    
    /**
* selected date To
*/
private Date selectedDateTo = Calendar.getInstance().getTime();

/**
 * getter
     * @return 
 */
public Date getSelectedDateTo() {
    return selectedDateTo;
}

/**
 * setter
     * @param selectedDateTo
 */
public void setSelectedDateTo(Date selectedDateTo) {
    this.selectedDateTo = selectedDateTo;
}
    
}
