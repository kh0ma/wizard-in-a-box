package com.github.kh0ma.tools.dropwizard.box.config;

public interface ConfigurationWriter {
    void write(String[] path, Object value) throws ConfigurationWriterException;
}
