package com.crepo.reports.service;

import com.crepo.reports.domain.ReportTestPeformance;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.ReportTestPeformance}.
 */
public interface ReportTestPeformanceService {
    /**
     * Save a reportTestPeformance.
     *
     * @param reportTestPeformance the entity to save.
     * @return the persisted entity.
     */
    ReportTestPeformance save(ReportTestPeformance reportTestPeformance);

    /**
     * Updates a reportTestPeformance.
     *
     * @param reportTestPeformance the entity to update.
     * @return the persisted entity.
     */
    ReportTestPeformance update(ReportTestPeformance reportTestPeformance);

    /**
     * Partially updates a reportTestPeformance.
     *
     * @param reportTestPeformance the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportTestPeformance> partialUpdate(ReportTestPeformance reportTestPeformance);

    /**
     * Get all the reportTestPeformances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportTestPeformance> findAll(Pageable pageable);

    /**
     * Get the "id" reportTestPeformance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportTestPeformance> findOne(Long id);

    /**
     * Delete the "id" reportTestPeformance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
