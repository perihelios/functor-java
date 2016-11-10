package com.perihelios.math.functor;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.perihelios.math.functor.StreamUtil.takeWhile;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StreamUtilTest {
	private final Stream<Long> sequence = Stream.iterate(1L, t -> t + 1L);

	@Test
	public void takeWhile_works() {
		assertThat(
			takeWhile(sequence, n -> n < 5L).collect(Collectors.toList()),
			is(asList(1L, 2L, 3L, 4L))
		);
	}

	@Test
	public void takeWhile_ends_if_underlying_stream_ends() {
		assertThat(
			takeWhile(Stream.of(1L, 2L), n -> n < 5L).collect(Collectors.toList()),
			is(asList(1L, 2L))
		);
	}

	@Test
	public void takeWhile_closes_underlying_stream_when_predicate_false() {
		AtomicBoolean closed = new AtomicBoolean();
		sequence.onClose(() -> closed.set(true));

		takeWhile(sequence, n -> n < 5L).count();

		assertThat(closed.get(), is(true));
	}

	@Test
	public void takeWhile_propagates_parallelism() throws Exception {
		assertThat(takeWhile(Stream.of(), n -> true).isParallel(), is(false));
		assertThat(takeWhile(Stream.of().parallel(), n -> true).isParallel(), is(true));
	}
}
