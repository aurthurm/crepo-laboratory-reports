package com.crepo.reports.repository;

import com.crepo.reports.domain.LaboratoryTest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LaboratoryTest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, Long> {}
