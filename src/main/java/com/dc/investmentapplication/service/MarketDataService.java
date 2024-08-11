package com.dc.investmentapplication.service;

import com.dc.investmentapplication.entity.MarketData;
import com.dc.investmentapplication.repository.MarketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketDataService {
    @Autowired
    private MarketDataRepository marketDataRepository;

    public List<MarketData> getAllMarketData(){
        return marketDataRepository.findAll();
    }

    public MarketData getMarketDataById(int id){
        return getMarketDataById(id);
    }

    public MarketData createMarketData(MarketData marketData){
        return marketDataRepository.save(marketData);
    }

    public MarketData updateMarketData(MarketData marketData){
        return marketDataRepository.save(marketData);
    }

    public void deleteMarketData(long id){
        marketDataRepository.deleteById(id);
    }
}
