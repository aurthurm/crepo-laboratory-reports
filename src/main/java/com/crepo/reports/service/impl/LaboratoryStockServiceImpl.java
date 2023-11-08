package com.crepo.reports.service.impl;

import com.crepo.reports.domain.LaboratoryStock;
import com.crepo.reports.repository.LaboratoryStockRepository;
import com.crepo.reports.service.LaboratoryStockService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.LaboratoryStock}.
 */
@Service
@Transactional
public class LaboratoryStockServiceImpl implements LaboratoryStockService {

    private final Logger log = LoggerFactory.getLogger(LaboratoryStockServiceImpl.class);

    private final LaboratoryStockRepository laboratoryStockRepository;

    public LaboratoryStockServiceImpl(LaboratoryStockRepository laboratoryStockRepository) {
        this.laboratoryStockRepository = laboratoryStockRepository;
    }

    @Override
    public LaboratoryStock save(LaboratoryStock laboratoryStock) {
        log.debug("Request to save LaboratoryStock : {}", laboratoryStock);
        return laboratoryStockRepository.save(laboratoryStock);
    }

    @Override
    public LaboratoryStock update(LaboratoryStock laboratoryStock) {
        log.debug("Request to update LaboratoryStock : {}", laboratoryStock);
        return laboratoryStockRepository.save(laboratoryStock);
    }

    @Override
    public Optional<LaboratoryStock> partialUpdate(LaboratoryStock laboratoryStock) {
        log.debug("Request to partially update LaboratoryStock : {}", laboratoryStock);

        return laboratoryStockRepository
            .findById(laboratoryStock.getId())
            .map(existingLaboratoryStock -> {
                if (laboratoryStock.getLaboratoryId() != null) {
                    existingLaboratoryStock.setLaboratoryId(laboratoryStock.getLaboratoryId());
                }
                if (laboratoryStock.getLaboratory() != null) {
                    existingLaboratoryStock.setLaboratory(laboratoryStock.getLaboratory());
                }
                if (laboratoryStock.getStockItemId() != null) {
                    existingLaboratoryStock.setStockItemId(laboratoryStock.getStockItemId());
                }
                if (laboratoryStock.getStockItem() != null) {
                    existingLaboratoryStock.setStockItem(laboratoryStock.getStockItem());
                }
                if (laboratoryStock.getDepartmentId() != null) {
                    existingLaboratoryStock.setDepartmentId(laboratoryStock.getDepartmentId());
                }
                if (laboratoryStock.getDepartment() != null) {
                    existingLaboratoryStock.setDepartment(laboratoryStock.getDepartment());
                }

                return existingLaboratoryStock;
            })
            .map(laboratoryStockRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LaboratoryStock> findAll(Pageable pageable) {
        log.debug("Request to get all LaboratoryStocks");
        return laboratoryStockRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LaboratoryStock> findOne(Long id) {
        log.debug("Request to get LaboratoryStock : {}", id);
        return laboratoryStockRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LaboratoryStock : {}", id);
        laboratoryStockRepository.deleteById(id);
    }
}
