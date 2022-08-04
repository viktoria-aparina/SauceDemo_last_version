package org.example.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertiesLoader {

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();

        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            //load a properties file from class path, inside static method
            properties.load(input);
            log.info("The property {} was successfully loaded", fileName);

        } catch (IOException ex) {
            ex.printStackTrace();
            log.error("The property {} was not loaded, because of error {}", fileName, ex.getCause());
        }
        return properties;
    }

    public static Properties loadProperties() {
        Properties properties = loadProperties("config.properties");
        String user = properties.getProperty("user");
        Properties userProperties = loadProperties(user + ".properties");
        properties.putAll(userProperties);
        return properties;
    }
}
