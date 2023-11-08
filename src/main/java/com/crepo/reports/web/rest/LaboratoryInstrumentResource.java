package com.crepo.reports.web.rest;

import com.crepo.reports.domain.LaboratoryInstrument;
import com.crepo.reports.repository.LaboratoryInstrumentRepository;
import com.crepo.reports.service.LaboratoryInstrumentService;
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
 * REST controller for managing {@link com.crepo.reports.domain.LaboratoryInstrument}.
 */
@RestController
@RequestMapping("/api/laboratory-instruments")
public class LaboratoryInstrumentResource {

    private final Logger log = LoggerFactory.getLogger(LaboratoryInstrumentResource.class);

    private static final String ENTITY_NAME = "laboratoryInstrument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LaboratoryInstrumentService laboratoryInstrumentService;

    private final LaboratoryInstrumentRepository laboratoryInstrumentRepository;

    public LaboratoryInstrumentResource(
        LaboratoryInstrumentService laboratoryInstrumentService,
        LaboratoryInstrumentRepository laboratoryInstrumentRepository
    ) {
        this.laboratoryInstrumentService = laboratoryInstrumentService;
        this.laboratoryInstrumentRepository = laboratoryInstrumentRepository;
    }

    /**
     * {@code POST  /laboratory-instruments} : Create a new laboratoryInstrument.
     *
     * @param laboratoryInstrument the laboratoryInstrument to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new laboratoryInstrument, or with status {@code 400 (Bad Request)} if the laboratoryInstrument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LaboratoryInstrument> createLaboratoryInstrument(@Valid @RequestBody LaboratoryInstrument laboratoryInstrument)
        throws URISyntaxException {
        log.debug("REST request to save LaboratoryInstrument : {}", laboratoryInstrument);
        if (laboratoryInstrument.getId() != null) {
            throw new BadRequestAlertException("A new laboratoryInstrument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LaboratoryInstrument result = laboratoryInstrumentService.save(laboratoryInstrument);
        return ResponseEntity
            .created(new URI("/api/laboratory-instruments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /laboratory-instruments/:id} : Updates an existing laboratoryInstrument.
     *
     * @param id the id of the laboratoryInstrument to save.
     * @param laboratoryInstrument the laboratoryInstrument to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryInstrument,
     * or with status {@code 400 (Bad Request)} if the laboratoryInstrument is not valid,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryInstrument couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryInstrument> updateLaboratoryInstrument(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LaboratoryInstrument laboratoryInstrument
    ) throws URISyntaxException {
        log.debug("REST request to update LaboratoryInstrument : {}, {}", id, laboratoryInstrument);
        if (laboratoryInstrument.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, laboratoryInstrument.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!laboratoryInstrumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LaboratoryInstrument result = laboratoryInstrumentService.update(laboratoryInstrument);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryInstrument.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /laboratory-instruments/:id} : Partial updates given fields of an existing laboratoryInstrument, field will ignore if it is null
     *
     * @param id the id of the laboratoryInstrument to save.
     * @param laboratoryInstrument the laboratoryInstrument to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryInstrument,
     * or with status {@code 400 (Bad Request)} if the laboratoryInstrument is not valid,
     * or with status {@code 404 (Not Found)} if the laboratoryInstrument is not found,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryInstrument couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LaboratoryInstrument> partialUpdateLaboratoryInstrument(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LaboratoryInstrument laboratoryInstrument
    ) throws URISyntaxException {
        log.debug("REST request to partial update LaboratoryInstrument partially : {}, {}", id, laboratoryInstrument);
        if (laboratoryInstrument.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, laboratoryInstrument.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!laboratoryInstrumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LaboratoryInstrument> result = laboratoryInstrumentService.partialUpdate(laboratoryInstrument);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryInstrument.getId().toString())
        );
    }

    /**
     * {@code GET  /laboratory-instruments} : get all the laboratoryInstruments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of laboratoryInstruments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LaboratoryInstrument>> getAllLaboratoryInstruments(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of LaboratoryInstruments");
        Page<LaboratoryInstrument> page = laboratoryInstrumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /laboratory-instruments/:id} : get the "id" laboratoryInstrument.
     *
     * @param id the id of the laboratoryInstrument to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the laboratoryInstrument, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryInstrument> getLaboratoryInstrument(@PathVariable Long id) {
        log.debug("REST request to get LaboratoryInstrument : {}", id);
        Optional<LaboratoryInstrument> laboratoryInstrument = laboratoryInstrumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(laboratoryInstrument);
    }

    /**
     * {@code DELETE  /laboratory-instruments/:id} : delete the "id" laboratoryInstrument.
     *
     * @param id the id of the laboratoryInstrument to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoryInstrument(@PathVariable Long id) {
        log.debug("REST request to delete LaboratoryInstrument : {}", id);
        laboratoryInstrumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
