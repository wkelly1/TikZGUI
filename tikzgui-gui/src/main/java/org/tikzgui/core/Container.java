package org.tikzgui.core;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Container {
	private ArrayList<PropertySet> properties;

	public Container(ArrayList<PropertySet> properties) {
		this.properties = properties;
	}

	public Container(PropertySet[] propertiesArr) {
		this.properties = new ArrayList<PropertySet> (Arrays.asList(propertiesArr));
	}

	public Container (PropertySet properties) {
		this.properties = new ArrayList<PropertySet>();
		this.properties.add(properties);
	}

	public Container() {
		properties = new ArrayList<PropertySet>();
	}

	//returns the children contained within this container
	public abstract GraphicsObject[] getChildren();

	public ArrayList<PropertySet> getProperties() {
		return properties;
	}

	public void addProperties (PropertySet newProperties) {
		properties.add(newProperties);
	}

	public void removeProperties (PropertySet properties) {
		this.properties.remove(properties);
	}
}
