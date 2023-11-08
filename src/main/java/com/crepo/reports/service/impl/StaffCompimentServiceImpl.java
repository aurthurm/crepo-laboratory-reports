package com.crepo.reports.service.impl;

import com.crepo.reports.domain.StaffCompiment;
import com.crepo.reports.repository.StaffCompimentRepository;
import com.crepo.reports.service.StaffCompimentService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.StaffCompiment}.
 */
@Service
@Transactional
public class StaffCompimentServiceImpl implements StaffCompimentService {

    private final Logger log = LoggerFactory.getLogger(StaffCompimentServiceImpl.class);

    private final StaffCompimentRepository staffCompimentRepository;

    public StaffCompimentServiceImpl(StaffCompimentRepository staffCompimentRepository) {
        this.staffCompimentRepository = staffCompimentRepository;
    }

    @Override
    public StaffCompiment save(StaffCompiment staffCompiment) {
        log.debug("Request to save StaffCompiment : {}", staffCompiment);
        return staffCompimentRepository.save(staffCompiment);
    }

    @Override
    public StaffCompiment update(StaffCompiment staffCompiment) {
        log.debug("Request to update StaffCompiment : {}", staffCompiment);
        return staffCompimentRepository.save(staffCompiment);
    }

    @Override
    public Optional<StaffCompiment> partialUpdate(StaffCompiment staffCompiment) {
        log.debug("Request to partially update StaffCompiment : {}", staffCompiment);

        return staffCompimentRepository
            .findById(staffCompiment.getId())
            .map(existingStaffCompiment -> {
                if (staffCompiment.getLaboratoryId() != null) {
                    existingStaffCompiment.setLaboratoryId(staffCompiment.getLaboratoryId());
                }
                if (staffCompiment.getLaboratory() != null) {
                    existingStaffCompiment.setLaboratory(staffCompiment.getLaboratory());
                }
                if (staffCompiment.getDepartmentId() != null) {
                    existingStaffCompiment.setDepartmentId(staffCompiment.getDepartmentId());
                }
                if (staffCompiment.getDepartment() != null) {
                    existingStaffCompiment.setDepartment(staffCompiment.getDepartment());
                }
                if (staffCompiment.getScientistAvailable() != null) {
                    existingStaffCompiment.setScientistAvailable(staffCompiment.getScientistAvailable());
                }
                if (staffCompiment.getScientistRequired() != null) {
                    existingStaffCompiment.setScientistRequired(staffCompiment.getScientistRequired());
                }
                if (staffCompiment.getMicroscopitsAvailable() != null) {
                    existingStaffCompiment.setMicroscopitsAvailable(staffCompiment.getMicroscopitsAvailable());
                }
                if (staffCompiment.getMicroscopitsRequired() != null) {
                    existingStaffCompiment.setMicroscopitsRequired(staffCompiment.getMicroscopitsRequired());
                }
                if (staffCompiment.getLabTechsAvailable() != null) {
                    existingStaffCompiment.setLabTechsAvailable(staffCompiment.getLabTechsAvailable());
                }
                if (staffCompiment.getLabTechsRequired() != null) {
                    existingStaffCompiment.setLabTechsRequired(staffCompiment.getLabTechsRequired());
                }
                if (staffCompiment.getGeneralHandsAvailable() != null) {
                    existingStaffCompiment.setGeneralHandsAvailable(staffCompiment.getGeneralHandsAvailable());
                }
                if (staffCompiment.getGeneralHandsRequired() != null) {
                    existingStaffCompiment.setGeneralHandsRequired(staffCompiment.getGeneralHandsRequired());
                }

                return existingStaffCompiment;
            })
            .map(staffCompimentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StaffCompiment> findAll(Pageable pageable) {
        log.debug("Request to get all StaffCompiments");
        return staffCompimentRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StaffCompiment> findOne(Long id) {
        log.debug("Request to get StaffCompiment : {}", id);
        return staffCompimentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StaffCompiment : {}", id);
        staffCompimentRepository.deleteById(id);
    }
}
