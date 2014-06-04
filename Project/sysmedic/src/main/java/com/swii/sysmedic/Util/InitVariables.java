/*
 * Taken from: http://stackoverflow.com/questions/12099008/how-to-include-values-from-properties-file-into-web-xml
 */

package com.swii.sysmedic.Util;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitVariables implements ServletContextListener
{

   @Override
   public void contextDestroyed(final ServletContextEvent event)
   {}

   @Override
   public void contextInitialized(final ServletContextEvent event)
   {
      final String props = "/config.properties";
      final Properties propsFromFile = new Properties();
      try
      {
         propsFromFile.load(getClass().getResourceAsStream(props));
      }
      catch (final Exception e)
      {
          // can't get resource
          System.out.println("The file can not be readed. "+ e.getMessage());
      }
      for (String prop : propsFromFile.stringPropertyNames())
      {
         if (System.getProperty(prop) == null)
         {
             System.setProperty(prop, propsFromFile.getProperty(prop));
         }
      }
   }
}  

/*
now you can get all properties in you project using

System.getProperty(...)
or in web.xml

<param-name>param-name</param-name>
<param-value>${param-name}</param-value>
*/
