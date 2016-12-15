package com.perihelios.math.functor;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class LongIndexerTest {
	@Test
	public void apply_works() {
		LongIndexer longIndexer;

		longIndexer = new LongIndexer(1);
		assertThat(longIndexer.apply(17), is(new LongIndexer.Index(1, 17)));
		assertThat(longIndexer.apply(17), is(new LongIndexer.Index(2, 17)));
		assertThat(longIndexer.apply(27), is(new LongIndexer.Index(3, 27)));

		longIndexer = new LongIndexer(10);
		assertThat(longIndexer.apply(17), is(new LongIndexer.Index(10, 17)));
		assertThat(longIndexer.apply(17), is(new LongIndexer.Index(11, 17)));
		assertThat(longIndexer.apply(27), is(new LongIndexer.Index(12, 27)));
	}

	@Test
	public void index_equals_hashCode_work() {
		Set<LongIndexer.Index> indices = new HashSet<>();
		indices.add(new LongIndexer.Index(1, 1));

		assertThat(indices.contains(new LongIndexer.Index(1, 1)), is(true));
		assertThat(indices.contains(new LongIndexer.Index(2, 1)), is(false));
		assertThat(indices.contains(new LongIndexer.Index(1, 2)), is(false));
	}

	@Test
	public void index_properties_work() {
		LongIndexer.Index index = new LongIndexer.Index(17, 27);

		assertThat(index.index, is(17L));
		assertThat(index.index(), is(17L));
		assertThat(index.value, is(27L));
		assertThat(index.value(), is(27L));
	}
}
