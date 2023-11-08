package com.crepo.reports.service.impl;

import com.crepo.reports.domain.ReportActivityUpdate;
import com.crepo.reports.repository.ReportActivityUpdateRepository;
import com.crepo.reports.service.ReportActivityUpdateService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.ReportActivityUpdate}.
 */
@Service
@Transactional
public class ReportActivityUpdateServiceImpl implements ReportActivityUpdateService {

    private final Logger log = LoggerFactory.getLogger(ReportActivityUpdateServiceImpl.class);

    private final ReportActivityUpdateRepository reportActivityUpdateRepository;

    public ReportActivityUpdateServiceImpl(ReportActivityUpdateRepository reportActivityUpdateRepository) {
        this.reportActivityUpdateRepository = reportActivityUpdateRepository;
    }

    @Override
    public ReportActivityUpdate save(ReportActivityUpdate reportActivityUpdate) {
        log.debug("Request to save ReportActivityUpdate : {}", reportActivityUpdate);
        return reportActivityUpdateRepository.save(reportActivityUpdate);
    }

    @Override
    public ReportActivityUpdate update(ReportActivityUpdate reportActivityUpdate) {
        log.debug("Request to update ReportActivityUpdate : {}", reportActivityUpdate);
        return reportActivityUpdateRepository.save(reportActivityUpdate);
    }

    @Override
    public Optional<ReportActivityUpdate> partialUpdate(ReportActivityUpdate reportActivityUpdate) {
        log.debug("Request to partially update ReportActivityUpdate : {}", reportActivityUpdate);

        return reportActivityUpdateRepository
            .findById(reportActivityUpdate.getId())
            .map(existingReportActivityUpdate -> {
                if (reportActivityUpdate.getLaboratoryId() != null) {
                    existingReportActivityUpdate.setLaboratoryId(reportActivityUpdate.getLaboratoryId());
                }
                if (reportActivityUpdate.getLaboratory() != null) {
                    existingReportActivityUpdate.setLaboratory(reportActivityUpdate.getLaboratory());
                }
                if (reportActivityUpdate.getActivity() != null) {
                    existingReportActivityUpdate.setActivity(reportActivityUpdate.getActivity());
                }
                if (reportActivityUpdate.getActivityDetails() != null) {
                    existingReportActivityUpdate.setActivityDetails(reportActivityUpdate.getActivityDetails());
                }
                if (reportActivityUpdate.getOutcomes() != null) {
                    existingReportActivityUpdate.setOutcomes(reportActivityUpdate.getOutcomes());
                }
                if (reportActivityUpdate.getComments() != null) {
                    existingReportActivityUpdate.setComments(reportActivityUpdate.getComments());
                }
                if (reportActivityUpdate.getReportingPeriodId() != null) {
                    existingReportActivityUpdate.setReportingPeriodId(reportActivityUpdate.getReportingPeriodId());
                }

                return existingReportActivityUpdate;
            })
            .map(reportActivityUpdateRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportActivityUpdate> findAll(Pageable pageable) {
        log.debug("Request to get all ReportActivityUpdates");
        return reportActivityUpdateRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportActivityUpdate> findOne(Long id) {
        log.debug("Request to get ReportActivityUpdate : {}", id);
        return reportActivityUpdateRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportActivityUpdate : {}", id);
        reportActivityUpdateRepository.deleteById(id);
    }
}
