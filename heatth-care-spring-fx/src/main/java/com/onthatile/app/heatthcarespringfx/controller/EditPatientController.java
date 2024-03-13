package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.data.Data;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.PatientBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditPatientController implements Initializable {
    @FXML
    public AnchorPane main_form;
    @FXML
    public TextField edit_patientID;
    @FXML
    public TextField edit_name;
    @FXML
    public ComboBox<String> edit_gender;
    @FXML
    public TextField edit_contactNumber;
    @FXML
    public TextArea edit_address;
    @FXML
    public ComboBox<String> edit_status;
    @FXML
    public Button edit_updateBtn;

    private final AlertMessage alert = new AlertMessage();

    public void updateBtn() {
        if (alert.confirmationMessage("Are you sure you want to update this patient")) {


            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = null;
            PatientBean patientBean = new PatientBean();
            patientBean.setId(Integer.parseInt(String.valueOf(edit_patientID.getText())));
            patientBean.setName(String.valueOf(edit_name.getText()));
            patientBean.setSurname(Data.temp_patient_surname);
            patientBean.setCellPhoneNumber(String.valueOf(edit_contactNumber.getText()));
            patientBean.setTelPhoneNumber(String.valueOf(Data.temp_patient_telnumber));
            patientBean.setAddress(String.valueOf(edit_address.getText()));
            patientBean.setAppointmentId(Integer.parseInt(String.valueOf(Data.temp_patient_appointmentId)));
            patientBean.setGender(String.valueOf(edit_gender.getSelectionModel().getSelectedItem()));
            patientBean.setDescription(Data.temp_patient_description);
            patientBean.setDateDeleted(Data.temp_patient_date_deleted);
            patientBean.setDateModified(Data.temp_patient_date_modified);
            patientBean.setDate(Data.temp_patient_date);
            patientBean.setStatus(String.valueOf(edit_status.getSelectionModel().getSelectedItem()));
            String body = "";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                body = objectMapper.writeValueAsString(patientBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpRequest = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .uri(new URI("http://localhost:8084/api/v1/updatePatient"))
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
                    alert.errorMessage("Operation failed please contact system admin for assistance");
                    exception.printStackTrace();
                }
            }
        }else {
            alert.errorMessage("Canceled");
        }
    }

    // CLOSE THE EDITPATIENTFORM FXML FILE AND OPEN IT AGAIN
    public void setField() {
        edit_patientID.setText(String.valueOf(Data.temp_PatientID));
        edit_name.setText(Data.temp_patient_name);
        edit_gender.getSelectionModel().select(Data.temp_patient_gender);
        edit_contactNumber.setText(String.valueOf(Data.temp_patient_number));
        edit_address.setText(Data.temp_patient_address);
        edit_status.getSelectionModel().select(Data.temp_patient_status);
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();
        Collections.addAll(genderL, Data.gender);
        ObservableList<String> listData = FXCollections.observableList(genderL);
        edit_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>(Arrays.asList(Data.status));
        ObservableList<String> listData = FXCollections.observableList(statusL);
        edit_status.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setField();
        genderList();
        statusList();
    }
}
