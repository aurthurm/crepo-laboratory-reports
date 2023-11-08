package com.crepo.reports.web.rest;

import com.crepo.reports.domain.StaffCompiment;
import com.crepo.reports.repository.StaffCompimentRepository;
import com.crepo.reports.service.StaffCompimentService;
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
 * REST controller for managing {@link com.crepo.reports.domain.StaffCompiment}.
 */
@RestController
@RequestMapping("/api/staff-compiments")
public class StaffCompimentResource {

    private final Logger log = LoggerFactory.getLogger(StaffCompimentResource.class);

    private static final String ENTITY_NAME = "staffCompiment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StaffCompimentService staffCompimentService;

    private final StaffCompimentRepository staffCompimentRepository;

    public StaffCompimentResource(StaffCompimentService staffCompimentService, StaffCompimentRepository staffCompimentRepository) {
        this.staffCompimentService = staffCompimentService;
        this.staffCompimentRepository = staffCompimentRepository;
    }

    /**
     * {@code POST  /staff-compiments} : Create a new staffCompiment.
     *
     * @param staffCompiment the staffCompiment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new staffCompiment, or with status {@code 400 (Bad Request)} if the staffCompiment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<StaffCompiment> createStaffCompiment(@Valid @RequestBody StaffCompiment staffCompiment)
        throws URISyntaxException {
        log.debug("REST request to save StaffCompiment : {}", staffCompiment);
        if (staffCompiment.getId() != null) {
            throw new BadRequestAlertException("A new staffCompiment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StaffCompiment result = staffCompimentService.save(staffCompiment);
        return ResponseEntity
            .created(new URI("/api/staff-compiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /staff-compiments/:id} : Updates an existing staffCompiment.
     *
     * @param id the id of the staffCompiment to save.
     * @param staffCompiment the staffCompiment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated staffCompiment,
     * or with status {@code 400 (Bad Request)} if the staffCompiment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the staffCompiment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<StaffCompiment> updateStaffCompiment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody StaffCompiment staffCompiment
    ) throws URISyntaxException {
        log.debug("REST request to update StaffCompiment : {}, {}", id, staffCompiment);
        if (staffCompiment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, staffCompiment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!staffCompimentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StaffCompiment result = staffCompimentService.update(staffCompiment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, staffCompiment.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /staff-compiments/:id} : Partial updates given fields of an existing staffCompiment, field will ignore if it is null
     *
     * @param id the id of the staffCompiment to save.
     * @param staffCompiment the staffCompiment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated staffCompiment,
     * or with status {@code 400 (Bad Request)} if the staffCompiment is not valid,
     * or with status {@code 404 (Not Found)} if the staffCompiment is not found,
     * or with status {@code 500 (Internal Server Error)} if the staffCompiment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StaffCompiment> partialUpdateStaffCompiment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody StaffCompiment staffCompiment
    ) throws URISyntaxException {
        log.debug("REST request to partial update StaffCompiment partially : {}, {}", id, staffCompiment);
        if (staffCompiment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, staffCompiment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!staffCompimentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StaffCompiment> result = staffCompimentService.partialUpdate(staffCompiment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, staffCompiment.getId().toString())
        );
    }

    /**
     * {@code GET  /staff-compiments} : get all the staffCompiments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of staffCompiments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<StaffCompiment>> getAllStaffCompiments(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of StaffCompiments");
        Page<StaffCompiment> page = staffCompimentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /staff-compiments/:id} : get the "id" staffCompiment.
     *
     * @param id the id of the staffCompiment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the staffCompiment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StaffCompiment> getStaffCompiment(@PathVariable Long id) {
        log.debug("REST request to get StaffCompiment : {}", id);
        Optional<StaffCompiment> staffCompiment = staffCompimentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(staffCompiment);
    }

    /**
     * {@code DELETE  /staff-compiments/:id} : delete the "id" staffCompiment.
     *
     * @param id the id of the staffCompiment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffCompiment(@PathVariable Long id) {
        log.debug("REST request to delete StaffCompiment : {}", id);
        staffCompimentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
