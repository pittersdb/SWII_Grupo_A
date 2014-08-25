/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.convert;


import com.swii.sysmedic.Facades.MedicamentoFacade;
import com.swii.sysmedic.Views.MedicoView;
import com.swii.sysmedic.entities.Medicamento;
import com.swii.sysmedic.entities.Medico;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter("medicamentoConverter")
public class MedicamentoConverter implements Converter {
    
    @EJB
    MedicamentoFacade medicamentoFacade;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try{
                Medicamento medicamento = medicamentoFacade.find(Integer.parseInt(value)); 
                return medicamento;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        
        
        if(object != null) {
            String id = String.valueOf(((Medicamento) object).getId());
            
            return id != null ? id: "";
            
        }
        else {
            return "";
        }
    }
}  
