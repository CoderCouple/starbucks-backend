package com.starbucks.service.mock;

import com.starbucks.service.PingService;

public class PingServiceMockImpl implements PingService {
    @Override
    public String getPingResponse() {
        return "Pong!";
    }
}
