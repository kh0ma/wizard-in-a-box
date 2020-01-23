package com.github.kh0ma.tools.dropwizard.box.config;

import io.dropwizard.Configuration;

public class ReflectionConfigurationWriterFactory<C extends Configuration> implements ConfigurationWriterFactory<C> {
    @Override
    public ConfigurationWriter build(C configuration) {
        return new ReflectionConfigurationWriter(configuration);
    }
}
