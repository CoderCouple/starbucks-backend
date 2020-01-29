package com.starbucks.service;

public class PingServiceImpl implements PingService {


    @Override
    public String getPingResponse(final String name) {
        return "Pong!";
    }
}
