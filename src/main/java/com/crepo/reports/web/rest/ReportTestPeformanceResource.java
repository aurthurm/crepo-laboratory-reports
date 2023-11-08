package com.crepo.reports.web.rest;

import com.crepo.reports.domain.ReportTestPeformance;
import com.crepo.reports.repository.ReportTestPeformanceRepository;
import com.crepo.reports.service.ReportTestPeformanceService;
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
 * REST controller for managing {@link com.crepo.reports.domain.ReportTestPeformance}.
 */
@RestController
@RequestMapping("/api/report-test-peformances")
public class ReportTestPeformanceResource {

    private final Logger log = LoggerFactory.getLogger(ReportTestPeformanceResource.class);

    private static final String ENTITY_NAME = "reportTestPeformance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportTestPeformanceService reportTestPeformanceService;

    private final ReportTestPeformanceRepository reportTestPeformanceRepository;

    public ReportTestPeformanceResource(
        ReportTestPeformanceService reportTestPeformanceService,
        ReportTestPeformanceRepository reportTestPeformanceRepository
    ) {
        this.reportTestPeformanceService = reportTestPeformanceService;
        this.reportTestPeformanceRepository = reportTestPeformanceRepository;
    }

    /**
     * {@code POST  /report-test-peformances} : Create a new reportTestPeformance.
     *
     * @param reportTestPeformance the reportTestPeformance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportTestPeformance, or with status {@code 400 (Bad Request)} if the reportTestPeformance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportTestPeformance> createReportTestPeformance(@Valid @RequestBody ReportTestPeformance reportTestPeformance)
        throws URISyntaxException {
        log.debug("REST request to save ReportTestPeformance : {}", reportTestPeformance);
        if (reportTestPeformance.getId() != null) {
            throw new BadRequestAlertException("A new reportTestPeformance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportTestPeformance result = reportTestPeformanceService.save(reportTestPeformance);
        return ResponseEntity
            .created(new URI("/api/report-test-peformances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-test-peformances/:id} : Updates an existing reportTestPeformance.
     *
     * @param id the id of the reportTestPeformance to save.
     * @param reportTestPeformance the reportTestPeformance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportTestPeformance,
     * or with status {@code 400 (Bad Request)} if the reportTestPeformance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportTestPeformance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportTestPeformance> updateReportTestPeformance(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReportTestPeformance reportTestPeformance
    ) throws URISyntaxException {
        log.debug("REST request to update ReportTestPeformance : {}, {}", id, reportTestPeformance);
        if (reportTestPeformance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportTestPeformance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportTestPeformanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportTestPeformance result = reportTestPeformanceService.update(reportTestPeformance);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportTestPeformance.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /report-test-peformances/:id} : Partial updates given fields of an existing reportTestPeformance, field will ignore if it is null
     *
     * @param id the id of the reportTestPeformance to save.
     * @param reportTestPeformance the reportTestPeformance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportTestPeformance,
     * or with status {@code 400 (Bad Request)} if the reportTestPeformance is not valid,
     * or with status {@code 404 (Not Found)} if the reportTestPeformance is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportTestPeformance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportTestPeformance> partialUpdateReportTestPeformance(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReportTestPeformance reportTestPeformance
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportTestPeformance partially : {}, {}", id, reportTestPeformance);
        if (reportTestPeformance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportTestPeformance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportTestPeformanceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportTestPeformance> result = reportTestPeformanceService.partialUpdate(reportTestPeformance);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportTestPeformance.getId().toString())
        );
    }

    /**
     * {@code GET  /report-test-peformances} : get all the reportTestPeformances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportTestPeformances in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportTestPeformance>> getAllReportTestPeformances(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ReportTestPeformances");
        Page<ReportTestPeformance> page = reportTestPeformanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-test-peformances/:id} : get the "id" reportTestPeformance.
     *
     * @param id the id of the reportTestPeformance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportTestPeformance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportTestPeformance> getReportTestPeformance(@PathVariable Long id) {
        log.debug("REST request to get ReportTestPeformance : {}", id);
        Optional<ReportTestPeformance> reportTestPeformance = reportTestPeformanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportTestPeformance);
    }

    /**
     * {@code DELETE  /report-test-peformances/:id} : delete the "id" reportTestPeformance.
     *
     * @param id the id of the reportTestPeformance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportTestPeformance(@PathVariable Long id) {
        log.debug("REST request to delete ReportTestPeformance : {}", id);
        reportTestPeformanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
