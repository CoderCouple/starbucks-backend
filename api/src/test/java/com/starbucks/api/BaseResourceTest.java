package com.starbucks.api;

import com.google.common.net.UrlEscapers;
import com.google.inject.servlet.GuiceFilter;
import com.starbucks.app.TestConfig;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import javax.ws.rs.Path;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.testng.AssertJUnit.fail;

public abstract class BaseResourceTest extends JerseyTest implements TestApiResource {
    private Map<String,String> queryParams = new HashMap<>();
    private Map<String,String> headers = new HashMap<>();

    @BeforeClass
    public void runBeforeClass() throws Exception {
        super.setUp();
    }

    @BeforeMethod
    public void runBeforeMethod() throws Exception {
        queryParams.clear();
        headers.clear();
    }

    @Override
    protected DeploymentContext configureDeployment() {
       final ResourceConfig config = ServletDeploymentContext
               .builder(TestConfig.class)
               .build()
               .getResourceConfig();
       forceSet(TestProperties.CONTAINER_PORT,"0");
       return ServletDeploymentContext
                .forServlet(new ServletContainer(config))
                .addFilter(GuiceFilter.class,"guiceFilter")
                .build();
    }

    @Override
    protected TestContainerFactory getTestContainerFactory(){
        return new GrizzlyWebTestContainerFactory();
    }

    protected void setQueryParam(String key, String val) {
        this.queryParams.put(key,val);
    }

    protected void setHeader(String key, String val) {
        this.headers.put(key,val);
    }

    private String getApiVersion(){
        return ((Path) getResourceClass().getAnnotation(Path.class)).value();
    }

    public String getApiResourcePath() {
        return "/"+getApiVersion()+getResourcePath();
    }

    protected Response get(){
        return get("");
    }

    protected Response get(String subPath){
        String resourcePath = getApiResourcePath()+subPath;
        WebTarget target = target(resourcePath);

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            target = target.queryParam(entry.getKey(), encodeValue(entry.getValue()));
        }
//
//        for (Map.Entry<String, String> entry : headers.entrySet()) {
//            target = target.request(.entry.getKey(), entry.getValue());
//        }

        return target.request().get();
    }

    protected Response post(String subPath, String json){
        String resourcePath = getApiResourcePath()+subPath;
        WebTarget target = target(resourcePath);
        return target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON_TYPE));
    }

    protected String getResourceAsString(String fileName){
        InputStream stream = getClass().getResourceAsStream(getResourcePath()+"/"+fileName);
        try{
            return IOUtils.toString(stream,StandardCharsets.UTF_8).toString();
        } catch (IOException e) {
           fail("Failed to read the file : "+fileName);
           return null;
        }
    }

    protected String encodeValue(String value){
        return UrlEscapers.urlFragmentEscaper().escape(value);
    }

}
