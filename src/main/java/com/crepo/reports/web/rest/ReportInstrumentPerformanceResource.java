package com.crepo.reports.web.rest;

import com.crepo.reports.domain.ReportInstrumentPerformance;
import com.crepo.reports.repository.ReportInstrumentPerformanceRepository;
import com.crepo.reports.service.ReportInstrumentPerformanceService;
import com.crepo.reports.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.crepo.reports.domain.ReportInstrumentPerformance}.
 */
@RestController
@RequestMapping("/api/report-instrument-performances")
public class ReportInstrumentPerformanceResource {

    private final Logger log = LoggerFactory.getLogger(ReportInstrumentPerformanceResource.class);

    private static final String ENTITY_NAME = "reportInstrumentPerformance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportInstrumentPerformanceService reportInstrumentPerformanceService;

    private final ReportInstrumentPerformanceRepository reportInstrumentPerformanceRepository;

    public ReportInstrumentPerformanceResource(
        ReportInstrumentPerformanceService reportInstrumentPerformanceService,
        ReportInstrumentPerformanceRepository reportInstrumentPerformanceRepository
    ) {
        this.reportInstrumentPerformanceService = reportInstrumentPerformanceService;
        this.reportInstrumentPerformanceRepository = reportInstrumentPerformanceRepository;
    }

    /**
     * {@code POST  /report-instrument-performances} : Create a new reportInstrumentPerformance.
     *
     * @param reportInstrumentPerformance the reportInstrumentPerformance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportInstrumentPerformance, or with status {@code 400 (Bad Request)} if the reportInstrumentPerformance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportInstrumentPerformance> createReportInstrumentPerformance(
        @Valid @RequestBody ReportInstrumentPerformance reportInstrumentPerformance
    ) throws URISyntaxException {
        log.debug("REST request to save ReportInstrumentPerformance : {}", reportInstrumentPerformance);
        if (reportInstrumentPerformance.getId() != null) {
            throw new BadRequestAlertException("A new reportInstrumentPerformance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportInstrumentPerformance result = reportInstrumentPerformanceService.save(reportInstrumentPerformance);
        return ResponseEntity
            .created(new URI("/api/report-instrument-performances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-instrument-performances/:id} : Updates an existing reportInstrumentPerformance.
     *
     * @param id the id of the reportInstrumentPerformance to save.
     * @param reportInstrumentPerformance the reportInstrumentPerformance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportInstrumentPerformance,
     * or with status {@code 400 (Bad Request)} if the reportInstrumentPerformance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportInstrumentPerformance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportInstrumentPerformance> updateReportInstrumentPerformance(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReportInstrumentPerformance reportInstrumentPerformance
    ) throws URISyntaxException {
        log.debug("REST request to update ReportInstrumentPerformance : {}, {}", id, reportInstrumentPerformance);
        if (reportInstrumentPerformance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportInstrumentPerformance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportInstrumentPerformanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportInstrumentPerformance result = reportInstrumentPerformanceService.update(reportInstrumentPerformance);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportInstrumentPerformance.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /report-instrument-performances/:id} : Partial updates given fields of an existing reportInstrumentPerformance, field will ignore if it is null
     *
     * @param id the id of the reportInstrumentPerformance to save.
     * @param reportInstrumentPerformance the reportInstrumentPerformance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportInstrumentPerformance,
     * or with status {@code 400 (Bad Request)} if the reportInstrumentPerformance is not valid,
     * or with status {@code 404 (Not Found)} if the reportInstrumentPerformance is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportInstrumentPerformance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportInstrumentPerformance> partialUpdateReportInstrumentPerformance(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReportInstrumentPerformance reportInstrumentPerformance
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportInstrumentPerformance partially : {}, {}", id, reportInstrumentPerformance);
        if (reportInstrumentPerformance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportInstrumentPerformance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportInstrumentPerformanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportInstrumentPerformance> result = reportInstrumentPerformanceService.partialUpdate(reportInstrumentPerformance);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportInstrumentPerformance.getId().toString())
        );
    }

    /**
     * {@code GET  /report-instrument-performances} : get all the reportInstrumentPerformances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportInstrumentPerformances in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportInstrumentPerformance>> getAllReportInstrumentPerformances(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ReportInstrumentPerformances");
        Page<ReportInstrumentPerformance> page = reportInstrumentPerformanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-instrument-performances/:id} : get the "id" reportInstrumentPerformance.
     *
     * @param id the id of the reportInstrumentPerformance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportInstrumentPerformance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportInstrumentPerformance> getReportInstrumentPerformance(@PathVariable Long id) {
        log.debug("REST request to get ReportInstrumentPerformance : {}", id);
        Optional<ReportInstrumentPerformance> reportInstrumentPerformance = reportInstrumentPerformanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportInstrumentPerformance);
    }

    /**
     * {@code DELETE  /report-instrument-performances/:id} : delete the "id" reportInstrumentPerformance.
     *
     * @param id the id of the reportInstrumentPerformance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportInstrumentPerformance(@PathVariable Long id) {
        log.debug("REST request to delete ReportInstrumentPerformance : {}", id);
        reportInstrumentPerformanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
