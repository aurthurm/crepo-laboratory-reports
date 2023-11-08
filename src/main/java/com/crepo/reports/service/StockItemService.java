package com.crepo.reports.service;

import com.crepo.reports.domain.StockItem;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crepo.reports.domain.StockItem}.
 */
public interface StockItemService {
    /**
     * Save a stockItem.
     *
     * @param stockItem the entity to save.
     * @return the persisted entity.
     */
    StockItem save(StockItem stockItem);

    /**
     * Updates a stockItem.
     *
     * @param stockItem the entity to update.
     * @return the persisted entity.
     */
    StockItem update(StockItem stockItem);

    /**
     * Partially updates a stockItem.
     *
     * @param stockItem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StockItem> partialUpdate(StockItem stockItem);

    /**
     * Get all the stockItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockItem> findAll(Pageable pageable);

    /**
     * Get the "id" stockItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockItem> findOne(Long id);

    /**
     * Delete the "id" stockItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
