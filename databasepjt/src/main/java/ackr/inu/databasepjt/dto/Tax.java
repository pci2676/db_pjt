package ackr.inu.databasepjt.dto;

import lombok.Data;

@Data
public class Tax {
    private int idx;
    private int year;
    private String city;
    private String district;
    private String taxType;
    private int tax;
}
