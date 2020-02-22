package com.example.amit.controllers;

import com.example.amit.dto.TimesNewsScrapperDTO;
import com.example.amit.pojo.TopSixNews;
import com.example.amit.services.TimesNewsScrapperService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TimesNewsScrapperController {
    private TimesNewsScrapperService timesNewsScrapperService;
    private TimesNewsScrapperDTO timesNewsScrapperDTO;
    @Autowired
    public TimesNewsScrapperController(TimesNewsScrapperService timesNewsScrapperService){
        this.timesNewsScrapperService=timesNewsScrapperService;
    }
    @GetMapping(value = "getTimeNews",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTopSixNews() throws IOException {
        try {
            TopSixNews[] data=timesNewsScrapperService.getTopSixNewsItems();
            timesNewsScrapperDTO=new TimesNewsScrapperDTO();
            timesNewsScrapperDTO.setNews(data);
            JSONObject jsonObject=new JSONObject(timesNewsScrapperDTO);
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (IOException e) {
            throw e;
        }
    }
}
