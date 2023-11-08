package com.crepo.reports.web.rest;

import com.crepo.reports.domain.ReportActivityUpdate;
import com.crepo.reports.repository.ReportActivityUpdateRepository;
import com.crepo.reports.service.ReportActivityUpdateService;
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
 * REST controller for managing {@link com.crepo.reports.domain.ReportActivityUpdate}.
 */
@RestController
@RequestMapping("/api/report-activity-updates")
public class ReportActivityUpdateResource {

    private final Logger log = LoggerFactory.getLogger(ReportActivityUpdateResource.class);

    private static final String ENTITY_NAME = "reportActivityUpdate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportActivityUpdateService reportActivityUpdateService;

    private final ReportActivityUpdateRepository reportActivityUpdateRepository;

    public ReportActivityUpdateResource(
        ReportActivityUpdateService reportActivityUpdateService,
        ReportActivityUpdateRepository reportActivityUpdateRepository
    ) {
        this.reportActivityUpdateService = reportActivityUpdateService;
        this.reportActivityUpdateRepository = reportActivityUpdateRepository;
    }

    /**
     * {@code POST  /report-activity-updates} : Create a new reportActivityUpdate.
     *
     * @param reportActivityUpdate the reportActivityUpdate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportActivityUpdate, or with status {@code 400 (Bad Request)} if the reportActivityUpdate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportActivityUpdate> createReportActivityUpdate(@Valid @RequestBody ReportActivityUpdate reportActivityUpdate)
        throws URISyntaxException {
        log.debug("REST request to save ReportActivityUpdate : {}", reportActivityUpdate);
        if (reportActivityUpdate.getId() != null) {
            throw new BadRequestAlertException("A new reportActivityUpdate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportActivityUpdate result = reportActivityUpdateService.save(reportActivityUpdate);
        return ResponseEntity
            .created(new URI("/api/report-activity-updates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-activity-updates/:id} : Updates an existing reportActivityUpdate.
     *
     * @param id the id of the reportActivityUpdate to save.
     * @param reportActivityUpdate the reportActivityUpdate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportActivityUpdate,
     * or with status {@code 400 (Bad Request)} if the reportActivityUpdate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportActivityUpdate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportActivityUpdate> updateReportActivityUpdate(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReportActivityUpdate reportActivityUpdate
    ) throws URISyntaxException {
        log.debug("REST request to update ReportActivityUpdate : {}, {}", id, reportActivityUpdate);
        if (reportActivityUpdate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportActivityUpdate.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportActivityUpdateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportActivityUpdate result = reportActivityUpdateService.update(reportActivityUpdate);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportActivityUpdate.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /report-activity-updates/:id} : Partial updates given fields of an existing reportActivityUpdate, field will ignore if it is null
     *
     * @param id the id of the reportActivityUpdate to save.
     * @param reportActivityUpdate the reportActivityUpdate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportActivityUpdate,
     * or with status {@code 400 (Bad Request)} if the reportActivityUpdate is not valid,
     * or with status {@code 404 (Not Found)} if the reportActivityUpdate is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportActivityUpdate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportActivityUpdate> partialUpdateReportActivityUpdate(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReportActivityUpdate reportActivityUpdate
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportActivityUpdate partially : {}, {}", id, reportActivityUpdate);
        if (reportActivityUpdate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportActivityUpdate.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportActivityUpdateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportActivityUpdate> result = reportActivityUpdateService.partialUpdate(reportActivityUpdate);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportActivityUpdate.getId().toString())
        );
    }

    /**
     * {@code GET  /report-activity-updates} : get all the reportActivityUpdates.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportActivityUpdates in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportActivityUpdate>> getAllReportActivityUpdates(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ReportActivityUpdates");
        Page<ReportActivityUpdate> page = reportActivityUpdateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-activity-updates/:id} : get the "id" reportActivityUpdate.
     *
     * @param id the id of the reportActivityUpdate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportActivityUpdate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportActivityUpdate> getReportActivityUpdate(@PathVariable Long id) {
        log.debug("REST request to get ReportActivityUpdate : {}", id);
        Optional<ReportActivityUpdate> reportActivityUpdate = reportActivityUpdateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportActivityUpdate);
    }

    /**
     * {@code DELETE  /report-activity-updates/:id} : delete the "id" reportActivityUpdate.
     *
     * @param id the id of the reportActivityUpdate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportActivityUpdate(@PathVariable Long id) {
        log.debug("REST request to delete ReportActivityUpdate : {}", id);
        reportActivityUpdateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
