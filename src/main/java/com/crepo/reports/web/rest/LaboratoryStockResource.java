package com.crepo.reports.web.rest;

import com.crepo.reports.domain.LaboratoryStock;
import com.crepo.reports.repository.LaboratoryStockRepository;
import com.crepo.reports.service.LaboratoryStockService;
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
 * REST controller for managing {@link com.crepo.reports.domain.LaboratoryStock}.
 */
@RestController
@RequestMapping("/api/laboratory-stocks")
public class LaboratoryStockResource {

    private final Logger log = LoggerFactory.getLogger(LaboratoryStockResource.class);

    private static final String ENTITY_NAME = "laboratoryStock";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LaboratoryStockService laboratoryStockService;

    private final LaboratoryStockRepository laboratoryStockRepository;

    public LaboratoryStockResource(LaboratoryStockService laboratoryStockService, LaboratoryStockRepository laboratoryStockRepository) {
        this.laboratoryStockService = laboratoryStockService;
        this.laboratoryStockRepository = laboratoryStockRepository;
    }

    /**
     * {@code POST  /laboratory-stocks} : Create a new laboratoryStock.
     *
     * @param laboratoryStock the laboratoryStock to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new laboratoryStock, or with status {@code 400 (Bad Request)} if the laboratoryStock has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LaboratoryStock> createLaboratoryStock(@Valid @RequestBody LaboratoryStock laboratoryStock)
        throws URISyntaxException {
        log.debug("REST request to save LaboratoryStock : {}", laboratoryStock);
        if (laboratoryStock.getId() != null) {
            throw new BadRequestAlertException("A new laboratoryStock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LaboratoryStock result = laboratoryStockService.save(laboratoryStock);
        return ResponseEntity
            .created(new URI("/api/laboratory-stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /laboratory-stocks/:id} : Updates an existing laboratoryStock.
     *
     * @param id the id of the laboratoryStock to save.
     * @param laboratoryStock the laboratoryStock to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryStock,
     * or with status {@code 400 (Bad Request)} if the laboratoryStock is not valid,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryStock couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryStock> updateLaboratoryStock(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LaboratoryStock laboratoryStock
    ) throws URISyntaxException {
        log.debug("REST request to update LaboratoryStock : {}, {}", id, laboratoryStock);
        if (laboratoryStock.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, laboratoryStock.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!laboratoryStockRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LaboratoryStock result = laboratoryStockService.update(laboratoryStock);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryStock.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /laboratory-stocks/:id} : Partial updates given fields of an existing laboratoryStock, field will ignore if it is null
     *
     * @param id the id of the laboratoryStock to save.
     * @param laboratoryStock the laboratoryStock to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated laboratoryStock,
     * or with status {@code 400 (Bad Request)} if the laboratoryStock is not valid,
     * or with status {@code 404 (Not Found)} if the laboratoryStock is not found,
     * or with status {@code 500 (Internal Server Error)} if the laboratoryStock couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LaboratoryStock> partialUpdateLaboratoryStock(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody LaboratoryStock laboratoryStock
    ) throws URISyntaxException {
        log.debug("REST request to partial update LaboratoryStock partially : {}, {}", id, laboratoryStock);
        if (laboratoryStock.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, laboratoryStock.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!laboratoryStockRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LaboratoryStock> result = laboratoryStockService.partialUpdate(laboratoryStock);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, laboratoryStock.getId().toString())
        );
    }

    /**
     * {@code GET  /laboratory-stocks} : get all the laboratoryStocks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of laboratoryStocks in body.
     */
    @GetMapping("")
    public ResponseEntity<List<LaboratoryStock>> getAllLaboratoryStocks(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of LaboratoryStocks");
        Page<LaboratoryStock> page = laboratoryStockService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /laboratory-stocks/:id} : get the "id" laboratoryStock.
     *
     * @param id the id of the laboratoryStock to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the laboratoryStock, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryStock> getLaboratoryStock(@PathVariable Long id) {
        log.debug("REST request to get LaboratoryStock : {}", id);
        Optional<LaboratoryStock> laboratoryStock = laboratoryStockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(laboratoryStock);
    }

    /**
     * {@code DELETE  /laboratory-stocks/:id} : delete the "id" laboratoryStock.
     *
     * @param id the id of the laboratoryStock to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoryStock(@PathVariable Long id) {
        log.debug("REST request to delete LaboratoryStock : {}", id);
        laboratoryStockService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
