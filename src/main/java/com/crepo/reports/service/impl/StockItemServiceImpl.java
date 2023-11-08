package com.crepo.reports.service.impl;

import com.crepo.reports.domain.StockItem;
import com.crepo.reports.repository.StockItemRepository;
import com.crepo.reports.service.StockItemService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.StockItem}.
 */
@Service
@Transactional
public class StockItemServiceImpl implements StockItemService {

    private final Logger log = LoggerFactory.getLogger(StockItemServiceImpl.class);

    private final StockItemRepository stockItemRepository;

    public StockItemServiceImpl(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    public StockItem save(StockItem stockItem) {
        log.debug("Request to save StockItem : {}", stockItem);
        return stockItemRepository.save(stockItem);
    }

    @Override
    public StockItem update(StockItem stockItem) {
        log.debug("Request to update StockItem : {}", stockItem);
        return stockItemRepository.save(stockItem);
    }

    @Override
    public Optional<StockItem> partialUpdate(StockItem stockItem) {
        log.debug("Request to partially update StockItem : {}", stockItem);

        return stockItemRepository
            .findById(stockItem.getId())
            .map(existingStockItem -> {
                if (stockItem.getName() != null) {
                    existingStockItem.setName(stockItem.getName());
                }
                if (stockItem.getDescription() != null) {
                    existingStockItem.setDescription(stockItem.getDescription());
                }

                return existingStockItem;
            })
            .map(stockItemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockItem> findAll(Pageable pageable) {
        log.debug("Request to get all StockItems");
        return stockItemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockItem> findOne(Long id) {
        log.debug("Request to get StockItem : {}", id);
        return stockItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockItem : {}", id);
        stockItemRepository.deleteById(id);
    }
}
