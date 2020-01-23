package com.starbucks.util;

import com.google.common.net.HostAndPort;

public class TestRunnerContext {

    public HostAndPort getTargetHostAndPort() {
        return HostAndPort.fromString("localhost:8080");
    }
}
