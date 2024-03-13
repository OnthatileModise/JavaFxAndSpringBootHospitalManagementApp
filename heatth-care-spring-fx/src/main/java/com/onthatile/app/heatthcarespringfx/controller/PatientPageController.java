package com.onthatile.app.heatthcarespringfx.controller;

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

public class PatientPageController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_patientID;

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

    public void userList() {
        List<String> listU = new ArrayList<>(Arrays.asList(UserBeans.user));
        ObservableList<String> listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }
    @FXML
    void loginShowPassword(ActionEvent event) {}
    @FXML
    void loginAccount(ActionEvent event) {}

    @FXML
    void switchPage() {
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

        } else if (login_user.getSelectionModel().getSelectedItem().equals("Doctor Portal")){
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

        } else if (login_user.getSelectionModel().getSelectedItem().equals("Patient Portal")) {
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();
    }
}
