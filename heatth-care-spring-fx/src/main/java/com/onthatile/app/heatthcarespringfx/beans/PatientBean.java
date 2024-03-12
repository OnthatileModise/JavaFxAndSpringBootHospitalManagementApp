package com.onthatile.app.heatthcarespringfx.beans;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientBean {
    private Integer id;
    private String name;
    private String surname;
    private String cellPhoneNumber;
    private String telPhoneNumber;
    private String address;
    private Integer appointmentId;
    private String gender;
    private String description;
    private String dateDeleted;
    private String dateModified;
    private String date;
    private String status;
}
