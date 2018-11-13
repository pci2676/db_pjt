package ackr.inu.databasepjt.dto;

import lombok.Data;

@Data
public class Crime {
    private int idx;
    private int year;
    private String typeR;
    private String typeM;
    private String city;
    private String district;
    private int event;
}
