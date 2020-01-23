package com.github.kh0ma.tools.dropwizard.box.it.sample.servlets;


import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlSupport;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class HelloServletIT {
    @Test
    public void checkHelloServlet() throws IOException {
        URL url = new URL(ContextUrlSupport.contextUrl() +
                "/hello");
        Assert.assertEquals("Hello World from servlet.", IOUtils.toString(url.openStream()));
    }
}
