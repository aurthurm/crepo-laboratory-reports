package com.crepo.reports.service;

import com.crepo.reports.domain.LaboratoryInstrument;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.LaboratoryInstrument}.
 */
public interface LaboratoryInstrumentService {
    /**
     * Save a laboratoryInstrument.
     *
     * @param laboratoryInstrument the entity to save.
     * @return the persisted entity.
     */
    LaboratoryInstrument save(LaboratoryInstrument laboratoryInstrument);

    /**
     * Updates a laboratoryInstrument.
     *
     * @param laboratoryInstrument the entity to update.
     * @return the persisted entity.
     */
    LaboratoryInstrument update(LaboratoryInstrument laboratoryInstrument);

    /**
     * Partially updates a laboratoryInstrument.
     *
     * @param laboratoryInstrument the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LaboratoryInstrument> partialUpdate(LaboratoryInstrument laboratoryInstrument);

    /**
     * Get all the laboratoryInstruments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LaboratoryInstrument> findAll(Pageable pageable);

    /**
     * Get the "id" laboratoryInstrument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LaboratoryInstrument> findOne(Long id);

    /**
     * Delete the "id" laboratoryInstrument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
