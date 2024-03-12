package com.onthatile.app.heatthcarespringfx.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onthatile.app.heatthcarespringfx.beans.AppointmentsBean;
import com.onthatile.app.heatthcarespringfx.beans.DoctorsBean;
import com.onthatile.app.heatthcarespringfx.data.Data;
import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.PatientBean;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import javafx.util.Callback;
import javafx.geometry.Pos;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class AdminMainFormController implements Initializable {
    @FXML
    public Label current_form;
    @FXML
    public Button logout_btn;
    @FXML
    public Label nav_adminID;
    @FXML
    public Label nav_username;
    @FXML
    public Button dashboard_btn;
    @FXML
    public Label dashboard_AD;
    @FXML
    public Label dashboard_TP;
    @FXML
    public Label dashboard_AP;
    @FXML
    public Label dashboard_tA;
    @FXML
    public AreaChart<String , Integer> dashboard_chart_PD;
    @FXML
    public BarChart<String , Integer> dashboard_chart_DD;
    @FXML
    public TableView<DoctorsBean> dashboard_tableView;
    @FXML
    public TableColumn<? , ?> dashboard_col_doctorID;
    @FXML
    public TableColumn<? , ?> dashboard_col_name;
    @FXML
    public TableColumn<? , ?> dashboard_col_specialized;
    @FXML
    public TableColumn<? , ?> dashboard_col_status;
    @FXML
    public TableView<DoctorsBean> doctors_tableView;
    @FXML
    public TableColumn<? , ?> doctors_col_doctorID;
    @FXML
    public TableColumn<? , ?> doctors_col_name;
    @FXML
    public TableColumn<? , ?> doctors_col_gender;
    @FXML
    public TableColumn<? , ?> doctors_col_contactNumber;
    @FXML
    public TableColumn<? , ?> doctors_col_email;
    @FXML
    public TableColumn<? , ?> doctors_col_specialization;
    @FXML
    public TableColumn<? , ?> doctors_col_address;
    @FXML
    public TableColumn<? , ?> doctors_col_status;
    @FXML
    public TableColumn<DoctorsBean, String> doctors_col_action;
    @FXML
    public TableView<PatientBean> patients_tableView;
    @FXML
    public TableColumn<? , ?> patients_col_patientID;
    @FXML
    public TableColumn<? , ?> patients_col_name;
    @FXML
    public TableColumn<?, ?> patients_col_contactNumber;
    @FXML
    public TableColumn<? , ?> patients_col_gender;
    @FXML
    public TableColumn<? , ?> patients_col_description;
    @FXML
    public TableColumn<? , ?> patients_col_date;
    @FXML
    public TableColumn<? , ?> patients_col_dateModify;
    @FXML
    public TableColumn<? , ?> patients_col_dateDelete;
    @FXML
    public TableColumn<? , ?> patients_col_status;
    @FXML
    public TableColumn<PatientBean, String> patients_col_action;
    @FXML
    public TableView<AppointmentsBean> appointments_tableView;
    @FXML
    public TableColumn<? , ?> appointments_appointmentID;
    @FXML
    public TableColumn<? ,?> appointments_name;
    @FXML
    public TableColumn<?, ?> appointments_gender;
    @FXML
    public TableColumn<? , ?> appointments_contactNumber;
    @FXML
    public TableColumn<? , ?> appointments_description;
    @FXML
    public TableColumn<? , ?> appointments_date;
    @FXML
    public TableColumn<? , ?> appointments_dateModify;
    @FXML
    public TableColumn<? , ?> appointments_dateDelete;
    @FXML
    public TableColumn<? , ?> appointments_status;
    @FXML
    public TableColumn<? , ?> appointments_action;
    @FXML
    public TableView<?> payment_tableView;
    @FXML
    public TableColumn<? , ?> payment_col_patientID;
    @FXML
    public TableColumn<? , ?> payment_col_name;
    @FXML
    public TableColumn<? , ?> payment_col_gender;
    @FXML
    public TableColumn<? , ?> payment_col_diagnosis;
    @FXML
    public TableColumn<? , ?> payment_col_doctor;
    @FXML
    public TableColumn<? , ?> payment_col_date;
    @FXML
    public Circle payment_circle;
    @FXML
    public Button payment_checkoutBtn;
    @FXML
    public Label payment_patientID;
    @FXML
    public Label payment_name;
    @FXML
    public Label payment_doctor;
    @FXML
    public Label payment_date;
    @FXML
    public Circle profile_circle;
    @FXML
    public Button profile_importBtn;
    @FXML
    public Label profile_label_adminIO;
    @FXML
    public Label profile_label_name;
    @FXML
    public Label profile_label_email;
    @FXML
    public Label profile_label_dateCreated;
    @FXML
    public TextField profile_adminID;
    @FXML
    public TextField profile_username;
    @FXML
    public TextField profile_email;
    @FXML
    public ComboBox<String> profile_status;
    @FXML
    public Button profile_updateBtn;
    @FXML
    private Label date_time;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Circle top_profile;
    @FXML
    public Label top_username;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AnchorPane doctors_form;
    @FXML
    private AnchorPane patients_form;
    @FXML
    private AnchorPane appointments_form;
    @FXML
    private AnchorPane payment_form;
    @FXML
    private AnchorPane profile_form;
    @FXML
    private Button doctors_btn;
    @FXML
    private Button patients_btn;
    @FXML
    private Button appointments_btn;
    @FXML
    private Button payment_btn;
    @FXML
    private Button profile_btn;
    ObservableList<PatientBean> patientBeansList;
    ObservableList<DoctorsBean> doctorsBeans;
    ObservableList<AppointmentsBean> appointmentListData;

    private Image image;
    private final AlertMessage alert = new AlertMessage();
    public void logoutBtn() {
        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXMLDocument.fxml")));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

//              TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardAD() {
        doctorsBeans = dashboardGetDoctorData();
        dashboard_AD.setText(String.valueOf(doctorsBeans.size()));
    }

    private void dashboardTP() {
        patientBeansList = patientBeans();
        dashboard_TP.setText(String.valueOf(patientBeansList.size()));
    }

    private void dashboardAP() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("http://localhost:8084/api/v1/getActivePatients"))
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
                List<PatientBean> patientBeans = new ObjectMapper().readValue(response.body(), new TypeReference<>() {});
                dashboard_AP.setText(String.valueOf(patientBeans.size()));
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void dashboardTA() {
        appointmentListData = appointmentsGetData();
        dashboard_tA.setText(String.valueOf(appointmentListData.size()));
    }

    private void dashboardGetDoctorDisplayData() {
        doctorsBeans = dashboardGetDoctorData();
        dashboard_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        dashboard_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboard_col_specialized.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        dashboard_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        dashboard_tableView.setItems(doctorsBeans);
    }

    private void doctorDisplayData() {
        doctorsBeans = dashboardGetDoctorData();
        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctors_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctors_tableView.setItems(doctorsBeans);
    }

    private void doctorActionButton() {
        doctorsBeans = dashboardGetDoctorData();
        Callback<TableColumn<DoctorsBean , String> , TableCell<DoctorsBean , String>> cellFactory =  (TableColumn<DoctorsBean , String> param) -> {
            final TableCell<DoctorsBean , String> cell = new TableCell<DoctorsBean , String>(){
                public void updateItem(String item , boolean empty){
                    super.updateItem(item , empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");
                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");
                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            DoctorsBean doctorsBean = doctors_tableView.getSelectionModel().getSelectedItem();
                            if (doctorsBean == null){
                                alert.errorMessage("Please select item first");
                                return;
                            }
                            try{
                                Data.temp_doctorID = String.valueOf(doctorsBean.getId());
                                Data.temp_doctorName = doctorsBean.getName();
                                Data.temp_doctorEmail = doctorsBean.getEmailAddress();
                                Data.temp_doctorPassword = "";
                                Data.temp_doctorSpecialized = doctorsBean.getSpecialization();
                                Data.temp_doctorGender = doctorsBean.getGender();
                                Data.temp_doctorMobileNumber = String.valueOf(doctorsBean.getContactNumber());
                                Data.temp_doctorAddress = doctorsBean.getAddress();
                                Data.temp_doctorStatus = doctorsBean.getStatus();
                                Data.temp_doctorImagePath = "";
                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("EditDoctorForm.fxml")));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });
                        removeButton.setOnAction((ActionEvent event) -> {
                            DoctorsBean doctorsBean = doctors_tableView.getSelectionModel().getSelectedItem();
                            if (doctorsBean == null) {
                                alert.errorMessage("Please select an item first");
                                return;
                            }
                            //TODO endpoint to delete a patient
                            HttpClient httpClient = HttpClient.newBuilder().build();
                            HttpRequest httpRequest = null;
                            try {
                                httpRequest = HttpRequest.newBuilder()
                                        .POST(HttpRequest.BodyPublishers.ofString("{\"id\"" + ":"+ doctorsBean.getId()+ "}"))
                                        .uri(new URI("http://localhost:8084/api/v1/deleteDoctor"))
                                        .header("Content-Type" , "application/json")
                                        .header("Accept", "application/json")
                                        .build();
                            }catch (URISyntaxException e){
                                e.printStackTrace();
                            }
                            HttpResponse<String> response = null;
                            if (httpRequest != null) {
                                try {
                                    response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                                    doctorDisplayData();
                                    alert.successMessage("Deleted Successfully!");
                                } catch (IOException | InterruptedException exception) {
                                    exception.printStackTrace();
                                }
                            }

                        });
                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };
        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableView.setItems(doctorsBeans);
    }

    public void dashboardDoctorDataChart(){
        try{
            doctorsBeans = dashboardGetDoctorData();
            dashboard_chart_DD.getData().clear();
            XYChart.Series<String, Integer> chart = new XYChart.Series<>();
            for (DoctorsBean doctorsBean : doctorsBeans){
                chart.getData().add(new XYChart.Data<>(String.valueOf(LocalDate.now()) , doctorsBean.getId()));
            }
            dashboard_chart_DD.getData().add(chart);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dashboardPatientDataChart(){
        try{
            patientBeansList = patientBeans();
            dashboard_chart_PD.getData().clear();
            XYChart.Series<String, Integer> chart = new XYChart.Series<>();
            for (PatientBean patientBean : patientBeansList){
                chart.getData().add(new XYChart.Data<>(String.valueOf(LocalDate.now()) , patientBean.getId()));
            }
            dashboard_chart_PD.getData().add(chart);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentsBean> appointmentsGetData(){
        ObservableList<AppointmentsBean> appointmentsBeans = FXCollections.observableArrayList();
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = null;
        try{
            httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("http://localhost:8084/api/v1/getAllAppointments"))
                    .header("Content-Type" , "application/json")
                    .header("Accept", "application/json")
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest , HttpResponse.BodyHandlers.ofString());
            List<AppointmentsBean> appointmentsBeanList = new ObjectMapper().readValue(response.body(), new TypeReference<>() {});
            appointmentsBeans.addAll(appointmentsBeanList);
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return appointmentsBeans;
    }

    private ObservableList<DoctorsBean> dashboardGetDoctorData(){
        ObservableList<DoctorsBean> doctorsBeanObservableList = FXCollections.observableArrayList();
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
                doctorsBeanObservableList.addAll(doctorsBeans);
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
        return doctorsBeanObservableList;
    }

    public ObservableList<PatientBean> patientBeans(){
        ObservableList<PatientBean> observableList = FXCollections.observableArrayList();
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
                observableList.addAll(patientBeanList);
            }catch (IOException | InterruptedException exception){
                exception.printStackTrace();
            }
        }

        return observableList;
    }

    private void patientDisplayData() {
        patientBeansList = patientBeans();
        patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("id"));
        patients_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("cellPhoneNumber"));
        patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("telPhoneNumber"));
        patients_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patients_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
        patients_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDeleted"));
        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        patients_tableView.setItems(patientBeansList);
    }

    private void patientActionButton() {
        patientBeansList = patientBeans();
        Callback<TableColumn<PatientBean , String> , TableCell<PatientBean , String>> cellFactory = (TableColumn<PatientBean , String> param) -> {
            final TableCell<PatientBean , String> cell = new TableCell<>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button deleteButton = new Button("Delete");
                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        deleteButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent actionEvent) -> {
                            try {
                                PatientBean patientBean = patients_tableView.getSelectionModel().getSelectedItem();
                                int num = patients_tableView.getSelectionModel().getSelectedIndex();
                                if (patientBean.getId() < 0) {
                                    alert.errorMessage("Please select an item first");
                                    return;
                                }
                                //TODO endpoint to edit a patient
                                Data.temp_PatientID = patientBean.getId();
                                Data.temp_patient_name = patientBean.getName();
                                Data.temp_patient_surname = patientBean.getSurname();
                                Data.temp_patient_telnumber = patientBean.getTelPhoneNumber();
                                Data.temp_patient_gender = patientBean.getGender();
                                Data.temp_patient_address = patientBean.getAddress();
                                Data.temp_patient_status = patientBean.getStatus();
                                Data.temp_patient_number = String.valueOf(Long.valueOf(patientBean.getCellPhoneNumber()));
                                Data.temp_patient_appointmentId = Long.valueOf(patientBean.getAppointmentId());
                                Data.temp_patient_description = patientBean.getDescription();
                                Data.temp_patient_date_deleted = patientBean.getDateDeleted();
                                Data.temp_patient_date_modified = patientBean.getDateModified();
                                Data.temp_patient_date = patientBean.getDate();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("EditPatientForm.fxml")));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();
                                patientDisplayData();
                            } catch (Exception e) {
                                alert.errorMessage("Please select an item first");
                                e.printStackTrace();
                            }
                        });

                        deleteButton.setOnAction((ActionEvent actionEvent) -> {
                            PatientBean patientBean = patients_tableView.getSelectionModel().getSelectedItem();
                            int num = patients_tableView.getSelectionModel().getSelectedIndex();
                            if ((num - 1) < 1) {
                                alert.errorMessage("Please select an item first");
                            }
                            //TODO endpoint to delete a patient
                            HttpClient httpClient = HttpClient.newBuilder().build();
                            HttpRequest httpRequest = null;
                            try {
                                httpRequest = HttpRequest.newBuilder()
                                        .POST(HttpRequest.BodyPublishers.ofString("{\"id\"" + ":"+ patientBean.getId()+ "}"))
                                        .uri(new URI("http://localhost:8084/api/v1/deletePatient"))
                                        .header("Content-Type" , "application/json")
                                        .header("Accept", "application/json")
                                        .build();
                            }catch (URISyntaxException e){
                                e.printStackTrace();
                            }
                            HttpResponse<String> response = null;
                            if (httpRequest != null) {
                                try {
                                    response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                                    patientDisplayData();
                                } catch (IOException | InterruptedException exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                        HBox manageBtn = new HBox(editButton, deleteButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };
        patients_col_action.setCellFactory(cellFactory);
        patients_tableView.setItems(patientBeansList);
    }

    private void appointmentDisplayData() {
        appointmentListData = appointmentsGetData();
        appointments_appointmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_contactNumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
        appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDeleted"));
        appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        appointments_tableView.setItems(appointmentListData);
    }

    private void paymentDisplayData() {
    }

    private void profileStatusList() {
        List<String> listS = new ArrayList<>();

        listS.addAll(Arrays.asList(Data.gender));
        ObservableList<String> listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    private void profileDisplayInfo() {
        profile_adminID.setText(String.valueOf(Data.loginBean.getId()));
        profile_username.setText(Data.loginBean.getName() + " " + Data.loginBean.getSurname());
        profile_email.setText(Data.loginBean.getEmail());
//        profile_status.getSelectionModel().select(Data.loginBean.g);

        profile_label_adminIO.setText(String.valueOf(Data.loginBean.getId()));
        profile_label_name.setText(Data.loginBean.getName());
        profile_label_email.setText(Data.loginBean.getEmail());
//        profile_label_dateCreated.setText(Data.loginBean.);
    }

    private void profileDisplayImages() {
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            dashboardAD();
            dashboardTP();
            dashboardAP();
            dashboardTA();
            dashboardGetDoctorDisplayData();

            current_form.setText("Dashboard Form");
        } else if (event.getSource() == doctors_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
            doctorDisplayData();
            doctorActionButton();

            current_form.setText("Doctor's Form");
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
            patientDisplayData();
            patientActionButton();
            current_form.setText("Patient's Form");
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            payment_form.setVisible(false);
            profile_form.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            appointmentDisplayData();

            current_form.setText("Appointment's Form");
        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(true);
            profile_form.setVisible(false);

            paymentDisplayData();

            current_form.setText("Payment Form");
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(true);

            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Profile Form");
        }
    }


    public void paymentSelectItems() {
    }

    public void paymentCheckOutBtn() {
    }

    public void profileInsertImage() {
    }

    public void profileUpdateBtn() {
    }

    public void runTime() {

        new Thread() {

            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {

                        Thread.sleep(1000); // 1000 ms = 1s

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();

    }

    public void displayAdminIDUsername(){
        nav_adminID.setText(String.valueOf(Data.admin_id));
        nav_username.setText(Data.admin_username);
        top_username.setText(Data.admin_username);
    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle){
        runTime();
        displayAdminIDUsername();
        //DASHBOARD
        dashboardAD();
        dashboardTP();
        dashboardAP();
        dashboardTA();
        dashboardGetDoctorDisplayData();
        dashboardPatientDataChart();
        dashboardDoctorDataChart();
        // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
        patientDisplayData();
        patientActionButton();
    }
}
