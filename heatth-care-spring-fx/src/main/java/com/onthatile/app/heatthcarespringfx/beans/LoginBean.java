package com.onthatile.app.heatthcarespringfx.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBean {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
}
