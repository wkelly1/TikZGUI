package org.tikzgui.core;

import java.util.Optional;

public abstract class Property<T> extends TeXElement {
	T defaultVal;
	T value;
	String latexId;
	String name;
	
	protected boolean inherit = true; // should this property inherit its value from parents/take the default value

	public abstract String getValueString();
	public String getName() {
		return name;
	}
	
	protected Property(T defaultVal, String latexId, String name) {
		this.name = name; 
		this.latexId = latexId;
		this.defaultVal = defaultVal;
	} //inherit
	
	protected Property(T value, T defaultVal, String latexId, String name) {
		inherit=false;
		this.value=value;
		this.latexId = latexId;
		this.name = name;
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
