package com.crepo.reports.service.impl;

import com.crepo.reports.domain.ReportTestPeformance;
import com.crepo.reports.repository.ReportTestPeformanceRepository;
import com.crepo.reports.service.ReportTestPeformanceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.ReportTestPeformance}.
 */
@Service
@Transactional
public class ReportTestPeformanceServiceImpl implements ReportTestPeformanceService {

    private final Logger log = LoggerFactory.getLogger(ReportTestPeformanceServiceImpl.class);

    private final ReportTestPeformanceRepository reportTestPeformanceRepository;

    public ReportTestPeformanceServiceImpl(ReportTestPeformanceRepository reportTestPeformanceRepository) {
        this.reportTestPeformanceRepository = reportTestPeformanceRepository;
    }

    @Override
    public ReportTestPeformance save(ReportTestPeformance reportTestPeformance) {
        log.debug("Request to save ReportTestPeformance : {}", reportTestPeformance);
        return reportTestPeformanceRepository.save(reportTestPeformance);
    }

    @Override
    public ReportTestPeformance update(ReportTestPeformance reportTestPeformance) {
        log.debug("Request to update ReportTestPeformance : {}", reportTestPeformance);
        return reportTestPeformanceRepository.save(reportTestPeformance);
    }

    @Override
    public Optional<ReportTestPeformance> partialUpdate(ReportTestPeformance reportTestPeformance) {
        log.debug("Request to partially update ReportTestPeformance : {}", reportTestPeformance);

        return reportTestPeformanceRepository
            .findById(reportTestPeformance.getId())
            .map(existingReportTestPeformance -> {
                if (reportTestPeformance.getLaboratoryId() != null) {
                    existingReportTestPeformance.setLaboratoryId(reportTestPeformance.getLaboratoryId());
                }
                if (reportTestPeformance.getLaboratory() != null) {
                    existingReportTestPeformance.setLaboratory(reportTestPeformance.getLaboratory());
                }
                if (reportTestPeformance.getDepartmentId() != null) {
                    existingReportTestPeformance.setDepartmentId(reportTestPeformance.getDepartmentId());
                }
                if (reportTestPeformance.getDepartment() != null) {
                    existingReportTestPeformance.setDepartment(reportTestPeformance.getDepartment());
                }
                if (reportTestPeformance.getSampleTypeId() != null) {
                    existingReportTestPeformance.setSampleTypeId(reportTestPeformance.getSampleTypeId());
                }
                if (reportTestPeformance.getSampleType() != null) {
                    existingReportTestPeformance.setSampleType(reportTestPeformance.getSampleType());
                }
                if (reportTestPeformance.getTestId() != null) {
                    existingReportTestPeformance.setTestId(reportTestPeformance.getTestId());
                }
                if (reportTestPeformance.getTest() != null) {
                    existingReportTestPeformance.setTest(reportTestPeformance.getTest());
                }
                if (reportTestPeformance.getTurnAroundTime() != null) {
                    existingReportTestPeformance.setTurnAroundTime(reportTestPeformance.getTurnAroundTime());
                }
                if (reportTestPeformance.getNumberTested() != null) {
                    existingReportTestPeformance.setNumberTested(reportTestPeformance.getNumberTested());
                }
                if (reportTestPeformance.getNumberDispatched() != null) {
                    existingReportTestPeformance.setNumberDispatched(reportTestPeformance.getNumberDispatched());
                }
                if (reportTestPeformance.getNumberRejected() != null) {
                    existingReportTestPeformance.setNumberRejected(reportTestPeformance.getNumberRejected());
                }
                if (reportTestPeformance.getInstrumentId() != null) {
                    existingReportTestPeformance.setInstrumentId(reportTestPeformance.getInstrumentId());
                }
                if (reportTestPeformance.getInstrument() != null) {
                    existingReportTestPeformance.setInstrument(reportTestPeformance.getInstrument());
                }
                if (reportTestPeformance.getCriticalResults() != null) {
                    existingReportTestPeformance.setCriticalResults(reportTestPeformance.getCriticalResults());
                }
                if (reportTestPeformance.getNumberCritical() != null) {
                    existingReportTestPeformance.setNumberCritical(reportTestPeformance.getNumberCritical());
                }
                if (reportTestPeformance.getCriticalComment() != null) {
                    existingReportTestPeformance.setCriticalComment(reportTestPeformance.getCriticalComment());
                }
                if (reportTestPeformance.getReportingPeriodId() != null) {
                    existingReportTestPeformance.setReportingPeriodId(reportTestPeformance.getReportingPeriodId());
                }

                return existingReportTestPeformance;
            })
            .map(reportTestPeformanceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportTestPeformance> findAll(Pageable pageable) {
        log.debug("Request to get all ReportTestPeformances");
        return reportTestPeformanceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportTestPeformance> findOne(Long id) {
        log.debug("Request to get ReportTestPeformance : {}", id);
        return reportTestPeformanceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportTestPeformance : {}", id);
        reportTestPeformanceRepository.deleteById(id);
    }
}
