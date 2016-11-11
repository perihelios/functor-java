package com.perihelios.math.functor;

import java.util.SortedMap;
import java.util.TreeMap;

public class TestUtil {
	public static <K, V> SortedMap<K, V> treeMap(K key, V value, Object... others) {
		TreeMap<K, V> map = new TreeMap<>();
		map.put(key, value);

		int length = others.length;
		for (int i = 0; i < length; i++) {
			@SuppressWarnings("unchecked")
			K otherKey = (K) others[i];
			i++;

			if (i == length) {
				throw new IllegalArgumentException("Found key " + otherKey + " without corresponding value");
			}

			@SuppressWarnings("unchecked")
			V otherValue = (V) others[i];

			map.put(otherKey, otherValue);
		}

		return map;
	}

	// Prevent instantiation of this class
	private TestUtil() {}
}
