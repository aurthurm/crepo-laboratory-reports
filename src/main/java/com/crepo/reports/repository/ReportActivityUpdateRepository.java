package com.crepo.reports.repository;

import com.crepo.reports.domain.ReportActivityUpdate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReportActivityUpdate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportActivityUpdateRepository extends JpaRepository<ReportActivityUpdate, Long> {}
