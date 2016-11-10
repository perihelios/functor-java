package com.perihelios.math.functor;

import java.util.Spliterator;

public abstract class BaseSpliterator<T> implements Spliterator<T> {
	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return Long.MAX_VALUE;
	}

	@Override
	public int characteristics() {
		return Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.ORDERED;
	}
}
