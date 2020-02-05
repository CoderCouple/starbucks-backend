package com.starbucks.dao;

public interface PersistentObject {
    String DELIMITER = "::";

    Object primaryKey();

    default void markForDeletion() {
        throw new UnsupportedOperationException();
    }
}
