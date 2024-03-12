package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.DoctorsBean;
import com.onthatile.app.heatthcarespringfx.beans.PatientBean;
import com.onthatile.app.heatthcarespringfx.data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EditDoctorFormController implements Initializable {
    @FXML
    public TextField editDoctor_fullName;
    @FXML
    public TextField editDoctor_email;
    @FXML
    public TextField editDoctor_password;
    @FXML
    public ComboBox<String> editDoctor_specialized;
    @FXML
    public ComboBox<String> editDoctor_gender;
    @FXML
    public TextField editDoctor_mobileNumber;
    @FXML
    public ImageView editDoctor_imageView;
    @FXML
    public Button editDoctor_importBtn;
    @FXML
    public TextArea editDoctor_address;
    @FXML
    public ComboBox<String> editDoctor_status;
    @FXML
    public Button editDoctor_updateBtn;
    @FXML
    public Button editDoctor_cancelBtn;

    private final AlertMessage alertMessage = new AlertMessage();

    public void importBtn() {
    }

    public void displayDoctorData(){
        editDoctor_fullName.setText(Data.temp_doctorName);
        editDoctor_email.setText(Data.temp_doctorEmail);
        editDoctor_password.setText("");
        editDoctor_specialized.getSelectionModel().select(Data.temp_doctorSpecialized);
        editDoctor_gender.getSelectionModel().select((Data.temp_doctorGender));
        editDoctor_mobileNumber.setText(Data.temp_doctorMobileNumber);
        editDoctor_address.setText(Data.temp_doctorAddress);
        editDoctor_status.getSelectionModel().select(Data.temp_doctorStatus);
    }

    public void updateBtn() {
        if (alertMessage.confirmationMessage("Are you sure you want to update this patient")) {
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = null;
            DoctorsBean doctorsBean = new DoctorsBean();
            doctorsBean.setId(Integer.parseInt(Data.temp_doctorID));
            doctorsBean.setName(editDoctor_fullName.getText());
            doctorsBean.setEmailAddress(editDoctor_address.getText());
            doctorsBean.setSpecialization(editDoctor_specialized.getSelectionModel().getSelectedItem());
            doctorsBean.setGender(editDoctor_gender.getSelectionModel().getSelectedItem());
            doctorsBean.setContactNumber(editDoctor_mobileNumber.getText());
            doctorsBean.setAddress(editDoctor_address.getText());
            doctorsBean.setStatus(editDoctor_status.getSelectionModel().getSelectedItem());
            String body = "";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                body = objectMapper.writeValueAsString(doctorsBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpRequest = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .uri(new URI("http://localhost:8084/api/v1/updateDoctor"))
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            if (httpRequest != null) {
                try {
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                } catch (IOException | InterruptedException exception) {
                    alertMessage.errorMessage("Operation failed please contact system admin for assistance");
                    exception.printStackTrace();
                }
            }
        }else {
            alertMessage.errorMessage("Canceled");
        }
    }

    public void cancelBtn() {
        displayDoctorData();
    }

    public void specializationList() {
        List<String> specializationL = new ArrayList<>();

        Collections.addAll(specializationL, Data.specialization);

        ObservableList<String> listData = FXCollections.observableList(specializationL);
        editDoctor_specialized.setItems(listData);
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        Collections.addAll(genderL, Data.gender);

        ObservableList<String> listData = FXCollections.observableList(genderL);
        editDoctor_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        Collections.addAll(statusL, Data.status);

        ObservableList<String> listData = FXCollections.observableList(statusL);
        editDoctor_status.setItems(listData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayDoctorData();
        specializationList();
        statusList();
        genderList();
    }
}
