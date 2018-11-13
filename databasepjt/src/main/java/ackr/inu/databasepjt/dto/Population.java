package ackr.inu.databasepjt.dto;

import lombok.Data;

@Data
public class Population {
    private int idx;
    private String city;    //행정구역별
    private int year;    //연도별
    private String age;     //연령별
    private int total;      //총인구
    private int total_out;  //총인구_외국인
    private int total_m;    //총인구_남자
    private int total_w;    //총인구_여자
    private int in_m;       //내국인_남자
    private int in_w;       //내국인_여자
    private int out_m;      //외국인_남자
    private int out_w;      //외국인_여자
    private double out_per; //외국인비율
}
