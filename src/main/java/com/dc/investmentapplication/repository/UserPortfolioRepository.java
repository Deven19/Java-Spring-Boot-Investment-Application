package com.dc.investmentapplication.repository;

import com.dc.investmentapplication.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {
}
