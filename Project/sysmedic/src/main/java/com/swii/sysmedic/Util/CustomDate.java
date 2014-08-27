/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author LUCAS
 */
public class CustomDate {

    private Date date;
    private String[] monthNames = {"Enero", "Febrero", "Marzo", "Abrill", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Deciembre"};
    
    /**
     * Creates a new instance of CustomDate
     */
    public CustomDate() {
        date = Calendar.getInstance().getTime();
    }    
    
    public CustomDate(Date date) {
        this.date = date;
    }    
    
    
    public int getDate(){        
        return date.getDate();
    }
    
    public int getYear(){        
        return date.getYear() + 1900;
    }
    
    public String getMonthName(){        
        return monthNames[date.getMonth()];
    }
    
}
