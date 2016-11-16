package com.perihelios.math.functor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Permutations {
	public static <T> Stream<List<T>> permutations(int choose, Iterable<T> items) {
		if (choose < 1) {
			throw new IllegalArgumentException("Items to choose must be greater than 0; got " + choose);
		}

		return StreamSupport.stream(new SplitlessSpliterator<List<T>>() {
			private Ringutator<T> ringutator = new Ringutator<>(items, choose);

			@Override
			public boolean tryAdvance(Consumer<? super List<T>> action) {
				if (ringutator == null) {
					return false;
				}

				List<T> values = new ArrayList<>(choose);

				if (!ringutator.fill(values)) {
					ringutator = null;
				}

				action.accept(values);
				return true;
			}
		}, false);
	}

	private static class Ringutator<T> {
		private final long size;
		private final long choose;

		private Node<T> current;
		private long remaining;
		private Ringutator<T> nextLevel;

		public Ringutator(Iterable<T> items, long choose) {
			Iterator<T> iterator = items.iterator();

			if (!iterator.hasNext()) {
				throw new IllegalArgumentException("At least one item must be available");
			}

			Node<T> first;
			Node<T> last;

			long size = 1L;
			first = last = new Node<>(iterator.next());

			while (iterator.hasNext()) {
				Node<T> next = new Node<>(iterator.next());
				last.next = next;
				next.previous = last;
				last = next;

				size++;
			}

			if (choose > size) {
				throw new IllegalArgumentException("Items to choose must be no greater than total items (" + size + "); got " + choose);
			}

			last.next = first;
			first.previous = last;

			this.size = size;
			this.choose = choose;
			this.remaining = size;
			this.current = first;

			if (choose > 1L) {
				first.next.previous = last;
				last.next = first.next;
				remaining--;

				nextLevel = new Ringutator<>(first.next, size - 1L, choose - 1L);
			}
		}

		private Ringutator(Node<T> head, long size, long choose) {
			this.size = size;
			this.remaining = size;
			this.choose = choose;
			this.current = head;

			if (choose > 1L) {
				head.next.previous = head.previous;
				head.previous.next = head.next;
				remaining--;

				nextLevel = new Ringutator<>(head.next, size - 1L, choose - 1L);
			}
		}

		public boolean fill(List<T> list) {
			list.add(current.value);

			if (nextLevel != null) {
				if (!nextLevel.fill(list)) {
					if (remaining > 0L) {
						current.previous.next = current;
						current.next.previous = current;

						current = current.next;
						current.next.previous = current.previous;
						current.previous.next = current.next;

						remaining--;

						nextLevel = new Ringutator<>(current.next, size - 1L, choose - 1L);

						return true;
					} else {
						current.previous.next = current;
						current.next.previous = current;

						return false;
					}
				} else {
					return true;
				}
			} else {
				current = current.next;
				remaining--;

				return remaining > 0L;
			}
		}

		private static class Node<T> {
			final T value;
			Node<T> next;
			Node<T> previous;

			private Node(T value) {
				this.value = value;
			}
		}
	}
}
