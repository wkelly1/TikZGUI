package org.tikzgui.core;

public abstract class GraphicsObject extends TeXElement {
	protected Container parent;
	public abstract PropertySet[] getProperties();

	public GraphicsObject (Container parent) {
		this.parent = parent;
	}

	public Container getParent() {
		return parent;
	}

	
}
