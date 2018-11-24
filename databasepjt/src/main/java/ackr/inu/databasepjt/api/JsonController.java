package ackr.inu.databasepjt.api;


import ackr.inu.databasepjt.dto.Crime;
import ackr.inu.databasepjt.dto.Population;
import ackr.inu.databasepjt.dto.Tax;
import ackr.inu.databasepjt.dto.Traffic;
import ackr.inu.databasepjt.model.DefaultRes;
import ackr.inu.databasepjt.service.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("json")
public class JsonController {

    private final JsonService jsonService;

    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/traffic")
    public ResponseEntity inputTrafficData(@RequestBody final String jsonData) {

        List<Traffic> tList = new LinkedList<>();

        try {
            JSONArray arr = new JSONArray(jsonData);
            int list_cnt = arr.length();
            for (int i = 0; i < list_cnt; i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                Traffic traffic = new Traffic();
                traffic.setYear(jsonObject.getInt("발생년"));
                traffic.setYmd(jsonObject.getInt("발생년월일시"));
                traffic.setDaynight(jsonObject.getString("주야"));
                traffic.setWeek(jsonObject.getString("요일"));
                traffic.setDead(jsonObject.getInt("사망자수"));
                traffic.setInjured(jsonObject.getInt("사상자수"));
                traffic.setCity(jsonObject.getString("발생지"));
                tList.add(traffic);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            return new ResponseEntity<>(jsonService.saveTraffic(tList), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crime")
    public ResponseEntity inputCrimeData(@RequestBody final String jsonData){
        List<Crime> cList = new LinkedList<>();

        try{
            JSONArray arr = new JSONArray(jsonData);
            int list_cnt = arr.length();
            for (int i = 0; i < list_cnt; i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                Crime crime = new Crime();
                crime.setYear(jsonObject.getInt("발생년도"));
                crime.setTypeR(jsonObject.getString("범죄대분류"));
                crime.setTypeM(jsonObject.getString("범죄중분류"));
                crime.setCity(jsonObject.getString("도시"));
                crime.setEvent(jsonObject.getInt("발생수"));
                cList.add(crime);
            }

        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try{
            return new ResponseEntity<>(jsonService.saveCrime(cList), HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tax")
    public ResponseEntity inputTaxData(@RequestBody final String jsonData){
        List<Tax> tList = new LinkedList<>();

        try{
            JSONArray arr = new JSONArray(jsonData);
            int list_cnt = arr.length();
            for (int i = 0; i < list_cnt; i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                Tax tax = new Tax();
                tax.setYear(jsonObject.getInt("징수년"));
                tax.setCity(jsonObject.getString("징수시도"));
                tax.setDistrict(jsonObject.getString("징수시군구"));
                tax.setTaxType(jsonObject.getString("징수분류"));
                tax.setTax(jsonObject.getInt("징수금액"));
                tList.add(tax);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try{
            return new ResponseEntity<>(jsonService.saveTax(tList), HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pop")
    public ResponseEntity inputPopulationData(@RequestBody final String jsonData){
        List<Population> pList = new LinkedList<>();

        try{
            JSONArray arr = new JSONArray(jsonData);
            int list_cnt = arr.length();

            for (int i = 0; i < list_cnt; i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                Population population = new Population();
                population.setCity(jsonObject.getString("행정구역별"));
                population.setYear(jsonObject.getInt("연도별"));
                population.setAge(jsonObject.getString("연령별"));
                if(population.getAge().equals("평균연령")||population.getAge().equals("중위연령"))  continue;
                population.setTotal(jsonObject.getInt("총인구"));
                population.setTotal_out(jsonObject.getInt("총인구_외국인"));
                population.setTotal_m(jsonObject.getInt("총인구_남자"));
                population.setTotal_w(jsonObject.getInt("총인구_여자"));
                population.setIn_m(jsonObject.getInt("내국인_남자"));
                population.setIn_w(jsonObject.getInt("내국인_여자"));
                population.setOut_m(jsonObject.getInt("외국인_남자"));
                population.setOut_w(jsonObject.getInt("외국인_여자"));
                population.setOut_per(jsonObject.getDouble("외국인비율"));
                pList.add(population);
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try{
            return new ResponseEntity<>(jsonService.savePopulation(pList), HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/crimeRate")
    public ResponseEntity inputCrimeRate(@RequestParam final int year, @RequestParam final String city){

        try{
            return new ResponseEntity<>(jsonService.saveCrimeRate(year,city), HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(DefaultRes.FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
