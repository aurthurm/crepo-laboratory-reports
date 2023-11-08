package com.crepo.reports.repository;

import com.crepo.reports.domain.ReportInstrumentPerformance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportInstrumentPerformance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportInstrumentPerformanceRepository extends JpaRepository<ReportInstrumentPerformance, Long> {}
