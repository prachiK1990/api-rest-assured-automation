package com.api.readers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class PropertyLoader {
	
	private static FileInputStream fis;
	private static Properties prop = null;
	private static final Logger log = LogManager.getLogger(PropertyLoader.class);
	private static String baseUrl;
	
	public static String getProperty(String key){
		if (prop == null){
			new PropertyLoader();
		}
		return prop.getProperty(key);
	}
	
	public PropertyLoader(){
		BufferedReader reader;
		String configFilePath = "config.properties";
		try{
			reader = new BufferedReader(new FileReader(configFilePath));
			prop = new Properties();
			try{
				prop.load(reader);
				reader.close();
				
			}catch (IOException e){
				e.printStackTrace();
			}
			
		}catch (FileNotFoundException e) {
			log.error(e.getMessage());
			throw new RuntimeException("Configuration properties not found at " + configFilePath);
			
		}
	}

}
