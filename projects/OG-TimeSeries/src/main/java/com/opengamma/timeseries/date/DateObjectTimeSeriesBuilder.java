/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.timeseries.date;

import java.util.Map;

/**
 * A builder of time-series that stores {@code Object} data values against {@code LocalDate} dates.
 * <p>
 * The "time" key to the time-series is a {@code LocalDate}.
 * See {@link DateObjectTimeSeries} for details about the "time" represented as an {@code int}.
 * 
 * @param <T>  the date type
 * @param <V>  the value being viewed over time
 */
public interface DateObjectTimeSeriesBuilder<T, V> {

  /**
   * Puts a time-value pair into the builder.
   * 
   * @param time  the time to add, not null
   * @param value  the value to add, not null
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> put(T time, V value);

  /**
   * Puts a time-value pair into the builder.
   * 
   * @param time  the time to add, not null
   * @param value  the value to add, not null
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> put(int time, V value);

  /**
   * Puts time-value pairs into the builder.
   * 
   * @param times  the times array to add, not null
   * @param values  the values array to add, not null
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> putAll(T[] times, V[] values);

  /**
   * Puts time-value pairs into the builder.
   * 
   * @param times  the times array to add, not null
   * @param values  the values array to add, not null
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> putAll(int[] times, V[] values);

  //-------------------------------------------------------------------------
  /**
   * Puts time-value pairs into the builder from another series.
   * <p>
   * This adds the whole of the specified series.
   * 
   * @param timeSeries  the timeSeries to copy from, not null
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> putAll(DateObjectTimeSeries<?, V> timeSeries);

  /**
   * Puts time-value pairs into the builder from another series.
   * <p>
   * This adds the the specified range of the specified series.
   * 
   * @param timeSeries  the timeSeries to copy from, not null
   * @param startPos  the start position, must be valid
   * @param endPos  the end position, must be valid
   * @return {@code this} for method chaining, not null
   * @throws IndexOutOfBoundsException if the indexes are invalid
   */
  DateObjectTimeSeriesBuilder<T, V> putAll(DateObjectTimeSeries<?, V> timeSeries, int startPos, int endPos);

  /**
   * Puts time-value pairs into the builder from another series.
   * <p>
   * This adds the the specified range of the specified series.
   * 
   * @param timeSeriesMap  the map representing a time-series to copy from, not null
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> putAll(Map<T, V> timeSeriesMap);

  //-------------------------------------------------------------------------
  /**
   * Clears the builder.
   * 
   * @return {@code this} for method chaining, not null
   */
  DateObjectTimeSeriesBuilder<T, V> clear();

  //-------------------------------------------------------------------------
  /**
   * Builds the immutable time-series from the builder.
   * <p>
   * Continuing to use the builder after calling this method is a programming error.
   * 
   * @return the immutable resulting time-series, not null
   */
  DateObjectTimeSeries<T, V> build();

}
