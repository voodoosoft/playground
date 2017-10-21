/*
 * VOODOOSOFT SOFTWARE LICENSE
 *
 * Copyright (c) 2002-2010, Stefan Wischnewski, www.voodoosoft.de
 * All rights reserved.
 *
 * You are granted the right to use, modify and redistribute this software
 * provided that one of the following conditions is met:
 *
 * (a) your project is open source licensed under one of the approved licenses of
 *     the Open Source Initiative (www.opensource.org)
 * (b) you did purchase a commercial license from the copyright holder
 * (c) you have any other special agreement with the copyright holder
 *
 * In either case, redistribution and use in source and binary forms, with or
 * without modification, is only permitted provided that:
 *
 * (a) redistributions of source code retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 * (b) neither the name of the copyright holder nor the names of its contributors
 *     may be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER AND CONTRIBUTORS IN THE HOPE
 * THAT IT WILL BE USEFUL, BUT WITHOUT ANY WARRANTY; WITHOUT EVEN THE IMPLIED
 * WARRANTY OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 */
package de.voodoosoft.gameroots.shared.geom;

import java.io.Serializable;



public final class Vector implements Cloneable, Serializable {

	private static final long serialVersionUID = -8242684335592375921L;

	public static final Vector ZERO = new Vector(0, 0, 0);
	public static final Vector UNDEFINED = new Vector();
	public static final Vector MAX = new Vector(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);

	public Vector() {
		x = Float.NaN;
		y = Float.NaN;
		z = Float.NaN;
		undefined = true;
	}

	public Vector(Vector source) {
		x = source.x;
		y = source.y;
		z = source.z;
		undefined = source.undefined;
	}

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	@Override
	public Vector clone() {
		Vector clone = new Vector(this);
		return clone;
	}



	public Vector addVector(float dx, float dy, float dz) {
		return new Vector(x + dx, y + dy, z + dz);
	}

	public Vector addVector(Vector add) {
		return new Vector(x + add.x, y + add.y, z + add.z);
	}

	public Vector subtractVector(Vector add) {
		return new Vector(x - add.x, y - add.y, z - add.z);
	}

	public Vector subtractVector(float dx, float dy, float dz) {
		return new Vector(x - dx, y - dy, z - dz);
	}

	public float dot(Vector v2) {
		return this.x * v2.x + this.y * v2.y + this.z * v2.z;
	}

	public Vector inverse() {
		float xi = x != 0 ? -x : 0;
		float yi = y != 0 ? -y : 0;
		float zi = z != 0 ? -z : 0;
		return new Vector(xi, yi, zi);
	}

	public Vector scale(float dx, float dy, float dz) {
		return new Vector(x * dx, y * dy, z * dz);
	}

	public boolean isZero() {
		return (x == 0.0 && y == 0.0 && z == 0.0);
	}

	public boolean isUndefined() {
		return undefined;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Vector))
			return false;

		Vector otherTriple = (Vector)other;

		if (this.undefined && otherTriple.undefined) {
			return true;
		}

		return (x == otherTriple.x && y == otherTriple.y && z == otherTriple.z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);

		return result;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(100);

		buf.append(getX());
		buf.append(":");
		buf.append(getY());
		if (getZ() != 0) {
			buf.append(":");
			buf.append(getZ());
		}

		return buf.toString();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	private boolean undefined;
	private final float x;
	private final float y;
	private final float z;
}
