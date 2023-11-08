package com.crepo.reports.service;

import com.crepo.reports.domain.ReportStockOut;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.ReportStockOut}.
 */
public interface ReportStockOutService {
    /**
     * Save a reportStockOut.
     *
     * @param reportStockOut the entity to save.
     * @return the persisted entity.
     */
    ReportStockOut save(ReportStockOut reportStockOut);

    /**
     * Updates a reportStockOut.
     *
     * @param reportStockOut the entity to update.
     * @return the persisted entity.
     */
    ReportStockOut update(ReportStockOut reportStockOut);

    /**
     * Partially updates a reportStockOut.
     *
     * @param reportStockOut the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportStockOut> partialUpdate(ReportStockOut reportStockOut);

    /**
     * Get all the reportStockOuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportStockOut> findAll(Pageable pageable);

    /**
     * Get the "id" reportStockOut.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportStockOut> findOne(Long id);

    /**
     * Delete the "id" reportStockOut.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
