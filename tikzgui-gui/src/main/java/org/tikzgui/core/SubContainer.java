package org.tikzgui.core;

import java.util.ArrayList;

public abstract class SubContainer extends Container {
	private Container parent;

	public SubContainer (Container parent, GeneralProperties properties) {
		super(properties);
	}

	public Container getParent() {
		return parent;
	}


	//gets this objects properties, combined with those of its parent
	@Override
	public ArrayList<PropertySet> getProperties() {

		ArrayList<PropertySet> allProperties = new ArrayList<>(super.getProperties());
		allProperties.addAll(parent.getProperties());
		return allProperties;
	}

}
