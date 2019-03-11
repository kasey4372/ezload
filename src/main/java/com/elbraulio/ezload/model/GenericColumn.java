package com.elbraulio.ezload.model;

import com.elbraulio.ezload.model.batch.AddBatch;
import com.elbraulio.ezload.model.transform.Transform;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This implementation works as a generic representation of the value and
 * format that a column (or cell) stores.
 *
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class GenericColumn<T> implements Column<T> {
    private final int order;
    private final String name;
    private final Constrain<T> constrain;
    private final Transform<T> transform;
    private final AddBatch<T> addBatch;

    /**
     * Ctor.
     *
     * @param order     position on file, from left to right starting from 0.
     * @param name      column name.
     * @param constrain value constrain.
     * @param transform value transform.
     * @param addBatch  batch operation to add value.
     */
    public GenericColumn(
            int order, String name, Constrain<T> constrain,
            Transform<T> transform, AddBatch<T> addBatch
    ) {

        this.order = order;
        this.name = name;
        this.constrain = constrain;
        this.transform = transform;
        this.addBatch = addBatch;
    }

    @Override
    public T value(String value) {
        return this.transform.from(value);
    }

    @Override
    public int order() {
        return this.order;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isValid(String value) {
        return this.constrain.isValid(value(value));
    }

    /*
     * @todo do not know how to test this
     * @body how to check if parameters were actually saved?
     */
    @Override
    public PreparedStatement addToPreparedStatement(
            PreparedStatement ps, int index, T value
    ) throws SQLException {
        return this.addBatch.addValue(ps, index, value);
    }
}