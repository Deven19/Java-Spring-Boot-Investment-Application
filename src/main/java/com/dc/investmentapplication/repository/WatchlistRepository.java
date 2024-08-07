package com.dc.investmentapplication.repository;

import com.dc.investmentapplication.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
}
