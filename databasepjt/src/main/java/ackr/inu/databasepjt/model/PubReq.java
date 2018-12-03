package ackr.inu.databasepjt.model;

import lombok.Data;

@Data
public class PubReq {
    private String pubName;
    private String city;
    private int openDate;
    private String status;
}
