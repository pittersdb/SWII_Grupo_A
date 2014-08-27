/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Util;

import com.swii.sysmedic.Reports.CitaReportBean;
import com.swii.sysmedic.entities.Cita;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author LUCAS
 */
public class Report {
    
    /**
     * Creates a new instance of Report
     */
    public Report() {
    }
    
    public void imprimirCita( Cita cita) throws JRException, IOException {
        
        CitaReportBean citaReport = new CitaReportBean();
        
        citaReport.setCiPaciente(cita.getPaciente().getCi());
        citaReport.setNombrePaciente(cita.getPaciente().getNombres() + " " +cita.getPaciente().getApellidos());
        citaReport.setNombreMedico(cita.getMedico().getUsers().getName() +" " +cita.getMedico().getUsers().getApellidos());
        citaReport.setEspecialidadMedico(cita.getMedico().getEspecialidad().getNombre());
        CustomDate customDate = new CustomDate(cita.getFechaConsultaActual());
        citaReport.setFechaCita(customDate.getDate() + " de " + customDate.getMonthName() + " de " +customDate.getYear());
        
        
        InputStream reportStream = new FileInputStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/sysmedicCitaReport.jrxml"));
        JasperReport report = JasperCompileManager.compileReport(reportStream);
        
        ArrayList<CitaReportBean> citasCollection = new ArrayList<>();
        
        citasCollection.add(citaReport);
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("txtUsuario", "Jefferson Valdez");
        //File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/sysmedicCitaReport.jrxml"));
        JRDataSource datasource = new JRBeanCollectionDataSource(citasCollection, false);
        byte[] bytes = JasperRunManager.runReportToPdf(report, parametros, datasource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        datasource = new JRBeanCollectionDataSource(citasCollection, true);
        
        // Print the jasper report
        JasperPrint print = JasperFillManager.fillReport(report, parametros, datasource);
        JasperPrintManager.printReport(print, false);
    }
    
}
