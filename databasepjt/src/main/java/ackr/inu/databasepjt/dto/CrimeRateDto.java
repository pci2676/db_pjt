package ackr.inu.databasepjt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrimeRateDto {
    private long city_idx;
    private double event;
    private String age;
    private double total;
}
