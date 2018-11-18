package ackr.inu.databasepjt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CrimeReq {
    private int year;
    private String city;
    private double event;
    private double population;
    private double crimeRate;

    public CrimeReq(final int year, final String city, final double event, final double population){
        this.year=year;
        this.city=city;
        this.event=event;
        this.population=population;
        this.crimeRate=0;
    }

    public static CrimeReq res(final int year, final String city, final double event, final double population){
        double rate = event/population;
        return res(year,city,event,population,rate);
    }
    public static CrimeReq res(final int year, final String city, final double event, final double population,final double crimeRate){
        return CrimeReq.builder()
                .year(year)
                .city(city)
                .event(event)
                .population(population)
                .crimeRate(crimeRate)
                .build();
    }
}
