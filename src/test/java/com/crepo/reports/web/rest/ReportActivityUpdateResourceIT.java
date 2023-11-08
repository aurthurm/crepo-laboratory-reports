package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.ReportActivityUpdate;
import com.crepo.reports.repository.ReportActivityUpdateRepository;
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
 * Integration tests for the {@link ReportActivityUpdateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportActivityUpdateResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITY_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_OUTCOMES = "AAAAAAAAAA";
    private static final String UPDATED_OUTCOMES = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_REPORTING_PERIOD_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPORTING_PERIOD_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/report-activity-updates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportActivityUpdateRepository reportActivityUpdateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportActivityUpdateMockMvc;

    private ReportActivityUpdate reportActivityUpdate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportActivityUpdate createEntity(EntityManager em) {
        ReportActivityUpdate reportActivityUpdate = new ReportActivityUpdate()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .activity(DEFAULT_ACTIVITY)
            .activityDetails(DEFAULT_ACTIVITY_DETAILS)
            .outcomes(DEFAULT_OUTCOMES)
            .comments(DEFAULT_COMMENTS)
            .reportingPeriodId(DEFAULT_REPORTING_PERIOD_ID);
        return reportActivityUpdate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportActivityUpdate createUpdatedEntity(EntityManager em) {
        ReportActivityUpdate reportActivityUpdate = new ReportActivityUpdate()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .activity(UPDATED_ACTIVITY)
            .activityDetails(UPDATED_ACTIVITY_DETAILS)
            .outcomes(UPDATED_OUTCOMES)
            .comments(UPDATED_COMMENTS)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);
        return reportActivityUpdate;
    }

    @BeforeEach
    public void initTest() {
        reportActivityUpdate = createEntity(em);
    }

    @Test
    @Transactional
    void createReportActivityUpdate() throws Exception {
        int databaseSizeBeforeCreate = reportActivityUpdateRepository.findAll().size();
        // Create the ReportActivityUpdate
        restReportActivityUpdateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isCreated());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeCreate + 1);
        ReportActivityUpdate testReportActivityUpdate = reportActivityUpdateList.get(reportActivityUpdateList.size() - 1);
        assertThat(testReportActivityUpdate.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportActivityUpdate.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportActivityUpdate.getActivity()).isEqualTo(DEFAULT_ACTIVITY);
        assertThat(testReportActivityUpdate.getActivityDetails()).isEqualTo(DEFAULT_ACTIVITY_DETAILS);
        assertThat(testReportActivityUpdate.getOutcomes()).isEqualTo(DEFAULT_OUTCOMES);
        assertThat(testReportActivityUpdate.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testReportActivityUpdate.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void createReportActivityUpdateWithExistingId() throws Exception {
        // Create the ReportActivityUpdate with an existing ID
        reportActivityUpdate.setId(1L);

        int databaseSizeBeforeCreate = reportActivityUpdateRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportActivityUpdateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportActivityUpdateRepository.findAll().size();
        // set the field null
        reportActivityUpdate.setLaboratoryId(null);

        // Create the ReportActivityUpdate, which fails.

        restReportActivityUpdateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReportingPeriodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportActivityUpdateRepository.findAll().size();
        // set the field null
        reportActivityUpdate.setReportingPeriodId(null);

        // Create the ReportActivityUpdate, which fails.

        restReportActivityUpdateMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReportActivityUpdates() throws Exception {
        // Initialize the database
        reportActivityUpdateRepository.saveAndFlush(reportActivityUpdate);

        // Get all the reportActivityUpdateList
        restReportActivityUpdateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportActivityUpdate.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].activity").value(hasItem(DEFAULT_ACTIVITY)))
            .andExpect(jsonPath("$.[*].activityDetails").value(hasItem(DEFAULT_ACTIVITY_DETAILS)))
            .andExpect(jsonPath("$.[*].outcomes").value(hasItem(DEFAULT_OUTCOMES)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].reportingPeriodId").value(hasItem(DEFAULT_REPORTING_PERIOD_ID)));
    }

    @Test
    @Transactional
    void getReportActivityUpdate() throws Exception {
        // Initialize the database
        reportActivityUpdateRepository.saveAndFlush(reportActivityUpdate);

        // Get the reportActivityUpdate
        restReportActivityUpdateMockMvc
            .perform(get(ENTITY_API_URL_ID, reportActivityUpdate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportActivityUpdate.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.activity").value(DEFAULT_ACTIVITY))
            .andExpect(jsonPath("$.activityDetails").value(DEFAULT_ACTIVITY_DETAILS))
            .andExpect(jsonPath("$.outcomes").value(DEFAULT_OUTCOMES))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.reportingPeriodId").value(DEFAULT_REPORTING_PERIOD_ID));
    }

    @Test
    @Transactional
    void getNonExistingReportActivityUpdate() throws Exception {
        // Get the reportActivityUpdate
        restReportActivityUpdateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportActivityUpdate() throws Exception {
        // Initialize the database
        reportActivityUpdateRepository.saveAndFlush(reportActivityUpdate);

        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();

        // Update the reportActivityUpdate
        ReportActivityUpdate updatedReportActivityUpdate = reportActivityUpdateRepository
            .findById(reportActivityUpdate.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedReportActivityUpdate are not directly saved in db
        em.detach(updatedReportActivityUpdate);
        updatedReportActivityUpdate
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .activity(UPDATED_ACTIVITY)
            .activityDetails(UPDATED_ACTIVITY_DETAILS)
            .outcomes(UPDATED_OUTCOMES)
            .comments(UPDATED_COMMENTS)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportActivityUpdateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReportActivityUpdate.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReportActivityUpdate))
            )
            .andExpect(status().isOk());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
        ReportActivityUpdate testReportActivityUpdate = reportActivityUpdateList.get(reportActivityUpdateList.size() - 1);
        assertThat(testReportActivityUpdate.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportActivityUpdate.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportActivityUpdate.getActivity()).isEqualTo(UPDATED_ACTIVITY);
        assertThat(testReportActivityUpdate.getActivityDetails()).isEqualTo(UPDATED_ACTIVITY_DETAILS);
        assertThat(testReportActivityUpdate.getOutcomes()).isEqualTo(UPDATED_OUTCOMES);
        assertThat(testReportActivityUpdate.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testReportActivityUpdate.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void putNonExistingReportActivityUpdate() throws Exception {
        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();
        reportActivityUpdate.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportActivityUpdateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportActivityUpdate.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportActivityUpdate() throws Exception {
        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();
        reportActivityUpdate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportActivityUpdateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportActivityUpdate() throws Exception {
        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();
        reportActivityUpdate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportActivityUpdateMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportActivityUpdateWithPatch() throws Exception {
        // Initialize the database
        reportActivityUpdateRepository.saveAndFlush(reportActivityUpdate);

        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();

        // Update the reportActivityUpdate using partial update
        ReportActivityUpdate partialUpdatedReportActivityUpdate = new ReportActivityUpdate();
        partialUpdatedReportActivityUpdate.setId(reportActivityUpdate.getId());

        partialUpdatedReportActivityUpdate.laboratoryId(UPDATED_LABORATORY_ID).activity(UPDATED_ACTIVITY);

        restReportActivityUpdateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportActivityUpdate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportActivityUpdate))
            )
            .andExpect(status().isOk());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
        ReportActivityUpdate testReportActivityUpdate = reportActivityUpdateList.get(reportActivityUpdateList.size() - 1);
        assertThat(testReportActivityUpdate.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportActivityUpdate.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportActivityUpdate.getActivity()).isEqualTo(UPDATED_ACTIVITY);
        assertThat(testReportActivityUpdate.getActivityDetails()).isEqualTo(DEFAULT_ACTIVITY_DETAILS);
        assertThat(testReportActivityUpdate.getOutcomes()).isEqualTo(DEFAULT_OUTCOMES);
        assertThat(testReportActivityUpdate.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testReportActivityUpdate.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void fullUpdateReportActivityUpdateWithPatch() throws Exception {
        // Initialize the database
        reportActivityUpdateRepository.saveAndFlush(reportActivityUpdate);

        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();

        // Update the reportActivityUpdate using partial update
        ReportActivityUpdate partialUpdatedReportActivityUpdate = new ReportActivityUpdate();
        partialUpdatedReportActivityUpdate.setId(reportActivityUpdate.getId());

        partialUpdatedReportActivityUpdate
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .activity(UPDATED_ACTIVITY)
            .activityDetails(UPDATED_ACTIVITY_DETAILS)
            .outcomes(UPDATED_OUTCOMES)
            .comments(UPDATED_COMMENTS)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportActivityUpdateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportActivityUpdate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportActivityUpdate))
            )
            .andExpect(status().isOk());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
        ReportActivityUpdate testReportActivityUpdate = reportActivityUpdateList.get(reportActivityUpdateList.size() - 1);
        assertThat(testReportActivityUpdate.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportActivityUpdate.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportActivityUpdate.getActivity()).isEqualTo(UPDATED_ACTIVITY);
        assertThat(testReportActivityUpdate.getActivityDetails()).isEqualTo(UPDATED_ACTIVITY_DETAILS);
        assertThat(testReportActivityUpdate.getOutcomes()).isEqualTo(UPDATED_OUTCOMES);
        assertThat(testReportActivityUpdate.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testReportActivityUpdate.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void patchNonExistingReportActivityUpdate() throws Exception {
        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();
        reportActivityUpdate.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportActivityUpdateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportActivityUpdate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportActivityUpdate() throws Exception {
        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();
        reportActivityUpdate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportActivityUpdateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportActivityUpdate() throws Exception {
        int databaseSizeBeforeUpdate = reportActivityUpdateRepository.findAll().size();
        reportActivityUpdate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportActivityUpdateMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportActivityUpdate))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportActivityUpdate in the database
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReportActivityUpdate() throws Exception {
        // Initialize the database
        reportActivityUpdateRepository.saveAndFlush(reportActivityUpdate);

        int databaseSizeBeforeDelete = reportActivityUpdateRepository.findAll().size();

        // Delete the reportActivityUpdate
        restReportActivityUpdateMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportActivityUpdate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportActivityUpdate> reportActivityUpdateList = reportActivityUpdateRepository.findAll();
        assertThat(reportActivityUpdateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
