package org.tikzgui.core;

import java.util.Optional;

public abstract class Property<T> extends TeXElement {
	T value;
	
	protected boolean inherit = false; // should this property inherit its value from parents/take the default value
	
	public Property() {} //inherit
	public Property(T value) {
		this.value=value;
	}
	
	public Optional<T> get() {
		if (inherit)
			return Optional.of(value);
		else
			return Optional.empty();
	}
	
	public void set(T value) {
		this.value = value;
	}
}
