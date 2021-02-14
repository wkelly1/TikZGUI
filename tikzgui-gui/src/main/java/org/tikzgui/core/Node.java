package org.tikzgui.core;

public class Node extends GraphicsObject {
	private GeneralProperties genProps;
	String name;
	Point position;
	String contents;
	
	public Node(Container parent, GeneralProperties genProps, String name, Point position, String contents) {
		super(parent);
		this.genProps = genProps;
		this.name = name;
		this.position = position;
		this.contents = contents;
	}

	@Override
	protected PropertySet[] getLocalProperties() {
		return new PropertySet[] {genProps};
	}

	public GeneralProperties getGenProps() {
		return genProps;
	}

	public void setGenProps(GeneralProperties genProps) {
		this.genProps = genProps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
