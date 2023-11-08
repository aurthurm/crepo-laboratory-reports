package com.crepo.reports.service;

import com.crepo.reports.domain.LaboratoryStock;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.LaboratoryStock}.
 */
public interface LaboratoryStockService {
    /**
     * Save a laboratoryStock.
     *
     * @param laboratoryStock the entity to save.
     * @return the persisted entity.
     */
    LaboratoryStock save(LaboratoryStock laboratoryStock);

    /**
     * Updates a laboratoryStock.
     *
     * @param laboratoryStock the entity to update.
     * @return the persisted entity.
     */
    LaboratoryStock update(LaboratoryStock laboratoryStock);

    /**
     * Partially updates a laboratoryStock.
     *
     * @param laboratoryStock the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LaboratoryStock> partialUpdate(LaboratoryStock laboratoryStock);

    /**
     * Get all the laboratoryStocks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LaboratoryStock> findAll(Pageable pageable);

    /**
     * Get the "id" laboratoryStock.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LaboratoryStock> findOne(Long id);

    /**
     * Delete the "id" laboratoryStock.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
