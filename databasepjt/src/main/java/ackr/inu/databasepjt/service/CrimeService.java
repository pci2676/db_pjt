package ackr.inu.databasepjt.service;

import ackr.inu.databasepjt.mapper.CrimeMapper;
import ackr.inu.databasepjt.mapper.PopulationMapper;
import ackr.inu.databasepjt.mapper.TaxMapper;
import ackr.inu.databasepjt.model.CrimeReq;
import ackr.inu.databasepjt.model.DefaultRes;
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

    public CrimeService(final CrimeMapper crimeMapper,
                        final PopulationMapper populationMapper,
                        final TaxMapper taxMapper) {
        this.crimeMapper = crimeMapper;
        this.populationMapper = populationMapper;
        this.taxMapper=taxMapper;
    }

    //강력범죄+폭력범죄 -> 총합출력
    public DefaultRes getCrimeSum(final int year, final String city) {
        final int event = crimeMapper.countingCrime(year, city);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DB, event);
    }

    //연령별, 도시, 년도입력 -> 인구 출력
    public DefaultRes getPopulation(final int year, final String age, final String city) {
        final int sum = populationMapper.selectedAgeTotal(year, "%"+city+"%", age);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DB, sum);
    }


    //년도, 도시입력 -> CrimeReq객체 출력
    public DefaultRes getCrimeRate(final int year, final String city) {
        final double population = populationMapper.total(year, "%"+city+"%");
        final double event = crimeMapper.countingCrime(year, city);
        final double tax = taxMapper.getTax(year,"%"+city+"%");
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_DB, CrimeReq.res(year,city,tax,event,population));
    }
}
