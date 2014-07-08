/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 
@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{
 
	private static final long serialVersionUID = 1L;
 
	private String localeCode;
 
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
                countries.put("Spanish", new Locale("es"));
		countries.put("English", Locale.ENGLISH); //label, value
		
	}
 
	public Map<String, Object> getCountriesInMap() {
		return countries;
	}
 
 
	public String getLocaleCode() {
		return localeCode;
	}
        
        
        
        public void setLocaleCodeBySufix(String code) {
		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale(new Locale(code));
	}
        
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
        public void setDefaultLocaleCode() {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
	}
        
        
 
	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e){
                if(e == null) return;
		String newLocaleValue = e.getNewValue().toString();
 
		//loop country map to compare the locale code
                for (Map.Entry<String, Object> entry : countries.entrySet()) {
 
        	   if(entry.getValue().toString().equals(newLocaleValue)){
 
        		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale((Locale)entry.getValue());
 
        	  }
               }
	}
 
}