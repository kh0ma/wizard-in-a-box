package com.github.kh0ma.tools.dropwizard.box.it.providers;

import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlProvider;

public class SampleWizSwaggerAppWarContextUrlProvider implements ContextUrlProvider {
    @Override
    public String contextUrl() {
        return "http://localhost:8887/sample-wiz-swagger-app-war";
    }
}
