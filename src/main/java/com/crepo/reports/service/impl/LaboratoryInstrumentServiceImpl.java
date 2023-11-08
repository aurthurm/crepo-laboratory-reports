package com.crepo.reports.service.impl;

import com.crepo.reports.domain.LaboratoryInstrument;
import com.crepo.reports.repository.LaboratoryInstrumentRepository;
import com.crepo.reports.service.LaboratoryInstrumentService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.LaboratoryInstrument}.
 */
@Service
@Transactional
public class LaboratoryInstrumentServiceImpl implements LaboratoryInstrumentService {

    private final Logger log = LoggerFactory.getLogger(LaboratoryInstrumentServiceImpl.class);

    private final LaboratoryInstrumentRepository laboratoryInstrumentRepository;

    public LaboratoryInstrumentServiceImpl(LaboratoryInstrumentRepository laboratoryInstrumentRepository) {
        this.laboratoryInstrumentRepository = laboratoryInstrumentRepository;
    }

    @Override
    public LaboratoryInstrument save(LaboratoryInstrument laboratoryInstrument) {
        log.debug("Request to save LaboratoryInstrument : {}", laboratoryInstrument);
        return laboratoryInstrumentRepository.save(laboratoryInstrument);
    }

    @Override
    public LaboratoryInstrument update(LaboratoryInstrument laboratoryInstrument) {
        log.debug("Request to update LaboratoryInstrument : {}", laboratoryInstrument);
        return laboratoryInstrumentRepository.save(laboratoryInstrument);
    }

    @Override
    public Optional<LaboratoryInstrument> partialUpdate(LaboratoryInstrument laboratoryInstrument) {
        log.debug("Request to partially update LaboratoryInstrument : {}", laboratoryInstrument);

        return laboratoryInstrumentRepository
            .findById(laboratoryInstrument.getId())
            .map(existingLaboratoryInstrument -> {
                if (laboratoryInstrument.getLaboratoryId() != null) {
                    existingLaboratoryInstrument.setLaboratoryId(laboratoryInstrument.getLaboratoryId());
                }
                if (laboratoryInstrument.getLaboratory() != null) {
                    existingLaboratoryInstrument.setLaboratory(laboratoryInstrument.getLaboratory());
                }
                if (laboratoryInstrument.getInstrumentId() != null) {
                    existingLaboratoryInstrument.setInstrumentId(laboratoryInstrument.getInstrumentId());
                }
                if (laboratoryInstrument.getInstrument() != null) {
                    existingLaboratoryInstrument.setInstrument(laboratoryInstrument.getInstrument());
                }
                if (laboratoryInstrument.getDepartmentId() != null) {
                    existingLaboratoryInstrument.setDepartmentId(laboratoryInstrument.getDepartmentId());
                }
                if (laboratoryInstrument.getDepartment() != null) {
                    existingLaboratoryInstrument.setDepartment(laboratoryInstrument.getDepartment());
                }

                return existingLaboratoryInstrument;
            })
            .map(laboratoryInstrumentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LaboratoryInstrument> findAll(Pageable pageable) {
        log.debug("Request to get all LaboratoryInstruments");
        return laboratoryInstrumentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LaboratoryInstrument> findOne(Long id) {
        log.debug("Request to get LaboratoryInstrument : {}", id);
        return laboratoryInstrumentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LaboratoryInstrument : {}", id);
        laboratoryInstrumentRepository.deleteById(id);
    }
}
