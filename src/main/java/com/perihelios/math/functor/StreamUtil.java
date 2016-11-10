package com.perihelios.math.functor;

import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtil {
	public static <T> Stream<T> takeWhile(Stream<T> stream, Predicate<T> predicate) {
		Spliterator<T> sourceSpliterator = stream.spliterator();

		return StreamSupport.stream(new SplitlessSpliterator<T>() {
			@Override
			public boolean tryAdvance(Consumer<? super T> action) {
				AtomicBoolean predicateFailed = new AtomicBoolean(true);

				return sourceSpliterator.tryAdvance(t -> {
					if (predicate.test(t)) {
						action.accept(t);
					} else {
						predicateFailed.set(false);
						stream.close();
					}
				}) && predicateFailed.get();
			}
		}, stream.isParallel());
	}
}
