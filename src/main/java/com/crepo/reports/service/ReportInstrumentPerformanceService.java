package com.crepo.reports.service;

import com.crepo.reports.domain.ReportInstrumentPerformance;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.ReportInstrumentPerformance}.
 */
public interface ReportInstrumentPerformanceService {
    /**
     * Save a reportInstrumentPerformance.
     *
     * @param reportInstrumentPerformance the entity to save.
     * @return the persisted entity.
     */
    ReportInstrumentPerformance save(ReportInstrumentPerformance reportInstrumentPerformance);

    /**
     * Updates a reportInstrumentPerformance.
     *
     * @param reportInstrumentPerformance the entity to update.
     * @return the persisted entity.
     */
    ReportInstrumentPerformance update(ReportInstrumentPerformance reportInstrumentPerformance);

    /**
     * Partially updates a reportInstrumentPerformance.
     *
     * @param reportInstrumentPerformance the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportInstrumentPerformance> partialUpdate(ReportInstrumentPerformance reportInstrumentPerformance);

    /**
     * Get all the reportInstrumentPerformances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportInstrumentPerformance> findAll(Pageable pageable);

    /**
     * Get the "id" reportInstrumentPerformance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportInstrumentPerformance> findOne(Long id);

    /**
     * Delete the "id" reportInstrumentPerformance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
