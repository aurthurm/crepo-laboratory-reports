package com.crepo.reports.repository;

import com.crepo.reports.domain.ReportTestPeformance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportTestPeformance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportTestPeformanceRepository extends JpaRepository<ReportTestPeformance, Long> {}
