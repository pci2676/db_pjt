package ackr.inu.databasepjt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CrimeRes {
    private int year;
    private String city;
    private double tax;
    private double event;
    private double population;
    private double crimeRate;

    public CrimeRes(final int year, final String city, final double event, final double population, final double tax){
        this.year=year;
        this.city=city;
        this.tax=tax;
        this.event=event;
        this.population=population;
        this.crimeRate=0;
    }

    public static CrimeRes res(final int year, final String city, final double tax, final double event, final double population){
        final double rate = event/population;
        return res(year,city,tax,event,population,rate);
    }
    public static CrimeRes res(final int year, final String city, final double tax, final double event, final double population, final double crimeRate){
        return CrimeRes.builder()
                .year(year)
                .city(city)
                .tax(tax)
                .event(event)
                .population(population)
                .crimeRate(crimeRate)
                .build();
    }
}
