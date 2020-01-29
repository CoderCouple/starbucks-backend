package com.starbucks.service;


import com.starbucks.dao.PingDao;
import com.starbucks.model.Ping;

import javax.inject.Inject;

public class PingServiceImpl implements PingService {

    private PingDao pingDao;
    @Inject
    public PingServiceImpl(final PingDao pingDao) {
        this.pingDao = pingDao;
    }

    @Override
    public String getPingResponse() {
        Ping p = pingDao.getPing();
        return p.getData();
    }
}
