package com.crepo.reports.service.impl;

import com.crepo.reports.domain.ReportDiseaseOutbreak;
import com.crepo.reports.repository.ReportDiseaseOutbreakRepository;
import com.crepo.reports.service.ReportDiseaseOutbreakService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.ReportDiseaseOutbreak}.
 */
@Service
@Transactional
public class ReportDiseaseOutbreakServiceImpl implements ReportDiseaseOutbreakService {

    private final Logger log = LoggerFactory.getLogger(ReportDiseaseOutbreakServiceImpl.class);

    private final ReportDiseaseOutbreakRepository reportDiseaseOutbreakRepository;

    public ReportDiseaseOutbreakServiceImpl(ReportDiseaseOutbreakRepository reportDiseaseOutbreakRepository) {
        this.reportDiseaseOutbreakRepository = reportDiseaseOutbreakRepository;
    }

    @Override
    public ReportDiseaseOutbreak save(ReportDiseaseOutbreak reportDiseaseOutbreak) {
        log.debug("Request to save ReportDiseaseOutbreak : {}", reportDiseaseOutbreak);
        return reportDiseaseOutbreakRepository.save(reportDiseaseOutbreak);
    }

    @Override
    public ReportDiseaseOutbreak update(ReportDiseaseOutbreak reportDiseaseOutbreak) {
        log.debug("Request to update ReportDiseaseOutbreak : {}", reportDiseaseOutbreak);
        return reportDiseaseOutbreakRepository.save(reportDiseaseOutbreak);
    }

    @Override
    public Optional<ReportDiseaseOutbreak> partialUpdate(ReportDiseaseOutbreak reportDiseaseOutbreak) {
        log.debug("Request to partially update ReportDiseaseOutbreak : {}", reportDiseaseOutbreak);

        return reportDiseaseOutbreakRepository
            .findById(reportDiseaseOutbreak.getId())
            .map(existingReportDiseaseOutbreak -> {
                if (reportDiseaseOutbreak.getLaboratoryId() != null) {
                    existingReportDiseaseOutbreak.setLaboratoryId(reportDiseaseOutbreak.getLaboratoryId());
                }
                if (reportDiseaseOutbreak.getLaboratory() != null) {
                    existingReportDiseaseOutbreak.setLaboratory(reportDiseaseOutbreak.getLaboratory());
                }
                if (reportDiseaseOutbreak.getOutbreak() != null) {
                    existingReportDiseaseOutbreak.setOutbreak(reportDiseaseOutbreak.getOutbreak());
                }
                if (reportDiseaseOutbreak.getDisease() != null) {
                    existingReportDiseaseOutbreak.setDisease(reportDiseaseOutbreak.getDisease());
                }
                if (reportDiseaseOutbreak.getComment() != null) {
                    existingReportDiseaseOutbreak.setComment(reportDiseaseOutbreak.getComment());
                }
                if (reportDiseaseOutbreak.getReportingPeriodId() != null) {
                    existingReportDiseaseOutbreak.setReportingPeriodId(reportDiseaseOutbreak.getReportingPeriodId());
                }

                return existingReportDiseaseOutbreak;
            })
            .map(reportDiseaseOutbreakRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportDiseaseOutbreak> findAll(Pageable pageable) {
        log.debug("Request to get all ReportDiseaseOutbreaks");
        return reportDiseaseOutbreakRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportDiseaseOutbreak> findOne(Long id) {
        log.debug("Request to get ReportDiseaseOutbreak : {}", id);
        return reportDiseaseOutbreakRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportDiseaseOutbreak : {}", id);
        reportDiseaseOutbreakRepository.deleteById(id);
    }
}
