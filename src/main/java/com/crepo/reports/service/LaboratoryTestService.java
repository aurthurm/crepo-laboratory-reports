package com.crepo.reports.service;

import com.crepo.reports.domain.LaboratoryTest;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.LaboratoryTest}.
 */
public interface LaboratoryTestService {
    /**
     * Save a laboratoryTest.
     *
     * @param laboratoryTest the entity to save.
     * @return the persisted entity.
     */
    LaboratoryTest save(LaboratoryTest laboratoryTest);

    /**
     * Updates a laboratoryTest.
     *
     * @param laboratoryTest the entity to update.
     * @return the persisted entity.
     */
    LaboratoryTest update(LaboratoryTest laboratoryTest);

    /**
     * Partially updates a laboratoryTest.
     *
     * @param laboratoryTest the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LaboratoryTest> partialUpdate(LaboratoryTest laboratoryTest);

    /**
     * Get all the laboratoryTests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LaboratoryTest> findAll(Pageable pageable);

    /**
     * Get the "id" laboratoryTest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LaboratoryTest> findOne(Long id);

    /**
     * Delete the "id" laboratoryTest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
