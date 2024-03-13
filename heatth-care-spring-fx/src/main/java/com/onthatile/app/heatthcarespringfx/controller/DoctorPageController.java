package com.onthatile.app.heatthcarespringfx.controller;

import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.UserBeans;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DoctorPageController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_doctorID;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    @FXML
    private ComboBox<String> login_user;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullName;

    @FXML
    private TextField register_email;

    @FXML
    private TextField register_doctorID;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private Button register_signupBtn;

    @FXML
    private Hyperlink register_loginHere;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void loginAccount(){

    }

    @FXML
    void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_showPassword.setVisible(true);
        } else {
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }

    @FXML
    void registerAccount(){

    }

    @FXML
    void registerShowPassword() {
        if (register_checkBox.isSelected()) {
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }
    }

    public void registerDoctorID(){

    }

    public void userList(){
        List<String> listU = new ArrayList<>(Arrays.asList(UserBeans.user));
        ObservableList<String> listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage(){
        if (login_user.getSelectionModel().getSelectedItem().equals("Admin Portal")) {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLDocument.fxml")));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (login_user.getSelectionModel().getSelectedItem().equals("Doctor Portal")) {

            try {

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("DoctorPage.fxml")));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (login_user.getSelectionModel().getSelectedItem().equals("Patient Portal")) {
            try {

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("PatientPage.fxml")));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        login_user.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerDoctorID();
        userList();
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == register_loginHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);
        } else if (event.getSource() == login_registerHere) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        }
    }
}
