package org.tikzgui.core;

import java.util.Optional;

public abstract class Property<T> extends TeXElement {
	T defaultVal;
	T value;
	
	protected boolean inherit = true; // should this property inherit its value from parents/take the default value
	
	protected Property(T defaultVal) {} //inherit
	protected Property(T value, T defaultVal) {
		inherit=false;
		this.value=value;
	}
	
	public T getDefault() {
		return defaultVal;
	}
	
	public Optional<T> get() {
		if (!inherit)
			return Optional.of(value);
		else
			return Optional.empty();
	}
	
	public void set(T value) {
		inherit=false;
		this.value = value;
	}
	
	public void setInherit() {
		inherit=true;
	}
}
