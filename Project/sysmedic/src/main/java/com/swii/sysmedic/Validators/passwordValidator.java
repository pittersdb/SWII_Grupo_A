/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Validators;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

//import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fabian
 */

@FacesValidator(value = "passwordValidator")
public class passwordValidator implements Validator {

@Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Si el valor es null, lo transformamos en un valor vacío
        //String valor = StringUtils.defaultString((String)value);
        // el valor debe tener 9 posiciones, de las cuales las primeras deben ser dígitos y la última letra
//        String valor = String 
//        valor=valor.toUpperCase();
//        Pattern mask =  Pattern.compile("[0-9]{8,8}[A-Z]");
//        Matcher matcher = mask.matcher(valor);
//        if(!matcher.matches()) 
//            throw new ValidatorException(new FacesMessage("El componente " + component.getId() + "no contiene un NIF válido. Las 8 primeras posiciones deben ser numéricas"));        
//        String dni=valor.substring(0,8);
//        String digitoControl = valor.substring(8,9);
//        // Calculamos la letra de control
//        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
//        int posicion_modulo = Integer.parseInt(dni)%23;
//        String digitoControlCalculado = letras.substring(posicion_modulo,posicion_modulo+1);
//        if(!digitoControl.equalsIgnoreCase(digitoControlCalculado))
//            throw new ValidatorException(new FacesMessage("El componente " + component.getId() + " no contiene un NIF válido"));                  
    }
}