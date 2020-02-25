package com.starbucks.persistance;

import com.starbucks.dao.LineItemDao;
import com.starbucks.dao.OrderDao;
import com.starbucks.dao.PingDao;
import com.starbucks.dao.ProductDao;
import com.starbucks.dao.UserDao;

public interface DaoFactory {

    PingDao getPingDao(final PersistenceManagerProvider pmp);

    UserDao getUserDao(final PersistenceManagerProvider pmp);

    OrderDao getOrderDao(final PersistenceManagerProvider pmp);

    ProductDao getProductDao(final PersistenceManagerProvider pmp);

    LineItemDao getLineItemDao(final PersistenceManagerProvider pmp);
}
