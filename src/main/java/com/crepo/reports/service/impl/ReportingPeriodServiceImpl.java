package com.crepo.reports.service.impl;

import com.crepo.reports.domain.ReportingPeriod;
import com.crepo.reports.repository.ReportingPeriodRepository;
import com.crepo.reports.service.ReportingPeriodService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.ReportingPeriod}.
 */
@Service
@Transactional
public class ReportingPeriodServiceImpl implements ReportingPeriodService {

    private final Logger log = LoggerFactory.getLogger(ReportingPeriodServiceImpl.class);

    private final ReportingPeriodRepository reportingPeriodRepository;

    public ReportingPeriodServiceImpl(ReportingPeriodRepository reportingPeriodRepository) {
        this.reportingPeriodRepository = reportingPeriodRepository;
    }

    @Override
    public ReportingPeriod save(ReportingPeriod reportingPeriod) {
        log.debug("Request to save ReportingPeriod : {}", reportingPeriod);
        return reportingPeriodRepository.save(reportingPeriod);
    }

    @Override
    public ReportingPeriod update(ReportingPeriod reportingPeriod) {
        log.debug("Request to update ReportingPeriod : {}", reportingPeriod);
        return reportingPeriodRepository.save(reportingPeriod);
    }

    @Override
    public Optional<ReportingPeriod> partialUpdate(ReportingPeriod reportingPeriod) {
        log.debug("Request to partially update ReportingPeriod : {}", reportingPeriod);

        return reportingPeriodRepository
            .findById(reportingPeriod.getId())
            .map(existingReportingPeriod -> {
                if (reportingPeriod.getDay() != null) {
                    existingReportingPeriod.setDay(reportingPeriod.getDay());
                }
                if (reportingPeriod.getWeek() != null) {
                    existingReportingPeriod.setWeek(reportingPeriod.getWeek());
                }
                if (reportingPeriod.getMonth() != null) {
                    existingReportingPeriod.setMonth(reportingPeriod.getMonth());
                }
                if (reportingPeriod.getYear() != null) {
                    existingReportingPeriod.setYear(reportingPeriod.getYear());
                }

                return existingReportingPeriod;
            })
            .map(reportingPeriodRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportingPeriod> findAll(Pageable pageable) {
        log.debug("Request to get all ReportingPeriods");
        return reportingPeriodRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportingPeriod> findOne(Long id) {
        log.debug("Request to get ReportingPeriod : {}", id);
        return reportingPeriodRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportingPeriod : {}", id);
        reportingPeriodRepository.deleteById(id);
    }
}
