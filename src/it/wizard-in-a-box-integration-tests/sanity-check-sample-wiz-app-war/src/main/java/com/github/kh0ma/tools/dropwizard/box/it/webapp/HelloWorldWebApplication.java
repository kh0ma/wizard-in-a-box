package com.github.kh0ma.tools.dropwizard.box.it.webapp;

import com.github.kh0ma.tools.dropwizard.box.WebApplication;
import com.github.kh0ma.tools.dropwizard.box.it.app.HelloWorldApplication;
import com.github.kh0ma.tools.dropwizard.box.it.app.HelloWorldConfiguration;

import javax.servlet.annotation.WebListener;

@WebListener
public class HelloWorldWebApplication extends WebApplication<HelloWorldConfiguration> {
    public HelloWorldWebApplication() {
        super(new HelloWorldApplication(), "hello-world-as-dw-war.yml");
    }
}
