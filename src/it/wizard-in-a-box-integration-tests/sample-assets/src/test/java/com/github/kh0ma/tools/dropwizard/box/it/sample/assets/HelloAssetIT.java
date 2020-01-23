package com.github.kh0ma.tools.dropwizard.box.it.sample.assets;


import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlSupport;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class HelloAssetIT {
    @Test
    public void checkHelloAsset() throws IOException {
        URL url = new URL(ContextUrlSupport.contextUrl() +
                "/static/hello.html");
        Assert.assertEquals("Hello from asset.", IOUtils.toString(url.openStream()));
    }
}
