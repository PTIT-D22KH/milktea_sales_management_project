/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author P51
 */
public class ConfigLoader {
    private static final String CONFIG_PATH = "/config.properties";
    private static ConfigLoader instance;
    private Properties propertyLoader = new Properties();
    
    /**
     * Constructor that read configure file
     */
    public ConfigLoader() {
        readConfigFile();
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public Properties getPropertyLoader() {
        return propertyLoader;
    }

    public String getProperty(String key) {
        return propertyLoader.getProperty(key);
    }
    
    
    
    private void readConfigFile() {
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream(CONFIG_PATH);
            propertyLoader.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
//    public void readConfigFile() throws IOException {
//        try (InputStream inputStream = getClass().getResourceAsStream(CONFIG_PATH)) {
//            if (inputStream == null) {
//                throw new IOException("Configuration file not found: " + CONFIG_PATH);
//            }
//            propertyLoader.load(inputStream);
//        }
//    }
    
    
}
