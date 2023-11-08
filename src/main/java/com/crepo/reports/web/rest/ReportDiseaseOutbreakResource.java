package com.crepo.reports.web.rest;

import com.crepo.reports.domain.ReportDiseaseOutbreak;
import com.crepo.reports.repository.ReportDiseaseOutbreakRepository;
import com.crepo.reports.service.ReportDiseaseOutbreakService;
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
 * REST controller for managing {@link com.crepo.reports.domain.ReportDiseaseOutbreak}.
 */
@RestController
@RequestMapping("/api/report-disease-outbreaks")
public class ReportDiseaseOutbreakResource {

    private final Logger log = LoggerFactory.getLogger(ReportDiseaseOutbreakResource.class);

    private static final String ENTITY_NAME = "reportDiseaseOutbreak";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportDiseaseOutbreakService reportDiseaseOutbreakService;

    private final ReportDiseaseOutbreakRepository reportDiseaseOutbreakRepository;

    public ReportDiseaseOutbreakResource(
        ReportDiseaseOutbreakService reportDiseaseOutbreakService,
        ReportDiseaseOutbreakRepository reportDiseaseOutbreakRepository
    ) {
        this.reportDiseaseOutbreakService = reportDiseaseOutbreakService;
        this.reportDiseaseOutbreakRepository = reportDiseaseOutbreakRepository;
    }

    /**
     * {@code POST  /report-disease-outbreaks} : Create a new reportDiseaseOutbreak.
     *
     * @param reportDiseaseOutbreak the reportDiseaseOutbreak to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportDiseaseOutbreak, or with status {@code 400 (Bad Request)} if the reportDiseaseOutbreak has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportDiseaseOutbreak> createReportDiseaseOutbreak(
        @Valid @RequestBody ReportDiseaseOutbreak reportDiseaseOutbreak
    ) throws URISyntaxException {
        log.debug("REST request to save ReportDiseaseOutbreak : {}", reportDiseaseOutbreak);
        if (reportDiseaseOutbreak.getId() != null) {
            throw new BadRequestAlertException("A new reportDiseaseOutbreak cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportDiseaseOutbreak result = reportDiseaseOutbreakService.save(reportDiseaseOutbreak);
        return ResponseEntity
            .created(new URI("/api/report-disease-outbreaks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-disease-outbreaks/:id} : Updates an existing reportDiseaseOutbreak.
     *
     * @param id the id of the reportDiseaseOutbreak to save.
     * @param reportDiseaseOutbreak the reportDiseaseOutbreak to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportDiseaseOutbreak,
     * or with status {@code 400 (Bad Request)} if the reportDiseaseOutbreak is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportDiseaseOutbreak couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportDiseaseOutbreak> updateReportDiseaseOutbreak(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReportDiseaseOutbreak reportDiseaseOutbreak
    ) throws URISyntaxException {
        log.debug("REST request to update ReportDiseaseOutbreak : {}, {}", id, reportDiseaseOutbreak);
        if (reportDiseaseOutbreak.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportDiseaseOutbreak.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportDiseaseOutbreakRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportDiseaseOutbreak result = reportDiseaseOutbreakService.update(reportDiseaseOutbreak);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportDiseaseOutbreak.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /report-disease-outbreaks/:id} : Partial updates given fields of an existing reportDiseaseOutbreak, field will ignore if it is null
     *
     * @param id the id of the reportDiseaseOutbreak to save.
     * @param reportDiseaseOutbreak the reportDiseaseOutbreak to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportDiseaseOutbreak,
     * or with status {@code 400 (Bad Request)} if the reportDiseaseOutbreak is not valid,
     * or with status {@code 404 (Not Found)} if the reportDiseaseOutbreak is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportDiseaseOutbreak couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportDiseaseOutbreak> partialUpdateReportDiseaseOutbreak(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReportDiseaseOutbreak reportDiseaseOutbreak
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportDiseaseOutbreak partially : {}, {}", id, reportDiseaseOutbreak);
        if (reportDiseaseOutbreak.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportDiseaseOutbreak.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportDiseaseOutbreakRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportDiseaseOutbreak> result = reportDiseaseOutbreakService.partialUpdate(reportDiseaseOutbreak);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportDiseaseOutbreak.getId().toString())
        );
    }

    /**
     * {@code GET  /report-disease-outbreaks} : get all the reportDiseaseOutbreaks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportDiseaseOutbreaks in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportDiseaseOutbreak>> getAllReportDiseaseOutbreaks(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ReportDiseaseOutbreaks");
        Page<ReportDiseaseOutbreak> page = reportDiseaseOutbreakService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-disease-outbreaks/:id} : get the "id" reportDiseaseOutbreak.
     *
     * @param id the id of the reportDiseaseOutbreak to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportDiseaseOutbreak, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportDiseaseOutbreak> getReportDiseaseOutbreak(@PathVariable Long id) {
        log.debug("REST request to get ReportDiseaseOutbreak : {}", id);
        Optional<ReportDiseaseOutbreak> reportDiseaseOutbreak = reportDiseaseOutbreakService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportDiseaseOutbreak);
    }

    /**
     * {@code DELETE  /report-disease-outbreaks/:id} : delete the "id" reportDiseaseOutbreak.
     *
     * @param id the id of the reportDiseaseOutbreak to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportDiseaseOutbreak(@PathVariable Long id) {
        log.debug("REST request to delete ReportDiseaseOutbreak : {}", id);
        reportDiseaseOutbreakService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
