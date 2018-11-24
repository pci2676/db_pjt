package ackr.inu.databasepjt.service;

import ackr.inu.databasepjt.dto.TrafficTop;
import ackr.inu.databasepjt.mapper.CrimeMapper;
import ackr.inu.databasepjt.mapper.PopulationMapper;
import ackr.inu.databasepjt.mapper.TaxMapper;
import ackr.inu.databasepjt.mapper.TrafficMapper;
import ackr.inu.databasepjt.model.DefaultRes;
import ackr.inu.databasepjt.model.TotalRes;
import ackr.inu.databasepjt.utils.ResponseMessage;
import ackr.inu.databasepjt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class TotalService {
    private final CrimeMapper crimeMapper;
    private final PopulationMapper populationMapper;
    private final TaxMapper taxMapper;
    private final TrafficMapper trafficMapper;

    public TotalService(final CrimeMapper crimeMapper,
                        final PopulationMapper populationMapper,
                        final TaxMapper taxMapper,
                        final TrafficMapper trafficMapper) {
        this.crimeMapper = crimeMapper;
        this.populationMapper = populationMapper;
        this.taxMapper=taxMapper;
        this.trafficMapper=trafficMapper;
    }

    public DefaultRes getTotalInfo(final int year,final String city){
        final double cAmount = crimeMapper.countingCrime(year,"%"+city+"%");
        final double population = populationMapper.total(year, "%"+city+"%");
        final double tAmount = trafficMapper.getTrafficCity(year, "%"+city+"%");
        final double tax = taxMapper.getTax(year,"%"+city+"%");
        //List<TrafficTop> list = trafficMapper.getTopTraffic(year,"%"+city+"%");

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DB, TotalRes.res(year,city,cAmount,tAmount,population,tax));
    }
}
