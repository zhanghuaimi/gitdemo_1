package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDate;
@Data
public class DailySummary {
    private LocalDate date;
    private Double daily_total;


}
