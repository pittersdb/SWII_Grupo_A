/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Interfaces;

/**
 *
 * @author LUCAS
 */

import com.thoughtworks.selenium.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public class UsersInterfaceTest  {
    
    //http://javadude.wordpress.com/2010/03/08/quick-tutorial-netbeans-selenium-hudson/
    public static void main(String[] args) {
        UsersInterfaceTest test = new UsersInterfaceTest();
        try {
            test.testIngreso();
        } catch (Exception ex) {
            Logger.getLogger(UsersInterfaceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Ignore
    public void testIngreso() throws Exception {
        String appServerURL = "https://localhost:8181/";
        RemoteControlConfiguration serverConfig = new RemoteControlConfiguration();
        serverConfig.setPort(4444);
        serverConfig.setTrustAllSSLCertificates(true);
        SeleniumServer server = new SeleniumServer(serverConfig);
        
        server.start();
        
        try{
            
            Selenium selBrowser = new DefaultSelenium("localhost", 4444, "*firefox", appServerURL);
            selBrowser.start();
            
            selBrowser.setSpeed("2000");
            selBrowser.open("/sysmedic/");
            selBrowser.type("username", "gchavez");
            selBrowser.type("password", "mypassword1");
            selBrowser.click("//input[@name='submit']");
            selBrowser.setSpeed("5000");
            selBrowser.click("//a[@onclick='mostrarUsuarios()']");
            selBrowser.setSpeed("2000");
            selBrowser.click("//button[@id='formUser:createUsuarioButton']");
            
            selBrowser.type("//input[@id='frmNuevoUsuario:nombre']", "Katherine");
            selBrowser.type("//input[@id='frmNuevoUsuario:apellido']", "Cisneros");
            selBrowser.type("//input[@id='frmNuevoUsuario:direccion']", "Calle 7 Ave. 8");
            selBrowser.type("//input[@id='frmNuevoUsuario:telefono']", "099996652");
            selBrowser.type("//input[@id='frmNuevoUsuario:nickname']", "kcisneros");
            selBrowser.type("//input[@id='frmNuevoUsuario:password']", "kcisneros");
            selBrowser.type("//input[@id='frmNuevoUsuario:repeatPassword']", "kcisneros");
            
            selBrowser.click("//button[@id='frmNuevoUsuario:btnGuardar']");
            
            selBrowser.setSpeed("3000");
            
            selBrowser.click("//button[@id='frmModificarPaciente:j_idt560']");
            selBrowser.click("//button[@id='frmModificarPaciente:j_idt564']");            
            
            selBrowser.setSpeed("5000");
            selBrowser.close();
            
            selBrowser.stop();
            server.stop();
        }catch(Exception e){
            server.stop();
            Logger.getLogger(UsersInterfaceTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}