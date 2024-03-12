package com.onthatile.app.heatthcarespringfx.charts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StockPrice {
    private String symbol;
    private Double price;
    private LocalDateTime time;
}
