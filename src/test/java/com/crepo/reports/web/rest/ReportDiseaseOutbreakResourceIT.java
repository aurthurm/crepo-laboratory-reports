package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.ReportDiseaseOutbreak;
import com.crepo.reports.domain.enumeration.Outbreak;
import com.crepo.reports.repository.ReportDiseaseOutbreakRepository;
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
 * Integration tests for the {@link ReportDiseaseOutbreakResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportDiseaseOutbreakResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final Outbreak DEFAULT_OUTBREAK = Outbreak.YES;
    private static final Outbreak UPDATED_OUTBREAK = Outbreak.NO;

    private static final String DEFAULT_DISEASE = "AAAAAAAAAA";
    private static final String UPDATED_DISEASE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REPORTING_PERIOD_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPORTING_PERIOD_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/report-disease-outbreaks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportDiseaseOutbreakRepository reportDiseaseOutbreakRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportDiseaseOutbreakMockMvc;

    private ReportDiseaseOutbreak reportDiseaseOutbreak;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportDiseaseOutbreak createEntity(EntityManager em) {
        ReportDiseaseOutbreak reportDiseaseOutbreak = new ReportDiseaseOutbreak()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .outbreak(DEFAULT_OUTBREAK)
            .disease(DEFAULT_DISEASE)
            .comment(DEFAULT_COMMENT)
            .reportingPeriodId(DEFAULT_REPORTING_PERIOD_ID);
        return reportDiseaseOutbreak;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportDiseaseOutbreak createUpdatedEntity(EntityManager em) {
        ReportDiseaseOutbreak reportDiseaseOutbreak = new ReportDiseaseOutbreak()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .outbreak(UPDATED_OUTBREAK)
            .disease(UPDATED_DISEASE)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);
        return reportDiseaseOutbreak;
    }

    @BeforeEach
    public void initTest() {
        reportDiseaseOutbreak = createEntity(em);
    }

    @Test
    @Transactional
    void createReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeCreate = reportDiseaseOutbreakRepository.findAll().size();
        // Create the ReportDiseaseOutbreak
        restReportDiseaseOutbreakMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isCreated());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeCreate + 1);
        ReportDiseaseOutbreak testReportDiseaseOutbreak = reportDiseaseOutbreakList.get(reportDiseaseOutbreakList.size() - 1);
        assertThat(testReportDiseaseOutbreak.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportDiseaseOutbreak.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportDiseaseOutbreak.getOutbreak()).isEqualTo(DEFAULT_OUTBREAK);
        assertThat(testReportDiseaseOutbreak.getDisease()).isEqualTo(DEFAULT_DISEASE);
        assertThat(testReportDiseaseOutbreak.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testReportDiseaseOutbreak.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void createReportDiseaseOutbreakWithExistingId() throws Exception {
        // Create the ReportDiseaseOutbreak with an existing ID
        reportDiseaseOutbreak.setId(1L);

        int databaseSizeBeforeCreate = reportDiseaseOutbreakRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportDiseaseOutbreakMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportDiseaseOutbreakRepository.findAll().size();
        // set the field null
        reportDiseaseOutbreak.setLaboratoryId(null);

        // Create the ReportDiseaseOutbreak, which fails.

        restReportDiseaseOutbreakMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReportingPeriodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportDiseaseOutbreakRepository.findAll().size();
        // set the field null
        reportDiseaseOutbreak.setReportingPeriodId(null);

        // Create the ReportDiseaseOutbreak, which fails.

        restReportDiseaseOutbreakMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReportDiseaseOutbreaks() throws Exception {
        // Initialize the database
        reportDiseaseOutbreakRepository.saveAndFlush(reportDiseaseOutbreak);

        // Get all the reportDiseaseOutbreakList
        restReportDiseaseOutbreakMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportDiseaseOutbreak.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].outbreak").value(hasItem(DEFAULT_OUTBREAK.toString())))
            .andExpect(jsonPath("$.[*].disease").value(hasItem(DEFAULT_DISEASE)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].reportingPeriodId").value(hasItem(DEFAULT_REPORTING_PERIOD_ID)));
    }

    @Test
    @Transactional
    void getReportDiseaseOutbreak() throws Exception {
        // Initialize the database
        reportDiseaseOutbreakRepository.saveAndFlush(reportDiseaseOutbreak);

        // Get the reportDiseaseOutbreak
        restReportDiseaseOutbreakMockMvc
            .perform(get(ENTITY_API_URL_ID, reportDiseaseOutbreak.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportDiseaseOutbreak.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.outbreak").value(DEFAULT_OUTBREAK.toString()))
            .andExpect(jsonPath("$.disease").value(DEFAULT_DISEASE))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.reportingPeriodId").value(DEFAULT_REPORTING_PERIOD_ID));
    }

    @Test
    @Transactional
    void getNonExistingReportDiseaseOutbreak() throws Exception {
        // Get the reportDiseaseOutbreak
        restReportDiseaseOutbreakMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportDiseaseOutbreak() throws Exception {
        // Initialize the database
        reportDiseaseOutbreakRepository.saveAndFlush(reportDiseaseOutbreak);

        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();

        // Update the reportDiseaseOutbreak
        ReportDiseaseOutbreak updatedReportDiseaseOutbreak = reportDiseaseOutbreakRepository
            .findById(reportDiseaseOutbreak.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedReportDiseaseOutbreak are not directly saved in db
        em.detach(updatedReportDiseaseOutbreak);
        updatedReportDiseaseOutbreak
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .outbreak(UPDATED_OUTBREAK)
            .disease(UPDATED_DISEASE)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportDiseaseOutbreakMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReportDiseaseOutbreak.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReportDiseaseOutbreak))
            )
            .andExpect(status().isOk());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
        ReportDiseaseOutbreak testReportDiseaseOutbreak = reportDiseaseOutbreakList.get(reportDiseaseOutbreakList.size() - 1);
        assertThat(testReportDiseaseOutbreak.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportDiseaseOutbreak.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportDiseaseOutbreak.getOutbreak()).isEqualTo(UPDATED_OUTBREAK);
        assertThat(testReportDiseaseOutbreak.getDisease()).isEqualTo(UPDATED_DISEASE);
        assertThat(testReportDiseaseOutbreak.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportDiseaseOutbreak.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void putNonExistingReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();
        reportDiseaseOutbreak.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportDiseaseOutbreakMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportDiseaseOutbreak.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();
        reportDiseaseOutbreak.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportDiseaseOutbreakMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();
        reportDiseaseOutbreak.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportDiseaseOutbreakMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportDiseaseOutbreakWithPatch() throws Exception {
        // Initialize the database
        reportDiseaseOutbreakRepository.saveAndFlush(reportDiseaseOutbreak);

        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();

        // Update the reportDiseaseOutbreak using partial update
        ReportDiseaseOutbreak partialUpdatedReportDiseaseOutbreak = new ReportDiseaseOutbreak();
        partialUpdatedReportDiseaseOutbreak.setId(reportDiseaseOutbreak.getId());

        partialUpdatedReportDiseaseOutbreak
            .laboratoryId(UPDATED_LABORATORY_ID)
            .outbreak(UPDATED_OUTBREAK)
            .disease(UPDATED_DISEASE)
            .comment(UPDATED_COMMENT);

        restReportDiseaseOutbreakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportDiseaseOutbreak.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportDiseaseOutbreak))
            )
            .andExpect(status().isOk());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
        ReportDiseaseOutbreak testReportDiseaseOutbreak = reportDiseaseOutbreakList.get(reportDiseaseOutbreakList.size() - 1);
        assertThat(testReportDiseaseOutbreak.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportDiseaseOutbreak.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportDiseaseOutbreak.getOutbreak()).isEqualTo(UPDATED_OUTBREAK);
        assertThat(testReportDiseaseOutbreak.getDisease()).isEqualTo(UPDATED_DISEASE);
        assertThat(testReportDiseaseOutbreak.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportDiseaseOutbreak.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void fullUpdateReportDiseaseOutbreakWithPatch() throws Exception {
        // Initialize the database
        reportDiseaseOutbreakRepository.saveAndFlush(reportDiseaseOutbreak);

        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();

        // Update the reportDiseaseOutbreak using partial update
        ReportDiseaseOutbreak partialUpdatedReportDiseaseOutbreak = new ReportDiseaseOutbreak();
        partialUpdatedReportDiseaseOutbreak.setId(reportDiseaseOutbreak.getId());

        partialUpdatedReportDiseaseOutbreak
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .outbreak(UPDATED_OUTBREAK)
            .disease(UPDATED_DISEASE)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportDiseaseOutbreakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportDiseaseOutbreak.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportDiseaseOutbreak))
            )
            .andExpect(status().isOk());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
        ReportDiseaseOutbreak testReportDiseaseOutbreak = reportDiseaseOutbreakList.get(reportDiseaseOutbreakList.size() - 1);
        assertThat(testReportDiseaseOutbreak.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportDiseaseOutbreak.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportDiseaseOutbreak.getOutbreak()).isEqualTo(UPDATED_OUTBREAK);
        assertThat(testReportDiseaseOutbreak.getDisease()).isEqualTo(UPDATED_DISEASE);
        assertThat(testReportDiseaseOutbreak.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportDiseaseOutbreak.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void patchNonExistingReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();
        reportDiseaseOutbreak.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportDiseaseOutbreakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportDiseaseOutbreak.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();
        reportDiseaseOutbreak.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportDiseaseOutbreakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportDiseaseOutbreak() throws Exception {
        int databaseSizeBeforeUpdate = reportDiseaseOutbreakRepository.findAll().size();
        reportDiseaseOutbreak.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportDiseaseOutbreakMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportDiseaseOutbreak))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportDiseaseOutbreak in the database
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReportDiseaseOutbreak() throws Exception {
        // Initialize the database
        reportDiseaseOutbreakRepository.saveAndFlush(reportDiseaseOutbreak);

        int databaseSizeBeforeDelete = reportDiseaseOutbreakRepository.findAll().size();

        // Delete the reportDiseaseOutbreak
        restReportDiseaseOutbreakMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportDiseaseOutbreak.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportDiseaseOutbreak> reportDiseaseOutbreakList = reportDiseaseOutbreakRepository.findAll();
        assertThat(reportDiseaseOutbreakList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
