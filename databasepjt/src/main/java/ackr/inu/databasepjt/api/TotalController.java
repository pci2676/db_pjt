package ackr.inu.databasepjt.api;

import ackr.inu.databasepjt.service.TotalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ackr.inu.databasepjt.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("total")
public class TotalController {

    private final TotalService totalService;

    public TotalController(final TotalService totalService){
        this.totalService=totalService;
    }

    @GetMapping("")
    public ResponseEntity totalResult(@RequestParam final int year,@RequestParam final String city){
        try{
            return new ResponseEntity<>(totalService.getTotalInfo(year,city), HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
