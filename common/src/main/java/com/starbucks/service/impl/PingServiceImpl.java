package com.starbucks.service.impl;


import com.starbucks.dao.PingDao;
import com.starbucks.service.PingService;
import com.starbucks.view.PingView;

import javax.inject.Inject;

public class PingServiceImpl implements PingService {

    private PingDao pingDao;
    @Inject
    public PingServiceImpl(final PingDao pingDao) {
        this.pingDao = pingDao;
    }

    @Override
    public String getPingResponse() {
        PingView pingView = pingDao.getPing();
        return pingView.getData();
    }
}
