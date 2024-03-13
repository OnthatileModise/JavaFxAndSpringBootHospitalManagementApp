package com.onthatile.app.heatthcarespringfx.controller;

import com.onthatile.app.heatthcarespringfx.alert.AlertMessage;
import com.onthatile.app.heatthcarespringfx.beans.AppointmentsBean;
import com.onthatile.app.heatthcarespringfx.data.Data;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DoctorMainFormController  implements Initializable {
    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Button logout_btn;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<?, ?> dashboard_chart_PD;

    @FXML
    private BarChart<?, ?> dashboard_chart_DD;

    @FXML
    private TableView<AppointmentsBean> dashboard_tableView;

    @FXML
    private TableColumn<AppointmentsBean, String> dashboard_col_appointmentID;

    @FXML
    private TableColumn<AppointmentsBean, String> dashboard_col_name;

    @FXML
    private TableColumn<AppointmentsBean, String> dashboard_col_description;

    @FXML
    private TableColumn<AppointmentsBean, String> dashboard_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentsBean, String> dashboard_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentsBean> appointments_tableView;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_name;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_gender;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_status;

    @FXML
    private TableColumn<AppointmentsBean, String> appointments_col_action;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;
    @FXML
    private ComboBox<String> profile_specialized;
    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    private Image image;

    private final AlertMessage alert = new AlertMessage();

    private Integer appointmentID;

    private ObservableList<AppointmentsBean> dashboardGetData;

    public ObservableList<AppointmentsBean> appointmentListData;

    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void dashboardDisplayIp(){

    }

    public void dashboardDisplayTP(){

    }

    public void dashboardDisplayAP(){

    }

    public void dashboardDisplayTA(){

    }

    public ObservableList<AppointmentsBean> dashboardAppointmentTableView(){
        ObservableList<AppointmentsBean> listData = FXCollections.observableArrayList();
        return listData;
    }


    public void dashboardDisplayData(){
        dashboardGetData = dashboardAppointmentTableView();

        dashboard_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboard_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboard_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboard_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboard_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboard_tableView.setItems(dashboardGetData);
    }

    public void dashboardNOP(){
        dashboard_chart_PD.getData().clear();
    }

    public void dashboardNOA(){
        dashboard_chart_DD.getData().clear();
    }

    public void patientConfirmBtn(){
        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
        if (patients_patientID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT
            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());
        }
    }

    public void patientAddBtn(){

    }

    public void patientRecordBtn(){
        try {
            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("RecordPageForm.fxml")));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Record of Patients");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void patientClearFields(){
        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }

    public void patientGenderList(){
        List<String> listG = new ArrayList<>();
        Collections.addAll(listG, Data.gender);
        ObservableList<String> listData = FXCollections.observableList(listG);
        patients_gender.setItems(listData);
    }

    public void appointmentGenderList(){

    }

    public void appointmentInsertBtn(){

    }

    public void appointmentUpdateBtn(){

    }

    public void appointmentDeleteBtn(){

    }

    public void appointmentClearBtn(){
        appointment_appointmentID.clear();
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobileNumber.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
    }

    public void appointmentGetAppointmentID(){

    }

    public void appointmentAppointmentID(){

    }

    public void appointmentStatusList(){

    }

    public ObservableList<AppointmentsBean> appointmentGetData(){
        ObservableList<AppointmentsBean> listData = FXCollections.observableArrayList();
        return listData;
    }

    public void appointmentShowData(){
        appointmentListData = appointmentGetData();
    }

    public void appointmentSelect(){

    }

    public void profileUpdateBtn(){

    }

    public void profileChangeProfile(){

    }

    public void profileLabels(){

    }

    public void profileFields(){

    }

    public void profileDisplayImages(){

    }

    public void profileGenderList(){

    }

    public void profileSpecializedList(){
        List<String> listS = new ArrayList<>(Arrays.asList(specialization));
        ObservableList<String> listData = FXCollections.observableArrayList(listS);
        profile_specialized.setItems(listData);
    }

    public void profileStatusList(){
        List<String> listS = new ArrayList<>(Arrays.asList(Data.status));
        ObservableList<String> listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIDNumberName(){
        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);
    }

    public void switchForm(ActionEvent event){
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }

    public void logoutBtn(){
        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("DoctorPage.fxml")));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
//                Data.temp_name = "";
//                Data.temp_gender = "";
//                Data.temp_number = Long.parseLong("0");
//                Data.temp_address = "";
//                Data.temp_status = "";
//                Data.temp_date = "";
//                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
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

    @Override
    public void initialize(URL location , ResourceBundle resourceBundle){
        displayAdminIDNumberName();
        runTime();

        dashboardDisplayIp();
        dashboardDisplayTP();
        dashboardDisplayAP();
        dashboardDisplayTA();
        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        //TO SHOW THE DATA IMMEDIATELY ONCE YOU LOGGED IN YOUR ACCOUNT
        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();

        //TO SHOW THE DATA IMMEDIATELY THE PATIENT'S GENDER COMBOBOX
        patientGenderList();

        //PROFILE
        profileLabels();
        profileFields(); // TO DISPLAY ALL DETAILS TO THE FIELDS
        profileGenderList();
        profileSpecializedList();
        profileStatusList();
        profileDisplayImages(); // TO DISPLAY THE PROFILE PICTURE OF THE DOCTOR
    }
}
