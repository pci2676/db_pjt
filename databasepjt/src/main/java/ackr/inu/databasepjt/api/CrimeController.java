package ackr.inu.databasepjt.api;

import ackr.inu.databasepjt.service.CrimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ackr.inu.databasepjt.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("crime")
public class CrimeController {

    private CrimeService crimeService;

    public CrimeController(final CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @GetMapping("")
    public ResponseEntity showAllCrime() {
        return null;
    }

    //범죄수 출력
    @GetMapping("/basic")
    public ResponseEntity showSelectedYearAndCity(@RequestParam final int year, @RequestParam final String city) {
        try{
            return new ResponseEntity<>(crimeService.getCrimeSum(year,city),HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //범죄율 출력
    @GetMapping("/CrimeRate")
    public ResponseEntity showCrimeRate(@RequestParam final int year, @RequestParam final String city){
        try{
            return new ResponseEntity<>(crimeService.getCrimeRate(year,city),HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
