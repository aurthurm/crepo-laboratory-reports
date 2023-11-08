package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.ReportingPeriod;
import com.crepo.reports.repository.ReportingPeriodRepository;
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
 * Integration tests for the {@link ReportingPeriodResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportingPeriodResourceIT {

    private static final Integer DEFAULT_DAY = 1;
    private static final Integer UPDATED_DAY = 2;

    private static final Integer DEFAULT_WEEK = 1;
    private static final Integer UPDATED_WEEK = 2;

    private static final String DEFAULT_MONTH = "AAAAAAAAAA";
    private static final String UPDATED_MONTH = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final String ENTITY_API_URL = "/api/reporting-periods";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportingPeriodRepository reportingPeriodRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportingPeriodMockMvc;

    private ReportingPeriod reportingPeriod;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportingPeriod createEntity(EntityManager em) {
        ReportingPeriod reportingPeriod = new ReportingPeriod().day(DEFAULT_DAY).week(DEFAULT_WEEK).month(DEFAULT_MONTH).year(DEFAULT_YEAR);
        return reportingPeriod;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportingPeriod createUpdatedEntity(EntityManager em) {
        ReportingPeriod reportingPeriod = new ReportingPeriod().day(UPDATED_DAY).week(UPDATED_WEEK).month(UPDATED_MONTH).year(UPDATED_YEAR);
        return reportingPeriod;
    }

    @BeforeEach
    public void initTest() {
        reportingPeriod = createEntity(em);
    }

    @Test
    @Transactional
    void createReportingPeriod() throws Exception {
        int databaseSizeBeforeCreate = reportingPeriodRepository.findAll().size();
        // Create the ReportingPeriod
        restReportingPeriodMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isCreated());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeCreate + 1);
        ReportingPeriod testReportingPeriod = reportingPeriodList.get(reportingPeriodList.size() - 1);
        assertThat(testReportingPeriod.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testReportingPeriod.getWeek()).isEqualTo(DEFAULT_WEEK);
        assertThat(testReportingPeriod.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testReportingPeriod.getYear()).isEqualTo(DEFAULT_YEAR);
    }

    @Test
    @Transactional
    void createReportingPeriodWithExistingId() throws Exception {
        // Create the ReportingPeriod with an existing ID
        reportingPeriod.setId(1L);

        int databaseSizeBeforeCreate = reportingPeriodRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportingPeriodMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportingPeriodRepository.findAll().size();
        // set the field null
        reportingPeriod.setMonth(null);

        // Create the ReportingPeriod, which fails.

        restReportingPeriodMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportingPeriodRepository.findAll().size();
        // set the field null
        reportingPeriod.setYear(null);

        // Create the ReportingPeriod, which fails.

        restReportingPeriodMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReportingPeriods() throws Exception {
        // Initialize the database
        reportingPeriodRepository.saveAndFlush(reportingPeriod);

        // Get all the reportingPeriodList
        restReportingPeriodMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportingPeriod.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].week").value(hasItem(DEFAULT_WEEK)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)));
    }

    @Test
    @Transactional
    void getReportingPeriod() throws Exception {
        // Initialize the database
        reportingPeriodRepository.saveAndFlush(reportingPeriod);

        // Get the reportingPeriod
        restReportingPeriodMockMvc
            .perform(get(ENTITY_API_URL_ID, reportingPeriod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportingPeriod.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY))
            .andExpect(jsonPath("$.week").value(DEFAULT_WEEK))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR));
    }

    @Test
    @Transactional
    void getNonExistingReportingPeriod() throws Exception {
        // Get the reportingPeriod
        restReportingPeriodMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportingPeriod() throws Exception {
        // Initialize the database
        reportingPeriodRepository.saveAndFlush(reportingPeriod);

        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();

        // Update the reportingPeriod
        ReportingPeriod updatedReportingPeriod = reportingPeriodRepository.findById(reportingPeriod.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReportingPeriod are not directly saved in db
        em.detach(updatedReportingPeriod);
        updatedReportingPeriod.day(UPDATED_DAY).week(UPDATED_WEEK).month(UPDATED_MONTH).year(UPDATED_YEAR);

        restReportingPeriodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReportingPeriod.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReportingPeriod))
            )
            .andExpect(status().isOk());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
        ReportingPeriod testReportingPeriod = reportingPeriodList.get(reportingPeriodList.size() - 1);
        assertThat(testReportingPeriod.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testReportingPeriod.getWeek()).isEqualTo(UPDATED_WEEK);
        assertThat(testReportingPeriod.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testReportingPeriod.getYear()).isEqualTo(UPDATED_YEAR);
    }

    @Test
    @Transactional
    void putNonExistingReportingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();
        reportingPeriod.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportingPeriodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportingPeriod.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();
        reportingPeriod.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportingPeriodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();
        reportingPeriod.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportingPeriodMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportingPeriodWithPatch() throws Exception {
        // Initialize the database
        reportingPeriodRepository.saveAndFlush(reportingPeriod);

        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();

        // Update the reportingPeriod using partial update
        ReportingPeriod partialUpdatedReportingPeriod = new ReportingPeriod();
        partialUpdatedReportingPeriod.setId(reportingPeriod.getId());

        partialUpdatedReportingPeriod.day(UPDATED_DAY).week(UPDATED_WEEK).year(UPDATED_YEAR);

        restReportingPeriodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportingPeriod.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportingPeriod))
            )
            .andExpect(status().isOk());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
        ReportingPeriod testReportingPeriod = reportingPeriodList.get(reportingPeriodList.size() - 1);
        assertThat(testReportingPeriod.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testReportingPeriod.getWeek()).isEqualTo(UPDATED_WEEK);
        assertThat(testReportingPeriod.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testReportingPeriod.getYear()).isEqualTo(UPDATED_YEAR);
    }

    @Test
    @Transactional
    void fullUpdateReportingPeriodWithPatch() throws Exception {
        // Initialize the database
        reportingPeriodRepository.saveAndFlush(reportingPeriod);

        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();

        // Update the reportingPeriod using partial update
        ReportingPeriod partialUpdatedReportingPeriod = new ReportingPeriod();
        partialUpdatedReportingPeriod.setId(reportingPeriod.getId());

        partialUpdatedReportingPeriod.day(UPDATED_DAY).week(UPDATED_WEEK).month(UPDATED_MONTH).year(UPDATED_YEAR);

        restReportingPeriodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportingPeriod.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportingPeriod))
            )
            .andExpect(status().isOk());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
        ReportingPeriod testReportingPeriod = reportingPeriodList.get(reportingPeriodList.size() - 1);
        assertThat(testReportingPeriod.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testReportingPeriod.getWeek()).isEqualTo(UPDATED_WEEK);
        assertThat(testReportingPeriod.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testReportingPeriod.getYear()).isEqualTo(UPDATED_YEAR);
    }

    @Test
    @Transactional
    void patchNonExistingReportingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();
        reportingPeriod.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportingPeriodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportingPeriod.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();
        reportingPeriod.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportingPeriodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportingPeriod() throws Exception {
        int databaseSizeBeforeUpdate = reportingPeriodRepository.findAll().size();
        reportingPeriod.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportingPeriodMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportingPeriod))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportingPeriod in the database
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReportingPeriod() throws Exception {
        // Initialize the database
        reportingPeriodRepository.saveAndFlush(reportingPeriod);

        int databaseSizeBeforeDelete = reportingPeriodRepository.findAll().size();

        // Delete the reportingPeriod
        restReportingPeriodMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportingPeriod.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportingPeriod> reportingPeriodList = reportingPeriodRepository.findAll();
        assertThat(reportingPeriodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
