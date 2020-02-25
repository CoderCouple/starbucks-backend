package com.starbucks.dao;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.starbucks.model.LineItem;
import com.starbucks.persistance.PersistenceManagerProvider;
import com.starbucks.persistance.PersistentDao;

import javax.jdo.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javax.jdo.Query.SQL;

public class LineItemDao extends PersistentDao<LineItem> {

    @AssistedInject
    public LineItemDao(final @Assisted PersistenceManagerProvider pmp) {
        super(pmp, LineItem.class);
    }

    public static final String LINE_ITEM_BASE_QUERY = "SELECT li.id as id, " +
                                                     "li.orderId as orderId, " +
                                                     "li.productId as productId, " +
                                                     "li.quantity as quantity " +
                                                     "FROM `LineItem` li ";

    public static final String GET_LINE_ITEM_BY_ID = LINE_ITEM_BASE_QUERY + "WHERE li.id = :lineItemId";;

    public Optional<LineItem> fetchLineItemById(final int lineItemId) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("lineItemId", String.valueOf(lineItemId));

        Query query = getPmp().get().newQuery(SQL, GET_LINE_ITEM_BY_ID);
        query.setResultClass(LineItem.class);
        query.setUnique(true);
        LineItem lineItemRecord  = (LineItem) query.executeWithMap(parameters);

        return Optional.ofNullable(lineItemRecord);
    }

    public LineItem createLineItem(final LineItem lineItem) {
        LineItem userRecord = persistAndFetch(lineItem);
        return userRecord;
    }
}
