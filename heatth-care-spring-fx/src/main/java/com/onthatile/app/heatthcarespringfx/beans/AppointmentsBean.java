package com.onthatile.app.heatthcarespringfx.beans;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentsBean {
    public int id;
    public String name;
    public String gender;
    public String contact;
    public String description;
    public String date;
    public String dateModified;
    public String dateDeleted;
    public String status;
    public String diagnosis;
    public String treatment;
    public DoctorsBean doctorsModel;
}
