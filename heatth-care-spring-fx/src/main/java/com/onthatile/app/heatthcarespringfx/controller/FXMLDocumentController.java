package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.beans.LoginBean;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Component
public class FXMLDocumentController {
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
    public ComboBox<?> login_user;
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

    public void loginShowPassword(ActionEvent actionEvent) {
    }

    public void switchPage(ActionEvent actionEvent) {
    }

    public void switchForm(ActionEvent actionEvent) {
    }

    public void registerShowPassword(ActionEvent actionEvent) {
    }

    public void registerAccount(ActionEvent actionEvent) {
    }
}
