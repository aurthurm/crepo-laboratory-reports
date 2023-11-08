package com.crepo.reports.service;

import com.crepo.reports.domain.ReportDiseaseOutbreak;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.ReportDiseaseOutbreak}.
 */
public interface ReportDiseaseOutbreakService {
    /**
     * Save a reportDiseaseOutbreak.
     *
     * @param reportDiseaseOutbreak the entity to save.
     * @return the persisted entity.
     */
    ReportDiseaseOutbreak save(ReportDiseaseOutbreak reportDiseaseOutbreak);

    /**
     * Updates a reportDiseaseOutbreak.
     *
     * @param reportDiseaseOutbreak the entity to update.
     * @return the persisted entity.
     */
    ReportDiseaseOutbreak update(ReportDiseaseOutbreak reportDiseaseOutbreak);

    /**
     * Partially updates a reportDiseaseOutbreak.
     *
     * @param reportDiseaseOutbreak the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReportDiseaseOutbreak> partialUpdate(ReportDiseaseOutbreak reportDiseaseOutbreak);

    /**
     * Get all the reportDiseaseOutbreaks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportDiseaseOutbreak> findAll(Pageable pageable);

    /**
     * Get the "id" reportDiseaseOutbreak.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportDiseaseOutbreak> findOne(Long id);

    /**
     * Delete the "id" reportDiseaseOutbreak.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
