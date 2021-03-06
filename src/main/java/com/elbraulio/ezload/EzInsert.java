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

package com.elbraulio.ezload;

import com.elbraulio.ezload.exception.EzException;
import com.elbraulio.ezload.parse.Parser;
import com.elbraulio.ezload.sql.InsertFromParser;
import com.elbraulio.ezload.sql.SqlFromParser;

import java.io.BufferedReader;
import java.sql.Connection;

/**
 * Factory to insert to a data base from EzLoad tools.
 *
 * @author Braulio Lopez (brauliop.3@gmail.com)
 * @since 0.1.0
 */
public final class EzInsert {

    /**
     * Insert a source to a data base.
     *
     * @param connection     connection to data base.
     * @param table          table name to insert.
     * @param parser         source format.
     * @param bufferedReader read the source.
     * @return total batch added to database.
     * @throws EzException EzLoad error.
     */
    public static long fromParser(
            Connection connection, String table, Parser parser,
            BufferedReader bufferedReader
    ) throws EzException {
        return new InsertFromParser(
                parser, new SqlFromParser(table, parser), Integer.MAX_VALUE
        ).execute(connection, bufferedReader);
    }

    /**
     * Insert a source to a data base.
     *
     * @param connection     connection to data base.
     * @param table          table name to insert.
     * @param parser         source format.
     * @param bufferedReader read the source.
     * @param chunkSize      chunk size to execute batch.
     * @return total batch added to database.
     * @throws EzException EzLoad error.
     */
    public static long fromParser(
            Connection connection, String table, Parser parser,
            BufferedReader bufferedReader, int chunkSize
    ) throws EzException {
        return new InsertFromParser(
                parser, new SqlFromParser(table, parser), chunkSize
        ).execute(connection, bufferedReader);
    }
}
