package com.github.kh0ma.tools.dropwizard.box.it.sample.web.resources;


import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlSupport;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class StaticHelloHtmlIT {
    @Test
    public void checkHelloHtml() throws IOException {
        URL url = new URL(ContextUrlSupport.contextUrl() +
                "/hello.html");
        Assert.assertEquals("Hello from html.", IOUtils.toString(url.openStream()));
    }
}
