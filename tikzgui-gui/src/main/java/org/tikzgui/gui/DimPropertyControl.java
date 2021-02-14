package org.tikzgui.gui;

import org.tikzgui.core.*;


import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DimPropertyControl extends PropertyControl<Double> {
	private Spinner<Double> spinner;
	
	public DimPropertyControl(DimProperty property, Runnable update) {
		super(property, update);
		spinner = new Spinner<Double>(new SpinnerValueFactory.DoubleSpinnerValueFactory(-999999.0, 999999.0, property.get().orElse(-1.0)));
		spinner.setEditable(true);
		this.container.getChildren().add(spinner);

		spinner.getValueFactory().valueProperty().addListener((observableValue, oldValue, newValue) -> {
			this.property.set(newValue);
			update.run();
		});
	}
}
