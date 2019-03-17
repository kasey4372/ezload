/*
 * MIT License
 *
 * Copyright (c) 2019 Braulio López
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.elbraulio.ezload.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A Column represent the format that a value from a column should check.
 *
 * @author Braulio Lopez (brauliop.3@gmail.com)
 * @since 1.0.0
 */
public interface Column<T> {

    /**
     * Column's value.
     *
     * @param value original value.
     * @return transformed value.
     */
    T value(String value);

    /**
     * Position from left to right of the column on a line separated by a
     * expression. From 0 to n.
     *
     * @return position number.
     */
    int order();

    /**
     * Column's name.
     *
     * @return name.
     */
    String name();

    /**
     * @todo this method should throw an exception
     * @body it is common that a validation check returns false, so what happens
     * @body if it is so? that is why it should throw an exception. Not sure
     * @body where, maybe when the File is loading.
     */
    /**
     * Checks if the value is valid or not.
     *
     * @param value raw value to check.
     * @return true if it is valid and false if it is not.
     */
    boolean isValid(String value);

    /**
     * Add a value to the {@link PreparedStatement} then returns it.
     *
     * @param ps    {@link PreparedStatement}
     * @param index index to add to {@link PreparedStatement}.
     * @param value raw value to add.
     * @return {@link PreparedStatement}
     * @throws SQLException {@link PreparedStatement} error.
     */
    PreparedStatement addToPreparedStatement(
            PreparedStatement ps, int index, String value
    ) throws SQLException;
}
