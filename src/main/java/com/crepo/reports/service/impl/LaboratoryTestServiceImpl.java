package com.crepo.reports.service.impl;

import com.crepo.reports.domain.LaboratoryTest;
import com.crepo.reports.repository.LaboratoryTestRepository;
import com.crepo.reports.service.LaboratoryTestService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.LaboratoryTest}.
 */
@Service
@Transactional
public class LaboratoryTestServiceImpl implements LaboratoryTestService {

    private final Logger log = LoggerFactory.getLogger(LaboratoryTestServiceImpl.class);

    private final LaboratoryTestRepository laboratoryTestRepository;

    public LaboratoryTestServiceImpl(LaboratoryTestRepository laboratoryTestRepository) {
        this.laboratoryTestRepository = laboratoryTestRepository;
    }

    @Override
    public LaboratoryTest save(LaboratoryTest laboratoryTest) {
        log.debug("Request to save LaboratoryTest : {}", laboratoryTest);
        return laboratoryTestRepository.save(laboratoryTest);
    }

    @Override
    public LaboratoryTest update(LaboratoryTest laboratoryTest) {
        log.debug("Request to update LaboratoryTest : {}", laboratoryTest);
        return laboratoryTestRepository.save(laboratoryTest);
    }

    @Override
    public Optional<LaboratoryTest> partialUpdate(LaboratoryTest laboratoryTest) {
        log.debug("Request to partially update LaboratoryTest : {}", laboratoryTest);

        return laboratoryTestRepository
            .findById(laboratoryTest.getId())
            .map(existingLaboratoryTest -> {
                if (laboratoryTest.getLaboratoryId() != null) {
                    existingLaboratoryTest.setLaboratoryId(laboratoryTest.getLaboratoryId());
                }
                if (laboratoryTest.getLaboratory() != null) {
                    existingLaboratoryTest.setLaboratory(laboratoryTest.getLaboratory());
                }
                if (laboratoryTest.getTestId() != null) {
                    existingLaboratoryTest.setTestId(laboratoryTest.getTestId());
                }
                if (laboratoryTest.getTest() != null) {
                    existingLaboratoryTest.setTest(laboratoryTest.getTest());
                }
                if (laboratoryTest.getDepartmentId() != null) {
                    existingLaboratoryTest.setDepartmentId(laboratoryTest.getDepartmentId());
                }
                if (laboratoryTest.getDepartment() != null) {
                    existingLaboratoryTest.setDepartment(laboratoryTest.getDepartment());
                }

                return existingLaboratoryTest;
            })
            .map(laboratoryTestRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LaboratoryTest> findAll(Pageable pageable) {
        log.debug("Request to get all LaboratoryTests");
        return laboratoryTestRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LaboratoryTest> findOne(Long id) {
        log.debug("Request to get LaboratoryTest : {}", id);
        return laboratoryTestRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LaboratoryTest : {}", id);
        laboratoryTestRepository.deleteById(id);
    }
}
