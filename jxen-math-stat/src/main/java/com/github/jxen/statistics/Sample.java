package com.github.jxen.statistics;

/**
 * {@code Sample} interface represents data sample which supports new items addition.
 *
 * @author Denis Murashev
 *
 * @param <T> sample class
 *
 * @since Math Statistics 0.1
 */
public interface Sample<T extends Sample<T>> {

  /**
   * Adds value to sample.
   *
   * @param value value
   * @return current sample
   */
  T add(Number value);

  /**
   * Combines two samples.
   *
   * @param sample sample to add
   * @return combined sample
   */
  T add(T sample);
}
