package com.perihelios.math.functor;

public class Pair<L, R> {
	final L left;
	final R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != Pair.class) return false;

		Pair o = (Pair) obj;

		return o.left.equals(left) && o.right.equals(right);
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}
}
