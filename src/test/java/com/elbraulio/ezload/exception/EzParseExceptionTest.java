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

package com.elbraulio.ezload.exception;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for {@link EzParseException}.
 *
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public class EzParseExceptionTest {
    @Test
    public void exceptionMessages() {
        MatcherAssert.assertThat(
                "should keep the initial message",
                new EzParseException(
                        "a message", new LinkedList<>()
                ).getMessage(),
                CoreMatchers.is("a message")
        );
    }

    @Test
    public void errorList() {
        final List<String> errors = new LinkedList<>();
        errors.add("error 1");
        MatcherAssert.assertThat(
                "should keep the initial message",
                new EzParseException("a message", errors).errors().get(0),
                CoreMatchers.is("error 1")
        );
    }

    @Test
    public void immutableErrors() {
        final List<String> errors = new LinkedList<>();
        errors.add("error 1");
        final EzParseException exception = new EzParseException(
                "a message", errors
        );
        exception.errors().clear();
        MatcherAssert.assertThat(
                "immutable errors",
                exception.errors().size(),
                Matchers.is(1)
        );
    }

    @Test
    public void chainedExceptions() {
        final List<String> errors = new LinkedList<>();
        errors.add("error 1");
        MatcherAssert.assertThat(
                "should keep the initial message",
                new EzParseException(
                        "a message", errors, new NumberFormatException("error")
                ).errors().get(0),
                CoreMatchers.is("error 1")
        );
    }
}