package com.starbucks.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BaseResponse {

    @JsonProperty("data")
    private Object data;

    @JsonProperty("meta")
    private Object meta;

    @JsonProperty("debug")
    private Object debug;

    @JsonProperty("errors")
    private List<Map<String, Object>> errors;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("timestamp")
    private Long timestamp;

    public BaseResponse() {
    }

    public Object getData() {
        return data;
    }

    public BaseResponse setData(final Object data) {
        this.data = data;
        return this;
    }

    public Object getMeta() {
        return meta;
    }

    public BaseResponse setMeta(final Object meta) {
        this.meta = meta;
        return this;
    }

    public Object getDebug() {
        return debug;
    }

    public BaseResponse setDebug(final Object debug) {
        this.debug = debug;
        return this;
    }

    public List<Map<String, Object>> getErrors() {
        return errors;
    }

    public BaseResponse setErrors(final List<Map<String, Object>> errors) {
        this.errors = errors;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public BaseResponse setStatus(final HttpStatus status) {
        this.status = status;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public BaseResponse setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public static Response buildResponse() {
        return Response.status(Response.Status.OK).entity(new BaseResponse()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static Response buildSuccessResponse(final Object entity) {
        return Response.status(Response.Status.OK).entity(new BaseResponse()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public static Response buildErrorResponse(final Object error) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BaseResponse)) {
            return false;
        }

        BaseResponse that = (BaseResponse) o;

        return new EqualsBuilder()
                .append(getData(), that.getData())
                .append(getMeta(), that.getMeta())
                .append(getDebug(), that.getDebug())
                .append(getErrors(), that.getErrors())
                .append(getStatus(), that.getStatus())
                .append(getTimestamp(), that.getTimestamp())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getData())
                .append(getMeta())
                .append(getDebug())
                .append(getErrors())
                .append(getStatus())
                .append(getTimestamp())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", data)
                .append("meta", meta)
                .append("debug", debug)
                .append("errors", errors)
                .append("status", status)
                .append("timestamp", timestamp)
                .toString();
    }
}
