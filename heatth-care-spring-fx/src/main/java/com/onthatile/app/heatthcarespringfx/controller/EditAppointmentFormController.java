package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.DoctorsBean;
import com.onthatile.app.heatthcarespringfx.data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EditAppointmentFormController implements Initializable {
    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private TextField editApp_fullName;

    @FXML
    private ComboBox<String> editApp_gender;

    @FXML
    private TextField editApp_mobileNumber;

    @FXML
    private TextArea editApp_address;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private Button editApp_cancelBtn;

    @FXML
    private TextArea editApp_description;

    @FXML
    private TextField editApp_diagnosis;

    @FXML
    private TextField editApp_treatment;

    @FXML
    private ComboBox<String> editApp_doctor;

    @FXML
    private ComboBox<String> editApp_specialized;

    @FXML
    private ComboBox<String> editApp_status;

    AlertMessage alertMessage = new AlertMessage();

    public void displayFields(){
        editApp_appointmentID.setText(Data.temp_appID);
        editApp_fullName.setText(Data.temp_appName);
        editApp_gender.getSelectionModel().select(Data.temp_appGender);
        editApp_mobileNumber.setText(Data.temp_appMobileNumber);
        editApp_address.setText(Data.temp_appAddress);
        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
        editApp_doctor.getSelectionModel().select((Data.temp_appDoctor).getId());
        editApp_specialized.getSelectionModel().select((Data.temp_appSpecialized));
        editApp_status.getSelectionModel().select(Data.temp_appStatus);
    }

    public void doctorList(){
        ObservableList<String> doctorsBeanObservableList = FXCollections.observableArrayList();
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("http://localhost:8084/api/v1/getAllDoctors"))
                    .header("Content-Type" , "application/json")
                    .header("Accept", "application/json")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        HttpResponse<String> response;
        if (httpRequest != null){
            try {
                response = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
                List<DoctorsBean> doctorsBeans = new ObjectMapper().readValue(response.body(), new TypeReference<>() {});
                for(DoctorsBean doctorsBean : doctorsBeans){
                    doctorsBeanObservableList.add(doctorsBean.getName());
                }
                editApp_doctor.setItems(doctorsBeanObservableList);
//                specializedList();
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void specializedList(){
        ObservableList<DoctorsBean> doctorsBeanObservableList = FXCollections.observableArrayList();
        String body = "{ " + "\"specialization\" :" + "\"" + editApp_doctor.getSelectionModel().getSelectedItem() + "\"" + " }";
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .uri(new URI("http://localhost:8084/api/v1/getDoctorsBySpecialization"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        HttpResponse<String> response;
        if (httpRequest != null){
            try {
                response = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
                List<DoctorsBean> doctorsBeans = new ObjectMapper().readValue(response.body(), new TypeReference<>() {});
                doctorsBeanObservableList.addAll(doctorsBeans);
                ObservableList<String> specializations = FXCollections.observableArrayList();
                for(DoctorsBean doctorsBean : doctorsBeans){
                    specializations.add(doctorsBean.getSpecialization());
                }
                editApp_specialized.setItems(specializations);
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void genderList(){
        List<String> genderL = new ArrayList<>(Arrays.asList(Data.gender));
        ObservableList<String> listData = FXCollections.observableList(genderL);
        editApp_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>(Arrays.asList(Data.status));
        ObservableList<String> listData = FXCollections.observableList(statusL);
        editApp_status.setItems(listData);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorList();
        genderList();
        statusList();
        displayFields();
    }
}
