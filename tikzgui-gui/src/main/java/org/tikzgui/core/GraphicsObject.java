package org.tikzgui.core;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GraphicsObject extends TeXElement {
	protected Container parent;
	protected abstract PropertySet[] getLocalProperties();

	public GraphicsObject (Container parent) {
		this.parent = parent;
	}

	public Container getParent() {
		return parent;
	}

	public ArrayList<PropertySet> getProperties() {
		return new ArrayList<PropertySet> (Arrays.asList(getLocalProperties()));
	}
}
