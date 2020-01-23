package com.github.kh0ma.tools.dropwizard.box.it.rest.resources;

import com.github.kh0ma.tools.dropwizard.box.it.support.ContextUrlSupport;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class DocumentedHelloWorldResourceIT {
    @Test
    public void checkHelloWorldResource() throws IOException {
        URL url = new URL(ContextUrlSupport.contextUrl() +
                "/api/documented-hello-world?name=Merlin");
        Assert.assertEquals("{\"id\":1,\"content\":\"Hello, Merlin!\"}", IOUtils.toString(url.openStream()));
    }

}
