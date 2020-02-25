package com.starbucks.persistance;

public interface PersistentObject {
    String DELIMITER = "::";
    Object primaryKey();

    default void markForDeletion() {
        throw new UnsupportedOperationException();
    }
}
