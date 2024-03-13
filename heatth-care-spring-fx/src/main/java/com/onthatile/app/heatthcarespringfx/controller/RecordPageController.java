package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.PatientBean;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.geometry.Pos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class RecordPageController implements Initializable {
    @FXML
    public AnchorPane record_page_mainForm;
    @FXML
    public TextField record_page_search;
    @FXML
    public TableView<PatientBean> record_page_tableView;
    @FXML
    public TableColumn<? , ?> record_page_col_patientID;
    @FXML
    public TableColumn<? , ?> record_page_col_name;
    @FXML
    public TableColumn<? , ?> record_page_col_gender;
    @FXML
    public TableColumn<? , ?> record_page_col_mobileNumber;
    @FXML
    public TableColumn<? , ?> record_page_col_address;
    @FXML
    public TableColumn<? , ?> record_page_col_dateCreated;
    @FXML
    public TableColumn<? , ?> record_page_col_dateModified;
    @FXML
    public TableColumn<? , ?> record_page_col_dateDeleted;
    @FXML
    public TableColumn<PatientBean, String> record_page_col_action;

    private final AlertMessage alert = new AlertMessage();

    public ObservableList<PatientBean> patientRecordData;

    public ObservableList<PatientBean> getPatientsRecordData(){
        ObservableList<PatientBean> listData = FXCollections.observableArrayList();
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("http://localhost:8084/api/v1/getAllPatients"))
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
                List<PatientBean> patientBeanList = new ObjectMapper().readValue(response.body(), new TypeReference<>() {
                });
                listData.addAll(patientBeanList);
            }catch (IOException | InterruptedException exception){
                exception.printStackTrace();
            }
        }
        return listData;
    }

    public void displayPatientsData() {
        patientRecordData = getPatientsRecordData();

        record_page_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        record_page_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        record_page_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        record_page_col_mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        record_page_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        record_page_col_dateCreated.setCellValueFactory(new PropertyValueFactory<>("date"));
        record_page_col_dateModified.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        record_page_col_dateDeleted.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));

        record_page_tableView.setItems(patientRecordData);
    }

    public void actionButtons() {

        patientRecordData = getPatientsRecordData();

        Callback<TableColumn<PatientBean, String>, TableCell<PatientBean, String>> cellFactory = (TableColumn<PatientBean, String> param) -> new TableCell<>() {
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Button editButton = new Button("Edit");
                    Button removeButton = new Button("Delete");

                    editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                            + "    -fx-cursor: hand;\n"
                            + "    -fx-text-fill: #fff;\n"
                            + "    -fx-font-size: 14px;\n"
                            + "    -fx-font-family: Arial;");

                    removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
                            + "    -fx-cursor: hand;\n"
                            + "    -fx-text-fill: #fff;\n"
                            + "    -fx-font-size: 14px;\n"
                            + "    -fx-font-family: Arial;");

                    editButton.setOnAction((ActionEvent event) -> {
                        try {

                            PatientBean pData = record_page_tableView.getSelectionModel().getSelectedItem();
                            int num = record_page_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            Data.temp_PatientID = pData.getId();
                            Data.temp_patient_name = pData.getName();
                            Data.temp_patient_gender = pData.getGender();
                            Data.temp_patient_telnumber = pData.getCellPhoneNumber();
                            Data.temp_patient_address = pData.getAddress();
                            Data.temp_patient_status = pData.getStatus();
                            // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("EditPatientForm.fxml")));
                            Stage stage = new Stage();

                            stage.setScene(new Scene(root));
                            stage.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    removeButton.setOnAction((ActionEvent event) -> {
                        PatientBean pData = record_page_tableView.getSelectionModel().getSelectedItem();
                        int num = record_page_tableView.getSelectionModel().getSelectedIndex();

                        if ((num - 1) < -1) {
                            alert.errorMessage("Please select item first");
                            return;
                        }

                        try {
                            if (alert.confirmationMessage("Are you sure you want to delete Patient ID: " + pData.getId() + "?")) {
                                if ((num - 1) < 1) {
                                    alert.errorMessage("Please select an item first");
                                }
                                //TODO endpoint to delete a patient
                                HttpClient httpClient = HttpClient.newBuilder().build();
                                HttpRequest httpRequest = null;
                                try {
                                    httpRequest = HttpRequest.newBuilder()
                                            .POST(HttpRequest.BodyPublishers.ofString("{\"id\"" + ":" + pData.getId() + "}"))
                                            .uri(new URI("http://localhost:8084/api/v1/deletePatient"))
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
                                    } catch (IOException | InterruptedException exception) {
                                        exception.printStackTrace();
                                    }
                                }
                                alert.successMessage("Deleted Successfully!");
                                displayPatientsData();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    HBox manageBtn = new HBox(editButton, removeButton);
                    manageBtn.setAlignment(Pos.CENTER);
                    manageBtn.setSpacing(5);
                    setGraphic(manageBtn);
                }
                setText(null);
            }
        };

        record_page_col_action.setCellFactory(cellFactory);
        record_page_tableView.setItems(patientRecordData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPatientsData();
        actionButtons();
    }
}
