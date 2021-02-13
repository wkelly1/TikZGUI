package org.tikzgui.core;

import java.util.ArrayList;

public class PictureContainer extends Container {

	ArrayList<GraphicsObject> children = new ArrayList<GraphicsObject>();

	@Override
	public GraphicsObject[] getChildren() {
		GraphicsObject[] childrenArr = new GraphicsObject[children.size()];
		childrenArr = children.toArray(childrenArr);
		return childrenArr;
	}

	//adds a new GraphicsObject to the container
	public void addChild(GraphicsObject object) {
		children.add(object);
	}


	//removes a GraphicsObject from the container
	public void removeChild(GraphicsObject object) {
		children.remove(object);
	}
	
}
