package com.github.kh0ma.tools.dropwizard.box.it.providers;

import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlProvider;

public class SampleJEEWarContextUrlProvider implements ContextUrlProvider {
    @Override
    public String contextUrl() {
        return "http://localhost:8885/sample-jee-war";
    }
}
