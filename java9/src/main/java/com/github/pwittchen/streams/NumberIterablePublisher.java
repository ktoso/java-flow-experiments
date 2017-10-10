
/************************************************************************
 * Licensed under Public Domain (CC0)                                    *
 *                                                                       *
 * To the extent possible under law, the person who associated CC0 with  *
 * this code has waived all copyright and related or neighboring         *
 * rights to this code.                                                  *
 *                                                                       *
 * You should have received a copy of the CC0 legalcode along with this  *
 * work. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.*
 ************************************************************************/
package com.github.pwittchen.streams;

import com.github.pwittchen.streams.AsyncIterablePublisher;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Executor;

public class NumberIterablePublisher extends AsyncIterablePublisher<Integer> {
  public NumberIterablePublisher(final int from, final int to, final Executor executor) {
    super(new Iterable<>() {
      {
        if (from > to) throw new IllegalArgumentException("from must be equal or greater than to!");
      }

      @Override public Iterator<Integer> iterator() {
        return new Iterator<>() {
          private int at = from;

          @Override public boolean hasNext() {
            return at < to;
          }

          @Override public Integer next() {
            if (!hasNext()) {
              return Collections.<Integer>emptyList().iterator().next();
            } else {
              return at++;
            }
          }

          @Override public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }
    }, executor);
  }
}