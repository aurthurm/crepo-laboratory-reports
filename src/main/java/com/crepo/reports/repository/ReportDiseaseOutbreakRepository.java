package com.crepo.reports.repository;

import com.crepo.reports.domain.ReportDiseaseOutbreak;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportDiseaseOutbreak entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportDiseaseOutbreakRepository extends JpaRepository<ReportDiseaseOutbreak, Long> {}
