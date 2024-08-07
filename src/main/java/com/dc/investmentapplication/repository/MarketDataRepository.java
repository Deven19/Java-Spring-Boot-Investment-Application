package com.dc.investmentapplication.repository;

import com.dc.investmentapplication.entity.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketDataRepository  extends JpaRepository<MarketData, Long> {
}
