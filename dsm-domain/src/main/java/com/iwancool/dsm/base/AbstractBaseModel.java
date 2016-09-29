
package com.iwancool.dsm.base;

import java.io.Serializable;


@SuppressWarnings("rawtypes")
public abstract class AbstractBaseModel implements Serializable, Comparable, Cloneable {

	private static final long serialVersionUID = -1875661261506118607L;

	public int compareTo(Object arg0) {
		return 0;
	}

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
