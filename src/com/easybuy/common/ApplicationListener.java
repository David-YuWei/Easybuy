/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easybuy.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author weiyu
 */
public class ApplicationListener implements ServletContextListener {

	private static final String SYSTEM_PROPERTIES = "/system.properties";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			initSystemProperties();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private void initSystemProperties() throws IOException {
		InputStream stream = null;
		try {
			stream = this.getClass().getResourceAsStream(SYSTEM_PROPERTIES);
			if (stream == null) {
				System.err.println("WARN: " + SYSTEM_PROPERTIES + " not exists.");
			} else {
				Properties sprops = new Properties();
				sprops.load(stream);
				Set<String> propertyNames = sprops.stringPropertyNames();
				for (String propertyName : propertyNames) {
					System.setProperty(propertyName, sprops.getProperty(propertyName, ""));
				}
			}
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
}
