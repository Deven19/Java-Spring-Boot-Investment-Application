package com.dc.investmentapplication.repository;

import com.dc.investmentapplication.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
}
