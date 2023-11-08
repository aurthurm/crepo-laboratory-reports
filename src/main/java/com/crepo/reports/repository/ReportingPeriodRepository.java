package com.crepo.reports.repository;

import com.crepo.reports.domain.ReportingPeriod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportingPeriod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportingPeriodRepository extends JpaRepository<ReportingPeriod, Long> {}
