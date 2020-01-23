package com.github.kh0ma.tools.dropwizard.box.config;

import io.dropwizard.configuration.ConfigurationSourceProvider;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathConfigurationSourceProvider implements ConfigurationSourceProvider {
    @Override
    public InputStream open(String path) throws IOException {
        getClass().getClassLoader();
        return getClass().getResourceAsStream("/" + path);
    }
}
