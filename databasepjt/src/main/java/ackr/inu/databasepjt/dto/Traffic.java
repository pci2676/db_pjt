package ackr.inu.databasepjt.dto;

import lombok.Data;

@Data
public class Traffic {
    private int idx;                //고유번호
    private int year;               //발생년
    private int ymd;                //발생년월일시
    private String daynight;        //주야
    private String week;            //요일
    private int dead;               //사망자수
    private int injured;            //사상자수
    private String city;            //발생지시도
    private String district;        //발생지시군구
}
