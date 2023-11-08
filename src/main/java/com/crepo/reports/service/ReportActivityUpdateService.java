package com.crepo.reports.service;

import com.crepo.reports.domain.ReportActivityUpdate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.ReportActivityUpdate}.
 */
public interface ReportActivityUpdateService {
    /**
     * Save a reportActivityUpdate.
     *
     * @param reportActivityUpdate the entity to save.
     * @return the persisted entity.
     */
    ReportActivityUpdate save(ReportActivityUpdate reportActivityUpdate);

    /**
     * Updates a reportActivityUpdate.
     *
     * @param reportActivityUpdate the entity to update.
     * @return the persisted entity.
     */
    ReportActivityUpdate update(ReportActivityUpdate reportActivityUpdate);

    /**
     * Partially updates a reportActivityUpdate.
     *
     * @param reportActivityUpdate the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportActivityUpdate> partialUpdate(ReportActivityUpdate reportActivityUpdate);

    /**
     * Get all the reportActivityUpdates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportActivityUpdate> findAll(Pageable pageable);

    /**
     * Get the "id" reportActivityUpdate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportActivityUpdate> findOne(Long id);

    /**
     * Delete the "id" reportActivityUpdate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
