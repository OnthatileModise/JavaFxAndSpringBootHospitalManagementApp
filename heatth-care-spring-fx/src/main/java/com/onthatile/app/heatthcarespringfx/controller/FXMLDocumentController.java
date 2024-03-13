package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.beans.LoginBean;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.UserBeans;
import com.onthatile.app.heatthcarespringfx.data.Data;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class FXMLDocumentController implements Initializable {
    @FXML
    public TextField login_username;
    @FXML
    public AnchorPane main_form;
    @FXML
    public AnchorPane login_form;
    @FXML
    public PasswordField login_password;
    @FXML
    public TextField login_showPassword;
    @FXML
    public CheckBox login_checkBox;
    @FXML
    public Button login_loginBtn;
    @FXML
    public ComboBox<String> login_user;
    @FXML
    public Hyperlink login_registerHere;
    @FXML
    public AnchorPane register_form;
    @FXML
    public TextField register_email;
    @FXML
    public TextField register_username;
    @FXML
    public PasswordField register_password;
    @FXML
    public TextField register_showPassword;
    @FXML
    public CheckBox register_checkBox;
    @FXML
    public Button register_signupBtn;
    @FXML
    public Hyperlink register_loginHere;
    private final AlertMessage alert = new AlertMessage();

    public void loginAccount() {
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()){
            alert.errorMessage("Please enter a username or password");
        }else {
            String body = "{ " + "\"name\": " + "\""+ login_username.getText() + "\""  + "," + "\"password\": " + "\"" +login_password.getText() +"\"" + " }";
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = null;
            try {
                httpRequest = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .uri(new URI("http://localhost:8084/api/v1/performLogin"))
                        .header("Content-Type" , "application/json")
                        .header("Accept", "application/json")
                        .build();
            }catch (URISyntaxException e){
                e.printStackTrace();
            }
            HttpResponse<String> response = null;
            if (httpRequest != null){
                try {
                    response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    if (response.body() != null){
                        LoginBean loginBean = new ObjectMapper().readValue(response.body(), LoginBean.class);
                        Data.admin_id = loginBean.getId();
                        Data.admin_username = loginBean.getName() + " " + loginBean.getSurname();
                        Data.loginBean = loginBean;
                        alert.successMessage("Login successful");
                        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("AdminMainForm.fxml")));
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent);
                        scene.getStylesheets().add( Objects.requireNonNull(getClass().getClassLoader().getResource("MainFormDesign.css")).toExternalForm());
                        scene.getStylesheets().add( Objects.requireNonNull(getClass().getClassLoader().getResource("PageDesign.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("PatientMainForm.css")).toExternalForm());
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("DoctorMainFormDesign.css")).toExternalForm());
                        stage.setTitle("Admin Portal");
                        stage.setScene(scene);
                        stage.show();
                        login_loginBtn.getScene().getWindow().hide();
                    }
                }catch (IOException | InterruptedException e){
                    alert.errorMessage("Incorrect Username / Password");
                }
            }
        }
    }

    public void userList() {
        List<String> listU = new ArrayList<>(Arrays.asList(UserBeans.user));
        ObservableList<String> listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void loginShowPassword() {
        if (login_checkBox.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void switchPage() {
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

        } else if (login_user.getSelectionModel().getSelectedItem().equals("Doctor Portal")) {
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

    public void switchForm(ActionEvent actionEvent) {
        if (actionEvent.getSource() == login_registerHere) {
            // REGISTRATION FORM WILL SHOW
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (actionEvent.getSource() == register_loginHere) {
            // LOGIN FORM WILL SHOW
            login_form.setVisible(true);
            register_form.setVisible(false);
        }
    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword() {
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

    public void registerAccount() {
        if (register_email.getText().isEmpty() || register_username.getText().isEmpty() || register_password.getText().isEmpty())
        {
            alert.errorMessage("Please fill all blank fields");
        }else {
            //Usage of a bean instead of a string
            String body = "{ " + "\"id\": " + "\"" + 0 + "\"" +
                    "," + "\"name\": " + "\"" + register_username.getText() + "\""
                    + "," + "\"surname\":" + "\""  + register_username.getText() + "\""
                    + "," + "\"email\":" + "\""  + register_email.getText() + "\""
                    + "," + "\"password\":" + "\""  + register_password.getText() + "\""
                    + "," + "\"role\":" + "\"" + "" + "\""
                    + " }";
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = null;
            try {
                httpRequest = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .uri(new URI("http://localhost:8084/api/v1/registerUser")) //API request to be a environment variable for easier configuration
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            HttpResponse<String> response = null;
            if (httpRequest != null) {
                try {
                    response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            assert response != null;
            if (response.body() != null) {
                alert.successMessage("Registration Successful!");
                registerClear();
                // TO SWITCH THE FORM INTO LOGIN FORM
                login_form.setVisible(true);
                register_form.setVisible(false);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();
    }
}
