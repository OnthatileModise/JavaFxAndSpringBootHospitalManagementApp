package com.onthatile.app.heatthcarespringfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CheckOutPatientController {
    @FXML
    public Label checkout_patientID;
    @FXML
    public Label checkout_name;
    @FXML
    public Label checkout_doctor;
    @FXML
    public ImageView checkout_imageView;
    @FXML
    public DatePicker checkout_date;
    @FXML
    public DatePicker checkout_checkout;
    @FXML
    public Button checkout_countBtn;
    @FXML
    public Label checkout_totalDays;
    @FXML
    public Label checkout_totalPrice;
    @FXML
    public Button checkout_payBtn;

    public void countBtn(ActionEvent actionEvent) {
    }

    public void payBtn(ActionEvent actionEvent) {
    }
}
