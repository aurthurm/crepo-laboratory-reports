package com.crepo.reports.repository;

import com.crepo.reports.domain.LaboratoryInstrument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LaboratoryInstrument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaboratoryInstrumentRepository extends JpaRepository<LaboratoryInstrument, Long> {}
