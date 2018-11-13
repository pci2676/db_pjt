package ackr.inu.databasepjt.service;

import ackr.inu.databasepjt.dto.Crime;
import ackr.inu.databasepjt.dto.Population;
import ackr.inu.databasepjt.dto.Tax;
import ackr.inu.databasepjt.dto.Traffic;
import ackr.inu.databasepjt.mapper.CrimeMapper;
import ackr.inu.databasepjt.mapper.PopulationMapper;
import ackr.inu.databasepjt.mapper.TaxMapper;
import ackr.inu.databasepjt.mapper.TrafficMapper;
import ackr.inu.databasepjt.model.DefaultRes;
import ackr.inu.databasepjt.utils.ResponseMessage;
import ackr.inu.databasepjt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Slf4j
@Service
public class JsonService {

    private TrafficMapper trafficMapper;
    private TaxMapper taxMapper;
    private PopulationMapper populationMapper;
    private CrimeMapper crimeMapper;

    public JsonService(final TrafficMapper trafficMapper,
                       final TaxMapper taxMapper,
                       final PopulationMapper populationMapper,
                       final CrimeMapper crimeMapper){
        this.trafficMapper=trafficMapper;
        this.taxMapper=taxMapper;
        this.populationMapper=populationMapper;
        this.crimeMapper=crimeMapper;
    }

    @Transactional
    public DefaultRes saveTraffic(final List<Traffic> tList){
        try{
            Traffic traffic;
            for(int i=0;i<tList.size();i++) {
                traffic = tList.get(i);
                trafficMapper.save(traffic);
            }
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes saveTax(final List<Tax> tList){
        try{
            Tax tax;
            for(int i=0;i<tList.size();i++) {
                tax = tList.get(i);
                taxMapper.save(tax);
            }
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes savePopulation(final List<Population> pList){
        try{
            Population population;
            for(int i=0;i<pList.size();i++) {
                population = pList.get(i);
                populationMapper.save(population);
            }
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes saveCrime(final List<Crime> cList){
        try{
            Crime crime;
            for(int i=0;i<cList.size();i++) {
                crime = cList.get(i);
                crimeMapper.save(crime);
            }
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
