package com.starbucks.util;

import com.google.common.net.HostAndPort;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestClient {

    private final CloseableHttpClient httpClient;
    private final List<Header> headers;
    private final HostAndPort hostAndPort;

    public RestClient(TestRunnerContext context) {
        this.httpClient = HttpClientBuilder.create().build();
        this.headers = new ArrayList<>();
        this.hostAndPort = context.getTargetHostAndPort();
    }

    public void addHeader(String key, String val){
        headers.add(new BasicHeader(key,val));
    }

    public void clearHeaders(){
        headers.clear();
    }

    public ParsedResponse get(final String url) throws IOException {
        String completeURL = buildCompleteURL(url);
        HttpGet get = new HttpGet(completeURL);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-type", "application/json");
        headers.forEach(get::addHeader);
        return executeRequest(get);
    }

    public ParsedResponse post(final String url, final String json) throws IOException {
        String completeURL = buildCompleteURL(url);
        HttpPost post = new HttpPost(completeURL);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        headers.forEach(post::addHeader);

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        return executeRequest(post);
    }


    public ParsedResponse executeRequest(HttpRequestBase method) throws IOException {
        CloseableHttpResponse  response = httpClient.execute(method);

        ParsedResponse res = new ParsedResponse();
        int statusCode = response.getStatusLine().getStatusCode();
        String mime = response.getEntity().getContentType().getValue();
        String content = EntityUtils.toString(response.getEntity());

        res.setStatusCode(statusCode)
                .setMime(mime)
                .setContent(content);

        response.close();
        headers.clear();
        return res;
    }

    public String buildCompleteURL(final String url){
        StringBuilder sb = new StringBuilder("http://");
        sb.append(hostAndPort);
        if(url.startsWith("/")){
            sb.append(url);
        } else {
            sb.append("/");
            sb.append(url);
        }
        return sb.toString();
    }


}
