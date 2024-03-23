// Copyright 2013 Thomas Müller
// This file is part of MarMoT, which is licensed under GPLv3.

package marmot.util;

import java.security.InvalidParameterException;


public class CollectableDouble implements Collectable {

	private static final long serialVersionUID = 1L;
	double d_;
	
	public CollectableDouble(double d) {
		d_ = d;
	}
	
	@Override
	public void add(Collectable other) {
		if (! (other instanceof CollectableDouble other_integer)) {
			throw new InvalidParameterException();
		}

        d_ += other_integer.getValue();
	}
	
	public double getValue() {
		return d_;
	}

	@Override
	public int sum() {
		return (int) d_;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result + Double.hashCode(d_);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectableDouble other = (CollectableDouble) obj;
		if (Double.doubleToLongBits(d_) != Double.doubleToLongBits(other.d_))
			return false;
		return true;
	}

	@Override
	public Collectable copy() {
		return new CollectableDouble(d_);
	}

}
