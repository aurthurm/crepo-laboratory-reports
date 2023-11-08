package com.crepo.reports.service.impl;

import com.crepo.reports.domain.ReportInstrumentPerformance;
import com.crepo.reports.repository.ReportInstrumentPerformanceRepository;
import com.crepo.reports.service.ReportInstrumentPerformanceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.ReportInstrumentPerformance}.
 */
@Service
@Transactional
public class ReportInstrumentPerformanceServiceImpl implements ReportInstrumentPerformanceService {

    private final Logger log = LoggerFactory.getLogger(ReportInstrumentPerformanceServiceImpl.class);

    private final ReportInstrumentPerformanceRepository reportInstrumentPerformanceRepository;

    public ReportInstrumentPerformanceServiceImpl(ReportInstrumentPerformanceRepository reportInstrumentPerformanceRepository) {
        this.reportInstrumentPerformanceRepository = reportInstrumentPerformanceRepository;
    }

    @Override
    public ReportInstrumentPerformance save(ReportInstrumentPerformance reportInstrumentPerformance) {
        log.debug("Request to save ReportInstrumentPerformance : {}", reportInstrumentPerformance);
        return reportInstrumentPerformanceRepository.save(reportInstrumentPerformance);
    }

    @Override
    public ReportInstrumentPerformance update(ReportInstrumentPerformance reportInstrumentPerformance) {
        log.debug("Request to update ReportInstrumentPerformance : {}", reportInstrumentPerformance);
        return reportInstrumentPerformanceRepository.save(reportInstrumentPerformance);
    }

    @Override
    public Optional<ReportInstrumentPerformance> partialUpdate(ReportInstrumentPerformance reportInstrumentPerformance) {
        log.debug("Request to partially update ReportInstrumentPerformance : {}", reportInstrumentPerformance);

        return reportInstrumentPerformanceRepository
            .findById(reportInstrumentPerformance.getId())
            .map(existingReportInstrumentPerformance -> {
                if (reportInstrumentPerformance.getLaboratoryId() != null) {
                    existingReportInstrumentPerformance.setLaboratoryId(reportInstrumentPerformance.getLaboratoryId());
                }
                if (reportInstrumentPerformance.getLaboratory() != null) {
                    existingReportInstrumentPerformance.setLaboratory(reportInstrumentPerformance.getLaboratory());
                }
                if (reportInstrumentPerformance.getDepartmentId() != null) {
                    existingReportInstrumentPerformance.setDepartmentId(reportInstrumentPerformance.getDepartmentId());
                }
                if (reportInstrumentPerformance.getDepartment() != null) {
                    existingReportInstrumentPerformance.setDepartment(reportInstrumentPerformance.getDepartment());
                }
                if (reportInstrumentPerformance.getInstrumentId() != null) {
                    existingReportInstrumentPerformance.setInstrumentId(reportInstrumentPerformance.getInstrumentId());
                }
                if (reportInstrumentPerformance.getInstrument() != null) {
                    existingReportInstrumentPerformance.setInstrument(reportInstrumentPerformance.getInstrument());
                }
                if (reportInstrumentPerformance.getStatus() != null) {
                    existingReportInstrumentPerformance.setStatus(reportInstrumentPerformance.getStatus());
                }
                if (reportInstrumentPerformance.getUptime() != null) {
                    existingReportInstrumentPerformance.setUptime(reportInstrumentPerformance.getUptime());
                }
                if (reportInstrumentPerformance.getDowntime() != null) {
                    existingReportInstrumentPerformance.setDowntime(reportInstrumentPerformance.getDowntime());
                }
                if (reportInstrumentPerformance.getServiceStatus() != null) {
                    existingReportInstrumentPerformance.setServiceStatus(reportInstrumentPerformance.getServiceStatus());
                }
                if (reportInstrumentPerformance.getCaliberationStatus() != null) {
                    existingReportInstrumentPerformance.setCaliberationStatus(reportInstrumentPerformance.getCaliberationStatus());
                }
                if (reportInstrumentPerformance.getFunctionality() != null) {
                    existingReportInstrumentPerformance.setFunctionality(reportInstrumentPerformance.getFunctionality());
                }
                if (reportInstrumentPerformance.getComment() != null) {
                    existingReportInstrumentPerformance.setComment(reportInstrumentPerformance.getComment());
                }
                if (reportInstrumentPerformance.getReportingPeriodId() != null) {
                    existingReportInstrumentPerformance.setReportingPeriodId(reportInstrumentPerformance.getReportingPeriodId());
                }

                return existingReportInstrumentPerformance;
            })
            .map(reportInstrumentPerformanceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportInstrumentPerformance> findAll(Pageable pageable) {
        log.debug("Request to get all ReportInstrumentPerformances");
        return reportInstrumentPerformanceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportInstrumentPerformance> findOne(Long id) {
        log.debug("Request to get ReportInstrumentPerformance : {}", id);
        return reportInstrumentPerformanceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportInstrumentPerformance : {}", id);
        reportInstrumentPerformanceRepository.deleteById(id);
    }
}
