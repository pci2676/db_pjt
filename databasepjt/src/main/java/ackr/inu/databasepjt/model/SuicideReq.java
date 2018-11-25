package ackr.inu.databasepjt.model;

import lombok.Data;

@Data
public class SuicideReq {
    private int year;
    private String city;
    private double rate;
}
