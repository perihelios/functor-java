package com.perihelios.math.functor;

import javax.annotation.Generated;
import java.util.function.LongFunction;

public class LongIndexer implements LongFunction<LongIndexer.Index> {
	private long index;

	public LongIndexer(long startingIndex) {
		this.index = startingIndex;
	}

	@Override
	public Index apply(long value) {
		return new Index(index++, value);
	}

	public static class Index {
		public final long index;
		public final long value;


		Index(long index, long value) {
			this.index = index;
			this.value = value;
		}

		public long index() {
			return index;
		}

		public long value() {
			return value;
		}

		@Generated("IntelliJ")
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Index index1 = (Index) o;

			if (index != index1.index) return false;
			return value == index1.value;
		}

		@Generated("IntelliJ")
		@Override
		public int hashCode() {
			int result = (int) (index ^ (index >>> 32));
			result = 31 * result + (int) (value ^ (value >>> 32));
			return result;
		}
	}
}
