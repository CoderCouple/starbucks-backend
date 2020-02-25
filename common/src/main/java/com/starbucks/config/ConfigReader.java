package com.starbucks.config;

import com.google.common.io.Resources;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.inject.Inject;
import java.io.File;

public class ConfigReader {
    public static final String FILE_NAME = "config.json";
    public static final String FILE_LOCATION = "/src/main/resources";
    public static final String FILE_PATH_APPENDER = "/";
    public static Configuration config;

    @Inject
    public ConfigReader() {
        this.config = initJSON();
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

    //Picked Up From : https://www.programcreek.com/java-api-examples/?code=Virtlink%2Fcommons-configuration2-jackson%2Fcommons-configuration2-jackson-master%2Fsrc%2Ftest%2Fjava%2Fcom%2Fvirtlink%2Fcommons%2Fconfiguration2%2Fjackson%2FJsonConfigurationTests.java#
    public Configuration initJSON() {
        final FileBasedBuilderParameters params = new Parameters()
                .fileBased()
                .setThrowExceptionOnMissing(true)
                .setEncoding("UTF-8")
                .setFileName(Resources.getResource(FILE_NAME).toString());

        final FileBasedConfigurationBuilder<JSONConfiguration> builder =
                new FileBasedConfigurationBuilder<>(JSONConfiguration.class);
        final JSONConfiguration config;
        try {
            config = builder.configure(params).getConfiguration();
            return config;
        } catch (final ConfigurationException e) {
            e.printStackTrace();
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
