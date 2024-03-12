package com.onthatile.app.heatthcarespringfx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthCareSpringFxApplication {
    public static void main(String[] args) {
        Application.launch(ChartApplication.class , args);
    }
}
