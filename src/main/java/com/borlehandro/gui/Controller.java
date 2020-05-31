package com.borlehandro.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Controller {

    @FXML
    private Slider testSlider;

    @FXML
    private Label testLabel;

    public void initialize() {
        testSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.err.println((Double.toString(newValue.intValue())));
            testLabel.setText(Double.toString(newValue.intValue()));
        });
    }
}