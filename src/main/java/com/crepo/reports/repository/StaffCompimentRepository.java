package com.crepo.reports.repository;

import com.crepo.reports.domain.StaffCompiment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StaffCompiment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffCompimentRepository extends JpaRepository<StaffCompiment, Long> {}
