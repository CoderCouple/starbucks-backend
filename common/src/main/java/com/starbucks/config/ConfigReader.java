package com.starbucks.config;

import com.google.inject.Inject;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigReader {
    public static final String FILE_NAME = "config.properties";
    public static final String FILE_LOCATION = "/src/main/resources";
    public static final String FILE_PATH_APPENDER = "/";
    public Configuration config;

    @Inject
    public ConfigReader() {
        this.config = init();
    }

    public Configuration init() {
        Parameters params = new Parameters();
        File propertiesFile = new File(getAbsoluteFileLocation());

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased()
                                .setFile(propertiesFile));
        try {
            Configuration config = builder.getConfiguration();
            return config;
        } catch (final ConfigurationException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public String getAbsoluteFileLocation() {
        return new StringBuilder()
                .append(FILE_LOCATION)
                .append(FILE_PATH_APPENDER)
                .append(FILE_NAME)
                .toString();
    }

}
