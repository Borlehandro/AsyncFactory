package com.borlehandro;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    public static int getValue(String name) throws IOException {
        Properties properties = new Properties();
        properties.load(Properties.class.getClassLoader().getResourceAsStream("values.properties"));
        return Integer.parseInt(properties.getProperty(name));
    }
}
