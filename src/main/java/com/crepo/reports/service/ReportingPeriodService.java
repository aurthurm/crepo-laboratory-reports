package com.crepo.reports.service;

import com.crepo.reports.domain.ReportingPeriod;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.ReportingPeriod}.
 */
public interface ReportingPeriodService {
    /**
     * Save a reportingPeriod.
     *
     * @param reportingPeriod the entity to save.
     * @return the persisted entity.
     */
    ReportingPeriod save(ReportingPeriod reportingPeriod);

    /**
     * Updates a reportingPeriod.
     *
     * @param reportingPeriod the entity to update.
     * @return the persisted entity.
     */
    ReportingPeriod update(ReportingPeriod reportingPeriod);

    /**
     * Partially updates a reportingPeriod.
     *
     * @param reportingPeriod the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportingPeriod> partialUpdate(ReportingPeriod reportingPeriod);

    /**
     * Get all the reportingPeriods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportingPeriod> findAll(Pageable pageable);

    /**
     * Get the "id" reportingPeriod.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportingPeriod> findOne(Long id);

    /**
     * Delete the "id" reportingPeriod.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
