package com.perihelios.math.functor;

import java.lang.reflect.Field;
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

	public static Object getField(Object instance, String fieldName) {
		Class<?> type = instance.getClass();

		while (type != Object.class) {
			Field[] declaredFields = type.getDeclaredFields();

			for (Field declaredField : declaredFields) {
				if (declaredField.getName().equals(fieldName)) {
					declaredField.setAccessible(true);
					try {
						return declaredField.get(instance);
					} catch (IllegalAccessException e) {
						throw new RuntimeException("Failed to obtain value from field " + fieldName + " on instance " + instance);
					}
				}
			}

			type = type.getSuperclass();
		}

		throw new IllegalArgumentException("No field named " + fieldName + " found on instance of class " + instance.getClass().getName() +
			" or any of its superclasses");
	}

	// Prevent instantiation of this class
	private TestUtil() {}
}
