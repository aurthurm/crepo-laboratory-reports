package com.crepo.reports.web.rest;

import com.crepo.reports.domain.ReportingPeriod;
import com.crepo.reports.repository.ReportingPeriodRepository;
import com.crepo.reports.service.ReportingPeriodService;
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
 * REST controller for managing {@link com.crepo.reports.domain.ReportingPeriod}.
 */
@RestController
@RequestMapping("/api/reporting-periods")
public class ReportingPeriodResource {

    private final Logger log = LoggerFactory.getLogger(ReportingPeriodResource.class);

    private static final String ENTITY_NAME = "reportingPeriod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportingPeriodService reportingPeriodService;

    private final ReportingPeriodRepository reportingPeriodRepository;

    public ReportingPeriodResource(ReportingPeriodService reportingPeriodService, ReportingPeriodRepository reportingPeriodRepository) {
        this.reportingPeriodService = reportingPeriodService;
        this.reportingPeriodRepository = reportingPeriodRepository;
    }

    /**
     * {@code POST  /reporting-periods} : Create a new reportingPeriod.
     *
     * @param reportingPeriod the reportingPeriod to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportingPeriod, or with status {@code 400 (Bad Request)} if the reportingPeriod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportingPeriod> createReportingPeriod(@Valid @RequestBody ReportingPeriod reportingPeriod)
        throws URISyntaxException {
        log.debug("REST request to save ReportingPeriod : {}", reportingPeriod);
        if (reportingPeriod.getId() != null) {
            throw new BadRequestAlertException("A new reportingPeriod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportingPeriod result = reportingPeriodService.save(reportingPeriod);
        return ResponseEntity
            .created(new URI("/api/reporting-periods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reporting-periods/:id} : Updates an existing reportingPeriod.
     *
     * @param id the id of the reportingPeriod to save.
     * @param reportingPeriod the reportingPeriod to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportingPeriod,
     * or with status {@code 400 (Bad Request)} if the reportingPeriod is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportingPeriod couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportingPeriod> updateReportingPeriod(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReportingPeriod reportingPeriod
    ) throws URISyntaxException {
        log.debug("REST request to update ReportingPeriod : {}, {}", id, reportingPeriod);
        if (reportingPeriod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportingPeriod.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportingPeriodRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportingPeriod result = reportingPeriodService.update(reportingPeriod);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportingPeriod.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reporting-periods/:id} : Partial updates given fields of an existing reportingPeriod, field will ignore if it is null
     *
     * @param id the id of the reportingPeriod to save.
     * @param reportingPeriod the reportingPeriod to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportingPeriod,
     * or with status {@code 400 (Bad Request)} if the reportingPeriod is not valid,
     * or with status {@code 404 (Not Found)} if the reportingPeriod is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportingPeriod couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportingPeriod> partialUpdateReportingPeriod(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReportingPeriod reportingPeriod
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportingPeriod partially : {}, {}", id, reportingPeriod);
        if (reportingPeriod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportingPeriod.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportingPeriodRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportingPeriod> result = reportingPeriodService.partialUpdate(reportingPeriod);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportingPeriod.getId().toString())
        );
    }

    /**
     * {@code GET  /reporting-periods} : get all the reportingPeriods.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportingPeriods in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportingPeriod>> getAllReportingPeriods(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ReportingPeriods");
        Page<ReportingPeriod> page = reportingPeriodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reporting-periods/:id} : get the "id" reportingPeriod.
     *
     * @param id the id of the reportingPeriod to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportingPeriod, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportingPeriod> getReportingPeriod(@PathVariable Long id) {
        log.debug("REST request to get ReportingPeriod : {}", id);
        Optional<ReportingPeriod> reportingPeriod = reportingPeriodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportingPeriod);
    }

    /**
     * {@code DELETE  /reporting-periods/:id} : delete the "id" reportingPeriod.
     *
     * @param id the id of the reportingPeriod to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportingPeriod(@PathVariable Long id) {
        log.debug("REST request to delete ReportingPeriod : {}", id);
        reportingPeriodService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
