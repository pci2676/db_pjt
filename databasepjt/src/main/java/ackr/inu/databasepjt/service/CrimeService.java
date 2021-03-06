package ackr.inu.databasepjt.service;

import ackr.inu.databasepjt.mapper.CrimeMapper;
import ackr.inu.databasepjt.mapper.PopulationMapper;
import ackr.inu.databasepjt.mapper.TaxMapper;
import ackr.inu.databasepjt.mapper.TrafficMapper;
import ackr.inu.databasepjt.model.CrimeRes;
import ackr.inu.databasepjt.model.DefaultRes;
import ackr.inu.databasepjt.model.TrafficRes;
import ackr.inu.databasepjt.utils.ResponseMessage;
import ackr.inu.databasepjt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CrimeService {
    private final CrimeMapper crimeMapper;
    private final PopulationMapper populationMapper;
    private final TaxMapper taxMapper;
    private final TrafficMapper trafficMapper;

    public CrimeService(final CrimeMapper crimeMapper,
                        final PopulationMapper populationMapper,
                        final TaxMapper taxMapper,
                        final TrafficMapper trafficMapper) {
        this.crimeMapper = crimeMapper;
        this.populationMapper = populationMapper;
        this.taxMapper=taxMapper;
        this.trafficMapper=trafficMapper;
    }

    //강력범죄+폭력범죄 -> 총합출력
    public DefaultRes getCrimeSum(final int year, final String city) {
        final double event = crimeMapper.countingCrime(year, city);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DB, event);
    }


    //년도, 도시입력 -> CrimeRes객체 출력
    public DefaultRes getCrimeRate(final int year, final String city) {
        final double population = populationMapper.total(year, "%"+city+"%");
        final double event = crimeMapper.countingCrime(year, "%"+city+"%");
        final double tax = taxMapper.getTax(year,"%"+city+"%");
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DB, CrimeRes.res(year,city,tax,event,population));
    }

    //년도, 도시입력 -> 당시 TrafficRes객체 출력
    public  DefaultRes getTraffic(final int year, final String city){
        final int event = trafficMapper.getTrafficCity(year,"%"+city+"%");
        TrafficRes trafficRes = new TrafficRes(year,city,event);
        return DefaultRes.res(StatusCode.OK,ResponseMessage.READ_DB,trafficRes);
    }


}
