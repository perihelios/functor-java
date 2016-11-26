package com.perihelios.math.functor;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.perihelios.math.functor.Consecutives.consecutive;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ConsecutivesTest {
	private List<Long> elements = asList(1L, 2L, 2L, 3L, 3L, 3L, 4L, 4L, 4L, 4L);

	@Test
	public void consecutive_works() {
		assertThat(consecutive(Stream.empty(), 2, Object::equals).count(), is(0L));
		assertThat(consecutive(Stream.of(1L), 2, Object::equals).count(), is(0L));
		assertThat(consecutive(elements.stream(), 2, (n1, n2) -> n1 > 0L && n2 > 0L).collect(toList()), is(asList(1L, 2L)));
		assertThat(consecutive(elements.stream(), 2, (n1, n2) -> n1 > 1L && n2 > 1L).collect(toList()), is(asList(2L, 2L)));
		assertThat(consecutive(elements.stream(), 3, (n1, n2) -> (n1 & 1) == 0 && (n2 & 1) == 0).collect(toList()), is(asList(4L, 4L, 4L)));
		assertThat(consecutive(elements.stream(), 3, (n1, n2) -> false).count(), is(0L));
		assertThat(consecutive(elements.stream(), 5, (n1, n2) -> n1 == 4L).count(), is(0L));
	}

	@Test
	public void consecutive_closes_stream() {
		AtomicBoolean closed = new AtomicBoolean();
		Stream<Long> stream;

		stream = elements.stream();
		stream.onClose(() -> closed.set(true));
		closed.set(false);
		consecutive(stream, 2, (n1, n2) -> n1 > 0L && n2 > 0L).count();
		assertThat(closed.get(), is(true));

		stream = Stream.empty();
		stream.onClose(() -> closed.set(true));
		closed.set(false);
		consecutive(stream, 2, (n1, n2) -> n1 > 0L && n2 > 0L).count();
		assertThat(closed.get(), is(true));

		stream = Stream.of(1L);
		stream.onClose(() -> closed.set(true));
		closed.set(false);
		consecutive(stream, 2, (n1, n2) -> n1 > 0L && n2 > 0L).count();
		assertThat(closed.get(), is(true));

		stream = elements.stream();
		stream.onClose(() -> closed.set(true));
		closed.set(false);
		consecutive(stream, 2, (n1, n2) -> n1 > 5L).count();
		assertThat(closed.get(), is(true));
	}

	@Test
	public void consecutive_minimum() {
		try {
			consecutive(elements.stream(), 1, (n1, n2) -> false);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Number of consecutive items must be 2 or greater; got 1"));
		}
	}
}
