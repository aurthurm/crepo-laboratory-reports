package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.LaboratoryInstrument;
import com.crepo.reports.repository.LaboratoryInstrumentRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LaboratoryInstrumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LaboratoryInstrumentResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/laboratory-instruments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LaboratoryInstrumentRepository laboratoryInstrumentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLaboratoryInstrumentMockMvc;

    private LaboratoryInstrument laboratoryInstrument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryInstrument createEntity(EntityManager em) {
        LaboratoryInstrument laboratoryInstrument = new LaboratoryInstrument()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .instrumentId(DEFAULT_INSTRUMENT_ID)
            .instrument(DEFAULT_INSTRUMENT)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT);
        return laboratoryInstrument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryInstrument createUpdatedEntity(EntityManager em) {
        LaboratoryInstrument laboratoryInstrument = new LaboratoryInstrument()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);
        return laboratoryInstrument;
    }

    @BeforeEach
    public void initTest() {
        laboratoryInstrument = createEntity(em);
    }

    @Test
    @Transactional
    void createLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeCreate = laboratoryInstrumentRepository.findAll().size();
        // Create the LaboratoryInstrument
        restLaboratoryInstrumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isCreated());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeCreate + 1);
        LaboratoryInstrument testLaboratoryInstrument = laboratoryInstrumentList.get(laboratoryInstrumentList.size() - 1);
        assertThat(testLaboratoryInstrument.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testLaboratoryInstrument.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testLaboratoryInstrument.getInstrumentId()).isEqualTo(DEFAULT_INSTRUMENT_ID);
        assertThat(testLaboratoryInstrument.getInstrument()).isEqualTo(DEFAULT_INSTRUMENT);
        assertThat(testLaboratoryInstrument.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testLaboratoryInstrument.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
    }

    @Test
    @Transactional
    void createLaboratoryInstrumentWithExistingId() throws Exception {
        // Create the LaboratoryInstrument with an existing ID
        laboratoryInstrument.setId(1L);

        int databaseSizeBeforeCreate = laboratoryInstrumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaboratoryInstrumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryInstrumentRepository.findAll().size();
        // set the field null
        laboratoryInstrument.setLaboratoryId(null);

        // Create the LaboratoryInstrument, which fails.

        restLaboratoryInstrumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInstrumentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryInstrumentRepository.findAll().size();
        // set the field null
        laboratoryInstrument.setInstrumentId(null);

        // Create the LaboratoryInstrument, which fails.

        restLaboratoryInstrumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLaboratoryInstruments() throws Exception {
        // Initialize the database
        laboratoryInstrumentRepository.saveAndFlush(laboratoryInstrument);

        // Get all the laboratoryInstrumentList
        restLaboratoryInstrumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laboratoryInstrument.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].instrumentId").value(hasItem(DEFAULT_INSTRUMENT_ID)))
            .andExpect(jsonPath("$.[*].instrument").value(hasItem(DEFAULT_INSTRUMENT)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)));
    }

    @Test
    @Transactional
    void getLaboratoryInstrument() throws Exception {
        // Initialize the database
        laboratoryInstrumentRepository.saveAndFlush(laboratoryInstrument);

        // Get the laboratoryInstrument
        restLaboratoryInstrumentMockMvc
            .perform(get(ENTITY_API_URL_ID, laboratoryInstrument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(laboratoryInstrument.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.instrumentId").value(DEFAULT_INSTRUMENT_ID))
            .andExpect(jsonPath("$.instrument").value(DEFAULT_INSTRUMENT))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT));
    }

    @Test
    @Transactional
    void getNonExistingLaboratoryInstrument() throws Exception {
        // Get the laboratoryInstrument
        restLaboratoryInstrumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLaboratoryInstrument() throws Exception {
        // Initialize the database
        laboratoryInstrumentRepository.saveAndFlush(laboratoryInstrument);

        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();

        // Update the laboratoryInstrument
        LaboratoryInstrument updatedLaboratoryInstrument = laboratoryInstrumentRepository
            .findById(laboratoryInstrument.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedLaboratoryInstrument are not directly saved in db
        em.detach(updatedLaboratoryInstrument);
        updatedLaboratoryInstrument
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryInstrumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLaboratoryInstrument.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLaboratoryInstrument))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryInstrument testLaboratoryInstrument = laboratoryInstrumentList.get(laboratoryInstrumentList.size() - 1);
        assertThat(testLaboratoryInstrument.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryInstrument.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryInstrument.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testLaboratoryInstrument.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testLaboratoryInstrument.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryInstrument.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void putNonExistingLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();
        laboratoryInstrument.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryInstrumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, laboratoryInstrument.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();
        laboratoryInstrument.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryInstrumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();
        laboratoryInstrument.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryInstrumentMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLaboratoryInstrumentWithPatch() throws Exception {
        // Initialize the database
        laboratoryInstrumentRepository.saveAndFlush(laboratoryInstrument);

        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();

        // Update the laboratoryInstrument using partial update
        LaboratoryInstrument partialUpdatedLaboratoryInstrument = new LaboratoryInstrument();
        partialUpdatedLaboratoryInstrument.setId(laboratoryInstrument.getId());

        partialUpdatedLaboratoryInstrument
            .laboratoryId(UPDATED_LABORATORY_ID)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .departmentId(UPDATED_DEPARTMENT_ID);

        restLaboratoryInstrumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoryInstrument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLaboratoryInstrument))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryInstrument testLaboratoryInstrument = laboratoryInstrumentList.get(laboratoryInstrumentList.size() - 1);
        assertThat(testLaboratoryInstrument.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryInstrument.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testLaboratoryInstrument.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testLaboratoryInstrument.getInstrument()).isEqualTo(DEFAULT_INSTRUMENT);
        assertThat(testLaboratoryInstrument.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryInstrument.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
    }

    @Test
    @Transactional
    void fullUpdateLaboratoryInstrumentWithPatch() throws Exception {
        // Initialize the database
        laboratoryInstrumentRepository.saveAndFlush(laboratoryInstrument);

        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();

        // Update the laboratoryInstrument using partial update
        LaboratoryInstrument partialUpdatedLaboratoryInstrument = new LaboratoryInstrument();
        partialUpdatedLaboratoryInstrument.setId(laboratoryInstrument.getId());

        partialUpdatedLaboratoryInstrument
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryInstrumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoryInstrument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLaboratoryInstrument))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryInstrument testLaboratoryInstrument = laboratoryInstrumentList.get(laboratoryInstrumentList.size() - 1);
        assertThat(testLaboratoryInstrument.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryInstrument.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryInstrument.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testLaboratoryInstrument.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testLaboratoryInstrument.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryInstrument.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void patchNonExistingLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();
        laboratoryInstrument.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryInstrumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, laboratoryInstrument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();
        laboratoryInstrument.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryInstrumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLaboratoryInstrument() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryInstrumentRepository.findAll().size();
        laboratoryInstrument.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryInstrumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryInstrument))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LaboratoryInstrument in the database
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLaboratoryInstrument() throws Exception {
        // Initialize the database
        laboratoryInstrumentRepository.saveAndFlush(laboratoryInstrument);

        int databaseSizeBeforeDelete = laboratoryInstrumentRepository.findAll().size();

        // Delete the laboratoryInstrument
        restLaboratoryInstrumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, laboratoryInstrument.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LaboratoryInstrument> laboratoryInstrumentList = laboratoryInstrumentRepository.findAll();
        assertThat(laboratoryInstrumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
