package ackr.inu.databasepjt.dto;

import lombok.Data;

@Data
public class Crime {
    private int idx;
    private int year;           //발생년도
    private String typeR;       //범죄대분류
    private String typeM;       //범죄중분류
    private String city;        //발생지
    private int event;          //발생수
}
