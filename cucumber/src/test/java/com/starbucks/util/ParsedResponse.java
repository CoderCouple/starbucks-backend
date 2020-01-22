package com.starbucks.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.http.entity.ContentType;

public class ParsedResponse {
    private int statusCode;
    private JsonObject content;
    private String mime;

    public int getStatusCode() {
        return statusCode;
    }

    public ParsedResponse setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public JsonObject getContent() {
        return content;
    }

    public ParsedResponse setContent(final String content) {
        if(mime.equalsIgnoreCase(ContentType.APPLICATION_JSON.toString())){
            try {
                this.content = new JsonParser().parse(content).getAsJsonObject();
            } catch (final JsonSyntaxException ex){
                this.content =null;
            }
        } else {
            this.content =null;
        }
        return this;
    }

    public String getMime() {
        return mime;
    }

    public ParsedResponse setMime(String mime) {
        this.mime = mime;
        return this;
    }
}
