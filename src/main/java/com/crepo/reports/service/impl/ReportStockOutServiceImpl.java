package com.crepo.reports.service.impl;

import com.crepo.reports.domain.ReportStockOut;
import com.crepo.reports.repository.ReportStockOutRepository;
import com.crepo.reports.service.ReportStockOutService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.crepo.reports.domain.ReportStockOut}.
 */
@Service
@Transactional
public class ReportStockOutServiceImpl implements ReportStockOutService {

    private final Logger log = LoggerFactory.getLogger(ReportStockOutServiceImpl.class);

    private final ReportStockOutRepository reportStockOutRepository;

    public ReportStockOutServiceImpl(ReportStockOutRepository reportStockOutRepository) {
        this.reportStockOutRepository = reportStockOutRepository;
    }

    @Override
    public ReportStockOut save(ReportStockOut reportStockOut) {
        log.debug("Request to save ReportStockOut : {}", reportStockOut);
        return reportStockOutRepository.save(reportStockOut);
    }

    @Override
    public ReportStockOut update(ReportStockOut reportStockOut) {
        log.debug("Request to update ReportStockOut : {}", reportStockOut);
        return reportStockOutRepository.save(reportStockOut);
    }

    @Override
    public Optional<ReportStockOut> partialUpdate(ReportStockOut reportStockOut) {
        log.debug("Request to partially update ReportStockOut : {}", reportStockOut);

        return reportStockOutRepository
            .findById(reportStockOut.getId())
            .map(existingReportStockOut -> {
                if (reportStockOut.getLaboratoryId() != null) {
                    existingReportStockOut.setLaboratoryId(reportStockOut.getLaboratoryId());
                }
                if (reportStockOut.getLaboratory() != null) {
                    existingReportStockOut.setLaboratory(reportStockOut.getLaboratory());
                }
                if (reportStockOut.getStockItemId() != null) {
                    existingReportStockOut.setStockItemId(reportStockOut.getStockItemId());
                }
                if (reportStockOut.getStockItem() != null) {
                    existingReportStockOut.setStockItem(reportStockOut.getStockItem());
                }
                if (reportStockOut.getDepartmentId() != null) {
                    existingReportStockOut.setDepartmentId(reportStockOut.getDepartmentId());
                }
                if (reportStockOut.getDepartment() != null) {
                    existingReportStockOut.setDepartment(reportStockOut.getDepartment());
                }
                if (reportStockOut.getAvailable() != null) {
                    existingReportStockOut.setAvailable(reportStockOut.getAvailable());
                }
                if (reportStockOut.getComment() != null) {
                    existingReportStockOut.setComment(reportStockOut.getComment());
                }
                if (reportStockOut.getReportingPeriodId() != null) {
                    existingReportStockOut.setReportingPeriodId(reportStockOut.getReportingPeriodId());
                }

                return existingReportStockOut;
            })
            .map(reportStockOutRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportStockOut> findAll(Pageable pageable) {
        log.debug("Request to get all ReportStockOuts");
        return reportStockOutRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReportStockOut> findOne(Long id) {
        log.debug("Request to get ReportStockOut : {}", id);
        return reportStockOutRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportStockOut : {}", id);
        reportStockOutRepository.deleteById(id);
    }
}
