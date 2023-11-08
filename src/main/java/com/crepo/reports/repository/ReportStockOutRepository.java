package com.crepo.reports.repository;

import com.crepo.reports.domain.ReportStockOut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportStockOut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportStockOutRepository extends JpaRepository<ReportStockOut, Long> {}
