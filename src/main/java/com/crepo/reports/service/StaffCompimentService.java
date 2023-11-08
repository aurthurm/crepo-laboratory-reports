package com.crepo.reports.service;

import com.crepo.reports.domain.StaffCompiment;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.StaffCompiment}.
 */
public interface StaffCompimentService {
    /**
     * Save a staffCompiment.
     *
     * @param staffCompiment the entity to save.
     * @return the persisted entity.
     */
    StaffCompiment save(StaffCompiment staffCompiment);

    /**
     * Updates a staffCompiment.
     *
     * @param staffCompiment the entity to update.
     * @return the persisted entity.
     */
    StaffCompiment update(StaffCompiment staffCompiment);

    /**
     * Partially updates a staffCompiment.
     *
     * @param staffCompiment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StaffCompiment> partialUpdate(StaffCompiment staffCompiment);

    /**
     * Get all the staffCompiments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StaffCompiment> findAll(Pageable pageable);

    /**
     * Get the "id" staffCompiment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StaffCompiment> findOne(Long id);

    /**
     * Delete the "id" staffCompiment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
