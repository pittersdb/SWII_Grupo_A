/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config
{
    
    private static Config config;
    
    public static Config getInstance(){
        if(config == null)
            config = new Config();
        return config;
    }
    
    private Properties configFile;
    
    private Config()
    {
        configFile = new java.util.Properties();
        try {
            configFile.load( new FileInputStream(new File(System.getProperty("basedir") + "\\src\\main\\webapp\\WEB-INF\\classes\\config.properties")) );
        }catch(Exception eta){
            eta.printStackTrace();
        }
    }
    
    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }
}