/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Util;

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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
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
        
         InputStream reportStream = new FileInputStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/sysmedicCitaReport.jrxml"));
         JasperReport report = JasperCompileManager.compileReport(reportStream);
         
         ArrayList<Cita> citasCollection = new ArrayList<>();
         
         citasCollection.add(new Cita(200));
         
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
//        
                
//        String script = "window.open('" + "" + "', '_blank'";
//ExtendedRenderKitService service = Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
//service.addScript(FacesContext.getCurrentInstance(), script);
        
        
        datasource = new JRBeanCollectionDataSource(citasCollection, true);
// Print the jasper report
        JasperPrint print = JasperFillManager.fillReport(report, parametros, datasource);
        JasperPrintManager.printReport(print, false);
    }
    
}
