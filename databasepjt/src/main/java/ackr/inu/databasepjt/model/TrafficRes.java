package ackr.inu.databasepjt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrafficRes {
    private int year;
    private String city;
    private int event;
}
