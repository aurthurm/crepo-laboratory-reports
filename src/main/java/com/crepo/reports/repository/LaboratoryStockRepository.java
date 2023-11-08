package com.crepo.reports.repository;

import com.crepo.reports.domain.LaboratoryStock;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LaboratoryStock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaboratoryStockRepository extends JpaRepository<LaboratoryStock, Long> {}
