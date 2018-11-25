package ackr.inu.databasepjt.service;

import ackr.inu.databasepjt.dto.Crime;
import ackr.inu.databasepjt.dto.Population;
import ackr.inu.databasepjt.dto.Tax;
import ackr.inu.databasepjt.dto.Traffic;
import ackr.inu.databasepjt.mapper.*;
import ackr.inu.databasepjt.model.DefaultRes;
import ackr.inu.databasepjt.model.SuicideReq;
import ackr.inu.databasepjt.utils.ResponseMessage;
import ackr.inu.databasepjt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class JsonService {

    final private TrafficMapper trafficMapper;
    final private TaxMapper taxMapper;
    final private PopulationMapper populationMapper;
    final private CrimeMapper crimeMapper;
    final private CityMapper cityMapper;
    final private SuicideMapper suicideMapper;

    public JsonService(final TrafficMapper trafficMapper,
                       final TaxMapper taxMapper,
                       final PopulationMapper populationMapper,
                       final CrimeMapper crimeMapper,
                       final CityMapper cityMapper,
                       final SuicideMapper suicideMapper){
        this.trafficMapper=trafficMapper;
        this.taxMapper=taxMapper;
        this.populationMapper=populationMapper;
        this.crimeMapper=crimeMapper;
        this.cityMapper=cityMapper;
        this.suicideMapper=suicideMapper;
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

    @Transactional
    public DefaultRes saveCrimeRate(final int year, final String city){
        try{
            final double population = populationMapper.total(year, "%"+city+"%");
            final double event = crimeMapper.countingCrime(year, "%"+city+"%");
            final String fullCity = crimeMapper.cityName("%"+city+"%");
            crimeMapper.saveCrimeRate(year,fullCity,event/population);
            return DefaultRes.res(StatusCode.OK,ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes saveCity(final String jsonData){
        try{
            JSONArray arr = new JSONArray(jsonData);
            int list_cnt = arr.length();
            for (int i = 0; i < list_cnt; i++){
                JSONObject jsonObject = arr.getJSONObject(i);
                cityMapper.save(jsonObject.getString("도시"));
            }
            return DefaultRes.res(StatusCode.CREATED,ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR,ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes saveSuicide(final String jsonData){
        try{
            JSONArray arr = new JSONArray(jsonData);
            int list_cnt = arr.length();
            for (int i = 0; i < list_cnt; i++){
                SuicideReq suicideReq = new SuicideReq();
                JSONObject jsonObject = arr.getJSONObject(i);
                suicideReq.setYear(jsonObject.getInt("자살년도"));
                suicideReq.setCity(jsonObject.getString("도시"));
                suicideReq.setRate(jsonObject.getDouble("자살률"));
                log.info(suicideReq.toString());
                suicideMapper.save(suicideReq);
            }
            return DefaultRes.res(StatusCode.CREATED,ResponseMessage.SAVE_JSON);
        }catch (Exception e){
            log.info(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR,ResponseMessage.DB_ERROR);
        }
    }
}
