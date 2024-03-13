package com.onthatile.app.heatthcarespringfx.data;

import com.onthatile.app.heatthcarespringfx.beans.DoctorsBean;
import com.onthatile.app.heatthcarespringfx.beans.LoginBean;
import org.springframework.stereotype.Component;

@Component
public class Data {
    public static Integer admin_id;
    public static String admin_username;
    public static String path;

    public static LoginBean loginBean;

    public static String doctor_id;
    public static String doctor_name;

    public static Integer patient_id;

    public static String[] gender = {"Male", "Female", "Others"};

    public static String[] status = {"Active", "Inactive", "Confirm"};

    public static String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public static Integer temp_PatientID;
    public static String temp_patient_name;
    public static String temp_patient_surname;
    public static String temp_patient_gender;
    public static String temp_patient_number;
    public static Long temp_patient_appointmentId;
    public static String temp_patient_description;

    public static String temp_patient_date_deleted;

    public static String temp_patient_date_modified;
    public static String temp_patient_date;
    public static String temp_patient_telnumber;
    public static String temp_patient_address;
    public static String temp_patient_status;
    public static String temp_patient_path;

    public static String temp_doctorID;
    public static String temp_doctorName;
    public static String temp_doctorEmail;
    public static String temp_doctorPassword;
    public static String temp_doctorSpecialized;
    public static String temp_doctorGender;
    public static String temp_doctorMobileNumber;
    public static String temp_doctorImagePath;
    public static String temp_doctorAddress;
    public static String temp_doctorStatus;

    public static String temp_appID;
    public static String temp_appName;
    public static String temp_appGender;
    public static String temp_appMobileNumber;
    public static String temp_appAddress;
    public static String temp_appDescription;
    public static String temp_appDiagnosis;
    public static String temp_appTreatment;
    public static DoctorsBean temp_appDoctor;
    public static String temp_appSpecialized;
    public static String temp_appStatus;
}
