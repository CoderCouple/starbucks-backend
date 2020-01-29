package com.starbucks.service;

public class PingServiceMockImpl implements PingService {
    @Override
    public String getPingResponse(final String name) {
        return "Pong!";
    }
}
