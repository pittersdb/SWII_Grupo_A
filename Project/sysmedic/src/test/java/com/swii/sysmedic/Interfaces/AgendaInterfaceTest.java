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

import com.swii.sysmedic.Util.Config;
import com.thoughtworks.selenium.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public class AgendaInterfaceTest  {
    //http://javadude.wordpress.com/2010/03/08/quick-tutorial-netbeans-selenium-hudson/
    public static void main(String[] args) {
        AgendaInterfaceTest test = new AgendaInterfaceTest();
        try {
            test.testSimple();
        } catch (Exception ex) {
            Logger.getLogger(AgendaInterfaceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Ignore
    public void testSimple() throws Exception {
         String appServerURL = "https://localhost:8181/";
        RemoteControlConfiguration serverConfig = new RemoteControlConfiguration();
        serverConfig.setPort(4444);
        serverConfig.setTrustAllSSLCertificates(true);
        SeleniumServer server = new SeleniumServer(serverConfig);
        
            server.start();
        
        Selenium selBrowser = new DefaultSelenium("localhost", 4444, "*googlechrome", appServerURL);
        selBrowser.start();
        // optional to make it visible/slower:
        
        selBrowser.setSpeed("2000");
        selBrowser.open("/sysmedic/");
        selBrowser.type("username", "lmacias");
        selBrowser.type("password", "lmacias");
// selBrowser.type("fullname", "bla1");
// selBrowser.type("age", "99");
        selBrowser.click("//input[@name='submit']");
        selBrowser.setSpeed("5000");
        selBrowser.close();
// // optional for slower pages (?):
//selBrowser.waitForPageToLoad("10000");
// Assert.assertTrue(selBrowser.isTextPresent("bla1, 99"));
        selBrowser.stop();
        server.stop();
    }
}