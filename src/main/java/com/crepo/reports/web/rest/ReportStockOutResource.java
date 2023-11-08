package com.crepo.reports.web.rest;

import com.crepo.reports.domain.ReportStockOut;
import com.crepo.reports.repository.ReportStockOutRepository;
import com.crepo.reports.service.ReportStockOutService;
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
 * REST controller for managing {@link com.crepo.reports.domain.ReportStockOut}.
 */
@RestController
@RequestMapping("/api/report-stock-outs")
public class ReportStockOutResource {

    private final Logger log = LoggerFactory.getLogger(ReportStockOutResource.class);

    private static final String ENTITY_NAME = "reportStockOut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportStockOutService reportStockOutService;

    private final ReportStockOutRepository reportStockOutRepository;

    public ReportStockOutResource(ReportStockOutService reportStockOutService, ReportStockOutRepository reportStockOutRepository) {
        this.reportStockOutService = reportStockOutService;
        this.reportStockOutRepository = reportStockOutRepository;
    }

    /**
     * {@code POST  /report-stock-outs} : Create a new reportStockOut.
     *
     * @param reportStockOut the reportStockOut to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportStockOut, or with status {@code 400 (Bad Request)} if the reportStockOut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ReportStockOut> createReportStockOut(@Valid @RequestBody ReportStockOut reportStockOut)
        throws URISyntaxException {
        log.debug("REST request to save ReportStockOut : {}", reportStockOut);
        if (reportStockOut.getId() != null) {
            throw new BadRequestAlertException("A new reportStockOut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportStockOut result = reportStockOutService.save(reportStockOut);
        return ResponseEntity
            .created(new URI("/api/report-stock-outs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-stock-outs/:id} : Updates an existing reportStockOut.
     *
     * @param id the id of the reportStockOut to save.
     * @param reportStockOut the reportStockOut to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportStockOut,
     * or with status {@code 400 (Bad Request)} if the reportStockOut is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportStockOut couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReportStockOut> updateReportStockOut(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReportStockOut reportStockOut
    ) throws URISyntaxException {
        log.debug("REST request to update ReportStockOut : {}, {}", id, reportStockOut);
        if (reportStockOut.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportStockOut.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportStockOutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportStockOut result = reportStockOutService.update(reportStockOut);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportStockOut.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /report-stock-outs/:id} : Partial updates given fields of an existing reportStockOut, field will ignore if it is null
     *
     * @param id the id of the reportStockOut to save.
     * @param reportStockOut the reportStockOut to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportStockOut,
     * or with status {@code 400 (Bad Request)} if the reportStockOut is not valid,
     * or with status {@code 404 (Not Found)} if the reportStockOut is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportStockOut couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportStockOut> partialUpdateReportStockOut(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReportStockOut reportStockOut
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportStockOut partially : {}, {}", id, reportStockOut);
        if (reportStockOut.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportStockOut.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportStockOutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportStockOut> result = reportStockOutService.partialUpdate(reportStockOut);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportStockOut.getId().toString())
        );
    }

    /**
     * {@code GET  /report-stock-outs} : get all the reportStockOuts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportStockOuts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ReportStockOut>> getAllReportStockOuts(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ReportStockOuts");
        Page<ReportStockOut> page = reportStockOutService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-stock-outs/:id} : get the "id" reportStockOut.
     *
     * @param id the id of the reportStockOut to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportStockOut, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReportStockOut> getReportStockOut(@PathVariable Long id) {
        log.debug("REST request to get ReportStockOut : {}", id);
        Optional<ReportStockOut> reportStockOut = reportStockOutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportStockOut);
    }

    /**
     * {@code DELETE  /report-stock-outs/:id} : delete the "id" reportStockOut.
     *
     * @param id the id of the reportStockOut to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportStockOut(@PathVariable Long id) {
        log.debug("REST request to delete ReportStockOut : {}", id);
        reportStockOutService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
