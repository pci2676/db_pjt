package ackr.inu.databasepjt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TotalRes {
    private int year;
    private String city;
    private double crimeAmount;
    private double trafficAmount;
    private double population;
    private double crimeRate;
    private double trafficRate;
    private double taxAmount;


    public static TotalRes res(final int year,final String city,final double crimeAmount,final double trafficAmount,final double population, final double taxAmount){
        return res(year,city,crimeAmount,trafficAmount,population,taxAmount,crimeAmount/population,trafficAmount/population);
    }

    public static TotalRes res(final int year,final String city,final double crimeAmount,final double trafficAmount,final double population, final double taxAmount,final double crimeRate, final double trafficRate){
        return TotalRes.builder()
                .year(year)
                .city(city)
                .crimeAmount(crimeAmount)
                .trafficAmount(trafficAmount)
                .population(population)
                .taxAmount(taxAmount)
                .crimeRate(crimeRate)
                .trafficRate(trafficRate)
                .build();
    }
}


