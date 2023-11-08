package com.crepo.reports.web.rest;

import com.crepo.reports.domain.LaboratoryTest;
import com.crepo.reports.repository.LaboratoryTestRepository;
import com.crepo.reports.service.LaboratoryTestService;
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
 * REST controller for managing {@link com.crepo.reports.domain.LaboratoryTest}.
 */
@RestController
@RequestMapping("/api/laboratory-tests")
public class LaboratoryTestResource {

    private final Logger log = LoggerFactory.getLogger(LaboratoryTestResource.class);

    private static final String ENTITY_NAME = "laboratoryTest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LaboratoryTestService laboratoryTestService;

    private final LaboratoryTestRepository laboratoryTestRepository;

    public LaboratoryTestResource(LaboratoryTestService laboratoryTestService, LaboratoryTestRepository laboratoryTestRepository) {
        this.laboratoryTestService = laboratoryTestService;
        this.laboratoryTestRepository = laboratoryTestRepository;
    }

    /**
     * {@code POST  /laboratory-tests} : Create a new laboratoryTest.
     *
     * @param laboratoryTest the laboratoryTest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new laboratoryTest, or with status {@code 400 (Bad Request)} if the laboratoryTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LaboratoryTest> createLaboratoryTest(@Valid @RequestBody LaboratoryTest laboratoryTest)
        throws URISyntaxException {
        log.debug("REST request to save LaboratoryTest : {}", laboratoryTest);
        if (laboratoryTest.getId() != null) {
            throw new BadRequestAlertException("A new laboratoryTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LaboratoryTest result = laboratoryTestService.save(laboratoryTest);
        return ResponseEntity
            .created(new URI("/api/laboratory-tests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /laboratory-tests/:id} : Updates an existing laboratoryTest.
     *
     * @param id the id of the laboratoryTest to save.
     * @param laboratoryTest the laboratoryTest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryTest,
     * or with status {@code 400 (Bad Request)} if the laboratoryTest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryTest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryTest> updateLaboratoryTest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LaboratoryTest laboratoryTest
    ) throws URISyntaxException {
        log.debug("REST request to update LaboratoryTest : {}, {}", id, laboratoryTest);
        if (laboratoryTest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, laboratoryTest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!laboratoryTestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LaboratoryTest result = laboratoryTestService.update(laboratoryTest);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryTest.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /laboratory-tests/:id} : Partial updates given fields of an existing laboratoryTest, field will ignore if it is null
     *
     * @param id the id of the laboratoryTest to save.
     * @param laboratoryTest the laboratoryTest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryTest,
     * or with status {@code 400 (Bad Request)} if the laboratoryTest is not valid,
     * or with status {@code 404 (Not Found)} if the laboratoryTest is not found,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryTest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LaboratoryTest> partialUpdateLaboratoryTest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LaboratoryTest laboratoryTest
    ) throws URISyntaxException {
        log.debug("REST request to partial update LaboratoryTest partially : {}, {}", id, laboratoryTest);
        if (laboratoryTest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, laboratoryTest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!laboratoryTestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LaboratoryTest> result = laboratoryTestService.partialUpdate(laboratoryTest);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryTest.getId().toString())
        );
    }

    /**
     * {@code GET  /laboratory-tests} : get all the laboratoryTests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of laboratoryTests in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LaboratoryTest>> getAllLaboratoryTests(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of LaboratoryTests");
        Page<LaboratoryTest> page = laboratoryTestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /laboratory-tests/:id} : get the "id" laboratoryTest.
     *
     * @param id the id of the laboratoryTest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the laboratoryTest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryTest> getLaboratoryTest(@PathVariable Long id) {
        log.debug("REST request to get LaboratoryTest : {}", id);
        Optional<LaboratoryTest> laboratoryTest = laboratoryTestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(laboratoryTest);
    }

    /**
     * {@code DELETE  /laboratory-tests/:id} : delete the "id" laboratoryTest.
     *
     * @param id the id of the laboratoryTest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoryTest(@PathVariable Long id) {
        log.debug("REST request to delete LaboratoryTest : {}", id);
        laboratoryTestService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
