package com.github.jxen.statistics;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * {@code SampleCollectors} class provides {@link Collector} implementations for {@link Sample} instances.
 *
 * @author Denis Murashev
 *
 * @since Math Statistics 0.1
 */
public final class SampleCollectors {

	private SampleCollectors() {
	}

	/**
	 * Provides collector for primitive double values.
	 *
	 * @param size size of {@link Stats} object
	 * @return collector for {@link Stats}
	 */
	public static Collector<Number, Stats, Stats> primitive(int size) {
		return new CollectorImpl<>(() -> new Stats(size), Stats::add, Stats::add);
	}

	/**
	 * Provides collector for quantile calculation.
	 *
	 * @return collector for {@link Quantile}
	 *
	 * @since Math Statistics 0.2
	 */
	public static Collector<Number, Quantile, Quantile> quantile() {
		return new CollectorImpl<>(Quantile::new, Quantile::add, Quantile::add);
	}

	private static final class CollectorImpl<T, S> implements Collector<T, S, S> {

		private final Supplier<S> supplier;
		private final BiConsumer<S, T> accumulator;
		private final BinaryOperator<S> combiner;

		private CollectorImpl(Supplier<S> supplier, BiConsumer<S, T> accumulator, BinaryOperator<S> combiner) {
			this.supplier = supplier;
			this.accumulator = accumulator;
			this.combiner = combiner;
		}

		@Override
		public Supplier<S> supplier() {
			return supplier;
		}

		@Override
		public BiConsumer<S, T> accumulator() {
			return accumulator;
		}

		@Override
		public BinaryOperator<S> combiner() {
			return combiner;
		}

		@Override
		public Function<S, S> finisher() {
			return Function.identity();
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
		}
	}
}
