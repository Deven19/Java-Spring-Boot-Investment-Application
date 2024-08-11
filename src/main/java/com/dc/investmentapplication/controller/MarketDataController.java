package com.dc.investmentapplication.controller;

import com.dc.investmentapplication.entity.MarketData;
import com.dc.investmentapplication.service.MarketDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/market-data")
@Validated
public class MarketDataController {

    @Autowired
    MarketDataService marketDataService;

    @GetMapping
    public List<MarketData> getMarketData(){
        return marketDataService.getAllMarketData();
    }

    @PostMapping
    public MarketData createMarketData(@Validated @RequestBody MarketData marketData){
        return marketDataService.createMarketData(marketData);
    }

    @GetMapping("/{id}")
    public MarketData getMarketDataById(@PathVariable int id){
        return marketDataService.getMarketDataById(id);
    }

    @PutMapping("/{id}")
    public MarketData updateMarketData(@Validated @RequestBody MarketData marketData){
        return marketDataService.updateMarketData(marketData);
    }

    @DeleteMapping("/{id}")
    public void deleteMarketData(@PathVariable int id){
        marketDataService.deleteMarketData(id);
    }

}
