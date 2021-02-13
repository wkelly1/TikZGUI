package org.tikzgui.core;

public abstract class PropertySet extends TeXElement {
	//returns all the properties contained within the set
	public abstract Property<?>[] getProperties();
}
