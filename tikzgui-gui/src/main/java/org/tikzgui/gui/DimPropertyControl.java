package org.tikzgui.gui;

import org.tikzgui.core.*;


import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DimPropertyControl extends PropertyControl<Double> {
	private Spinner<Double> spinner = new Spinner<Double>(new SpinnerValueFactory.DoubleSpinnerValueFactory(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0));
	
	public DimPropertyControl(DimProperty property) {
		super(property);
		this.container.getChildren().add(spinner);
	}
}
