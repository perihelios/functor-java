package com.perihelios.math.functor;

import java.util.Spliterator;

public abstract class SplitlessSpliteratorOfLong implements Spliterator.OfLong {
	@Override
	public Spliterator.OfLong trySplit() {
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
