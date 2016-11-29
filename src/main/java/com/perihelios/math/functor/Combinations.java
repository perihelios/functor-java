package com.perihelios.math.functor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Combinations {
	public static <T> Stream<List<T>> combinations(int choose, Iterable<T> items) {
		if (choose < 1) {
			throw new IllegalArgumentException("Items to choose must be greater than 0; got " + choose);
		}

		T[] values;

		if (items instanceof Collection) {
			Collection<T> collection = (Collection<T>) items;

			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[collection.size()];
			values = temp;

			collection.toArray(values);
		} else {
			Collection<T> collection = new ArrayList<>(1024);

			for (T item : items) {
				collection.add(item);
			}

			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[collection.size()];
			values = temp;

			collection.toArray(values);
		}

		if (values.length == 0) {
			throw new IllegalArgumentException("At least one item must be available");
		}

		if (choose > values.length) {
			throw new IllegalArgumentException("Items to choose must be no greater than total items (" + values.length + "); got " + choose);
		}

		int[] indices = new int[choose];
		for (int i = 0; i < choose; i++) {
			indices[i] = i;
		}

		indices[choose - 1]--;

		return StreamSupport.stream(new SplitlessSpliterator<List<T>>() {
			@Override
			public boolean tryAdvance(Consumer<? super List<T>> action) {
				int adjustmentIndex;
				for (adjustmentIndex = choose - 1; adjustmentIndex >= 0; adjustmentIndex--) {
					int index = indices[adjustmentIndex];

					if (index < values.length - (choose - adjustmentIndex)) {
						indices[adjustmentIndex] = index + 1;
						break;
					}
				}

				if (adjustmentIndex < 0) {
					return false;
				}

				for (int i = adjustmentIndex + 1; i < choose; i++) {
					indices[i] = indices[i - 1] + 1;
				}

				List<T> combination = new ArrayList<>(choose);

				for (int index: indices) {
					combination.add(values[index]);
				}

				action.accept(combination);
				return true;
			}
		}, false);
	}
}
