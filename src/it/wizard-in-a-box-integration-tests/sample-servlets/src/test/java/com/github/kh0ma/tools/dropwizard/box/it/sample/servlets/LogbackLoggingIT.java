package com.github.kh0ma.tools.dropwizard.box.it.sample.servlets;

import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlSupport;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class LogbackLoggingIT {
    @Test
    public void checkHelloServlet() throws IOException {
        URL url = new URL(ContextUrlSupport.contextUrl() +
                "/check/logging");
        Assert.assertEquals("LoggerFactory type = ch.qos.logback.classic.LoggerContext", IOUtils.toString(url.openStream()));
    }
}
