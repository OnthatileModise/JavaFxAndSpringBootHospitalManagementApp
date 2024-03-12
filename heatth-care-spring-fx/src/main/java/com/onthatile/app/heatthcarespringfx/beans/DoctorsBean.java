package com.onthatile.app.heatthcarespringfx.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter @Component
public class DoctorsBean {
    public int id;
    public String name;
    public String gender;
    public String contactNumber;
    public String emailAddress;
    public String specialization;
    public String address;
    public String status;
}
