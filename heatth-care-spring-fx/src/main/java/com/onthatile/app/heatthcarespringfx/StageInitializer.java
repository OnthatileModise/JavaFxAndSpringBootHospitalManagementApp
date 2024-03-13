package com.onthatile.app.heatthcarespringfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {
    @Value("classpath:/FXMLDocument.fxml")
    private Resource chartResource;
    private final String applicationTitle;
    private final ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlLoader.load();
            Stage stage = event.getStage();
            stage.setMinWidth(340);
            stage.setMinHeight(580);
            Scene scene = new Scene(parent);
            scene.getStylesheets().add( Objects.requireNonNull(getClass().getClassLoader().getResource("MainFormDesign.css")).toExternalForm());
            scene.getStylesheets().add( Objects.requireNonNull(getClass().getClassLoader().getResource("PageDesign.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("PatientMainForm.css")).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("DoctorMainFormDesign.css")).toExternalForm());
            stage.setScene(scene);
            stage.setTitle(applicationTitle);
            stage.show();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
