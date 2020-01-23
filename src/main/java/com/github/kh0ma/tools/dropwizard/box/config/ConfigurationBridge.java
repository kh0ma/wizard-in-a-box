package com.github.kh0ma.tools.dropwizard.box.config;

import io.dropwizard.Configuration;

public interface ConfigurationBridge<C extends Configuration> {
    void load(C configuration);
}
