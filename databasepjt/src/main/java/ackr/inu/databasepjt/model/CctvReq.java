package ackr.inu.databasepjt.model;

import lombok.Data;

@Data
public class CctvReq {
    private String position;
    private int city_idx;
    private int count;
    private int year;
    private double latitude;
    private double longitude;
    private String purpose;
}
