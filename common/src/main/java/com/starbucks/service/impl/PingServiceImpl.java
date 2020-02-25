package com.starbucks.service.impl;


import com.starbucks.persistance.DaoProvider;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.service.PingService;
import com.starbucks.view.PingView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class PingServiceImpl implements PingService {

    private DaoProvider daoProvider;
    private static final Logger LOGGER = LoggerFactory.getLogger(PingServiceImpl.class);

    @Inject
    public PingServiceImpl(final DaoProvider daoProvider) {
        this.daoProvider = daoProvider;
    }

    @Override
    public String getPingResponse() {
        try (final PersistenceManagerProvider pmp = daoProvider.getReadPmp()) {
            PingView pingView = daoProvider.getDaoFactory().getPingDao(pmp).getPing();
            return pingView.getData();
        }
    }
}
