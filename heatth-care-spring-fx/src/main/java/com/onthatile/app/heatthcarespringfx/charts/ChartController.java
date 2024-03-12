package com.onthatile.app.heatthcarespringfx.charts;



import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ChartController{
    @FXML
    public LineChart<String , Double> chart;
    private final WebClientPatientClient webClientPatientClient;

    public ChartController(WebClientPatientClient webClientPatientClient) {
        this.webClientPatientClient = webClientPatientClient;
    }

    @FXML
    public void initialize(){
        String symbol = "SYMBOL1";
        PriceSubscriber priceSubscriber = new PriceSubscriber(symbol);
        webClientPatientClient.pricesFor(symbol).subscribe(priceSubscriber);

        String symbol1 = "SYMBOL2";
        PriceSubscriber priceSubscriber1 = new PriceSubscriber(symbol);
        webClientPatientClient.pricesFor(symbol1).subscribe(priceSubscriber1);

        ObservableList<Series<String,Double>> data = FXCollections.observableArrayList();
        data.add(priceSubscriber.series);
        data.add(priceSubscriber1.series);
        chart.setData(data);
     }

    private static class PriceSubscriber implements Consumer<StockPrice> {
        private final ObservableList<Data<String, Double>> seriesData = FXCollections.observableArrayList();
        private final Series<String , Double> series;
        public PriceSubscriber(String symbol) {
            series = new Series<>(symbol , seriesData);
        }

        @Override
        public void accept(StockPrice stockPrice) {
            Platform.runLater(() ->
                    seriesData.add(new Data<>(String.valueOf(stockPrice.getTime().getSecond()) , stockPrice.getPrice()))
            );
        }
    }
}
