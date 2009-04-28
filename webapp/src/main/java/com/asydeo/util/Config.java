package com.asydeo.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;


public class Config {

    private static XMLConfiguration config;

    static {
        new Config();
    }
    
    public Config() {
        try {
            config = new XMLConfiguration("config.xml");
            FileChangedReloadingStrategy reload =
              new FileChangedReloadingStrategy();
            reload.setRefreshDelay(60000);
            config.setReloadingStrategy(reload);
        }
        catch (final ConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    public static String getValue(String name) {
        return config.getString(name);
    }
}
