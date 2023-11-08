package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.ReportInstrumentPerformance;
import com.crepo.reports.domain.enumeration.InstrumentFunctionality;
import com.crepo.reports.repository.ReportInstrumentPerformanceRepository;
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
 * Integration tests for the {@link ReportInstrumentPerformanceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportInstrumentPerformanceResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_UPTIME = "AAAAAAAAAA";
    private static final String UPDATED_UPTIME = "BBBBBBBBBB";

    private static final String DEFAULT_DOWNTIME = "AAAAAAAAAA";
    private static final String UPDATED_DOWNTIME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CALIBERATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CALIBERATION_STATUS = "BBBBBBBBBB";

    private static final InstrumentFunctionality DEFAULT_FUNCTIONALITY = InstrumentFunctionality.YES;
    private static final InstrumentFunctionality UPDATED_FUNCTIONALITY = InstrumentFunctionality.NO;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REPORTING_PERIOD_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPORTING_PERIOD_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/report-instrument-performances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportInstrumentPerformanceRepository reportInstrumentPerformanceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportInstrumentPerformanceMockMvc;

    private ReportInstrumentPerformance reportInstrumentPerformance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportInstrumentPerformance createEntity(EntityManager em) {
        ReportInstrumentPerformance reportInstrumentPerformance = new ReportInstrumentPerformance()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT)
            .instrumentId(DEFAULT_INSTRUMENT_ID)
            .instrument(DEFAULT_INSTRUMENT)
            .status(DEFAULT_STATUS)
            .uptime(DEFAULT_UPTIME)
            .downtime(DEFAULT_DOWNTIME)
            .serviceStatus(DEFAULT_SERVICE_STATUS)
            .caliberationStatus(DEFAULT_CALIBERATION_STATUS)
            .functionality(DEFAULT_FUNCTIONALITY)
            .comment(DEFAULT_COMMENT)
            .reportingPeriodId(DEFAULT_REPORTING_PERIOD_ID);
        return reportInstrumentPerformance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportInstrumentPerformance createUpdatedEntity(EntityManager em) {
        ReportInstrumentPerformance reportInstrumentPerformance = new ReportInstrumentPerformance()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .status(UPDATED_STATUS)
            .uptime(UPDATED_UPTIME)
            .downtime(UPDATED_DOWNTIME)
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .caliberationStatus(UPDATED_CALIBERATION_STATUS)
            .functionality(UPDATED_FUNCTIONALITY)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);
        return reportInstrumentPerformance;
    }

    @BeforeEach
    public void initTest() {
        reportInstrumentPerformance = createEntity(em);
    }

    @Test
    @Transactional
    void createReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeCreate = reportInstrumentPerformanceRepository.findAll().size();
        // Create the ReportInstrumentPerformance
        restReportInstrumentPerformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isCreated());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeCreate + 1);
        ReportInstrumentPerformance testReportInstrumentPerformance = reportInstrumentPerformanceList.get(
            reportInstrumentPerformanceList.size() - 1
        );
        assertThat(testReportInstrumentPerformance.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportInstrumentPerformance.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportInstrumentPerformance.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testReportInstrumentPerformance.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testReportInstrumentPerformance.getInstrumentId()).isEqualTo(DEFAULT_INSTRUMENT_ID);
        assertThat(testReportInstrumentPerformance.getInstrument()).isEqualTo(DEFAULT_INSTRUMENT);
        assertThat(testReportInstrumentPerformance.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testReportInstrumentPerformance.getUptime()).isEqualTo(DEFAULT_UPTIME);
        assertThat(testReportInstrumentPerformance.getDowntime()).isEqualTo(DEFAULT_DOWNTIME);
        assertThat(testReportInstrumentPerformance.getServiceStatus()).isEqualTo(DEFAULT_SERVICE_STATUS);
        assertThat(testReportInstrumentPerformance.getCaliberationStatus()).isEqualTo(DEFAULT_CALIBERATION_STATUS);
        assertThat(testReportInstrumentPerformance.getFunctionality()).isEqualTo(DEFAULT_FUNCTIONALITY);
        assertThat(testReportInstrumentPerformance.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testReportInstrumentPerformance.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void createReportInstrumentPerformanceWithExistingId() throws Exception {
        // Create the ReportInstrumentPerformance with an existing ID
        reportInstrumentPerformance.setId(1L);

        int databaseSizeBeforeCreate = reportInstrumentPerformanceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportInstrumentPerformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportInstrumentPerformanceRepository.findAll().size();
        // set the field null
        reportInstrumentPerformance.setLaboratoryId(null);

        // Create the ReportInstrumentPerformance, which fails.

        restReportInstrumentPerformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInstrumentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportInstrumentPerformanceRepository.findAll().size();
        // set the field null
        reportInstrumentPerformance.setInstrumentId(null);

        // Create the ReportInstrumentPerformance, which fails.

        restReportInstrumentPerformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReportingPeriodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportInstrumentPerformanceRepository.findAll().size();
        // set the field null
        reportInstrumentPerformance.setReportingPeriodId(null);

        // Create the ReportInstrumentPerformance, which fails.

        restReportInstrumentPerformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReportInstrumentPerformances() throws Exception {
        // Initialize the database
        reportInstrumentPerformanceRepository.saveAndFlush(reportInstrumentPerformance);

        // Get all the reportInstrumentPerformanceList
        restReportInstrumentPerformanceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportInstrumentPerformance.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].instrumentId").value(hasItem(DEFAULT_INSTRUMENT_ID)))
            .andExpect(jsonPath("$.[*].instrument").value(hasItem(DEFAULT_INSTRUMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].uptime").value(hasItem(DEFAULT_UPTIME)))
            .andExpect(jsonPath("$.[*].downtime").value(hasItem(DEFAULT_DOWNTIME)))
            .andExpect(jsonPath("$.[*].serviceStatus").value(hasItem(DEFAULT_SERVICE_STATUS)))
            .andExpect(jsonPath("$.[*].caliberationStatus").value(hasItem(DEFAULT_CALIBERATION_STATUS)))
            .andExpect(jsonPath("$.[*].functionality").value(hasItem(DEFAULT_FUNCTIONALITY.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].reportingPeriodId").value(hasItem(DEFAULT_REPORTING_PERIOD_ID)));
    }

    @Test
    @Transactional
    void getReportInstrumentPerformance() throws Exception {
        // Initialize the database
        reportInstrumentPerformanceRepository.saveAndFlush(reportInstrumentPerformance);

        // Get the reportInstrumentPerformance
        restReportInstrumentPerformanceMockMvc
            .perform(get(ENTITY_API_URL_ID, reportInstrumentPerformance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportInstrumentPerformance.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.instrumentId").value(DEFAULT_INSTRUMENT_ID))
            .andExpect(jsonPath("$.instrument").value(DEFAULT_INSTRUMENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.uptime").value(DEFAULT_UPTIME))
            .andExpect(jsonPath("$.downtime").value(DEFAULT_DOWNTIME))
            .andExpect(jsonPath("$.serviceStatus").value(DEFAULT_SERVICE_STATUS))
            .andExpect(jsonPath("$.caliberationStatus").value(DEFAULT_CALIBERATION_STATUS))
            .andExpect(jsonPath("$.functionality").value(DEFAULT_FUNCTIONALITY.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.reportingPeriodId").value(DEFAULT_REPORTING_PERIOD_ID));
    }

    @Test
    @Transactional
    void getNonExistingReportInstrumentPerformance() throws Exception {
        // Get the reportInstrumentPerformance
        restReportInstrumentPerformanceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportInstrumentPerformance() throws Exception {
        // Initialize the database
        reportInstrumentPerformanceRepository.saveAndFlush(reportInstrumentPerformance);

        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();

        // Update the reportInstrumentPerformance
        ReportInstrumentPerformance updatedReportInstrumentPerformance = reportInstrumentPerformanceRepository
            .findById(reportInstrumentPerformance.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedReportInstrumentPerformance are not directly saved in db
        em.detach(updatedReportInstrumentPerformance);
        updatedReportInstrumentPerformance
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .status(UPDATED_STATUS)
            .uptime(UPDATED_UPTIME)
            .downtime(UPDATED_DOWNTIME)
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .caliberationStatus(UPDATED_CALIBERATION_STATUS)
            .functionality(UPDATED_FUNCTIONALITY)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportInstrumentPerformanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReportInstrumentPerformance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReportInstrumentPerformance))
            )
            .andExpect(status().isOk());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
        ReportInstrumentPerformance testReportInstrumentPerformance = reportInstrumentPerformanceList.get(
            reportInstrumentPerformanceList.size() - 1
        );
        assertThat(testReportInstrumentPerformance.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportInstrumentPerformance.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportInstrumentPerformance.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportInstrumentPerformance.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportInstrumentPerformance.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testReportInstrumentPerformance.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testReportInstrumentPerformance.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReportInstrumentPerformance.getUptime()).isEqualTo(UPDATED_UPTIME);
        assertThat(testReportInstrumentPerformance.getDowntime()).isEqualTo(UPDATED_DOWNTIME);
        assertThat(testReportInstrumentPerformance.getServiceStatus()).isEqualTo(UPDATED_SERVICE_STATUS);
        assertThat(testReportInstrumentPerformance.getCaliberationStatus()).isEqualTo(UPDATED_CALIBERATION_STATUS);
        assertThat(testReportInstrumentPerformance.getFunctionality()).isEqualTo(UPDATED_FUNCTIONALITY);
        assertThat(testReportInstrumentPerformance.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportInstrumentPerformance.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void putNonExistingReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();
        reportInstrumentPerformance.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportInstrumentPerformanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportInstrumentPerformance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();
        reportInstrumentPerformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInstrumentPerformanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();
        reportInstrumentPerformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInstrumentPerformanceMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportInstrumentPerformanceWithPatch() throws Exception {
        // Initialize the database
        reportInstrumentPerformanceRepository.saveAndFlush(reportInstrumentPerformance);

        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();

        // Update the reportInstrumentPerformance using partial update
        ReportInstrumentPerformance partialUpdatedReportInstrumentPerformance = new ReportInstrumentPerformance();
        partialUpdatedReportInstrumentPerformance.setId(reportInstrumentPerformance.getId());

        partialUpdatedReportInstrumentPerformance
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .status(UPDATED_STATUS)
            .downtime(UPDATED_DOWNTIME)
            .serviceStatus(UPDATED_SERVICE_STATUS);

        restReportInstrumentPerformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportInstrumentPerformance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportInstrumentPerformance))
            )
            .andExpect(status().isOk());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
        ReportInstrumentPerformance testReportInstrumentPerformance = reportInstrumentPerformanceList.get(
            reportInstrumentPerformanceList.size() - 1
        );
        assertThat(testReportInstrumentPerformance.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportInstrumentPerformance.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportInstrumentPerformance.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportInstrumentPerformance.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testReportInstrumentPerformance.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testReportInstrumentPerformance.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testReportInstrumentPerformance.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReportInstrumentPerformance.getUptime()).isEqualTo(DEFAULT_UPTIME);
        assertThat(testReportInstrumentPerformance.getDowntime()).isEqualTo(UPDATED_DOWNTIME);
        assertThat(testReportInstrumentPerformance.getServiceStatus()).isEqualTo(UPDATED_SERVICE_STATUS);
        assertThat(testReportInstrumentPerformance.getCaliberationStatus()).isEqualTo(DEFAULT_CALIBERATION_STATUS);
        assertThat(testReportInstrumentPerformance.getFunctionality()).isEqualTo(DEFAULT_FUNCTIONALITY);
        assertThat(testReportInstrumentPerformance.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testReportInstrumentPerformance.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void fullUpdateReportInstrumentPerformanceWithPatch() throws Exception {
        // Initialize the database
        reportInstrumentPerformanceRepository.saveAndFlush(reportInstrumentPerformance);

        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();

        // Update the reportInstrumentPerformance using partial update
        ReportInstrumentPerformance partialUpdatedReportInstrumentPerformance = new ReportInstrumentPerformance();
        partialUpdatedReportInstrumentPerformance.setId(reportInstrumentPerformance.getId());

        partialUpdatedReportInstrumentPerformance
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .status(UPDATED_STATUS)
            .uptime(UPDATED_UPTIME)
            .downtime(UPDATED_DOWNTIME)
            .serviceStatus(UPDATED_SERVICE_STATUS)
            .caliberationStatus(UPDATED_CALIBERATION_STATUS)
            .functionality(UPDATED_FUNCTIONALITY)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportInstrumentPerformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportInstrumentPerformance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportInstrumentPerformance))
            )
            .andExpect(status().isOk());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
        ReportInstrumentPerformance testReportInstrumentPerformance = reportInstrumentPerformanceList.get(
            reportInstrumentPerformanceList.size() - 1
        );
        assertThat(testReportInstrumentPerformance.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportInstrumentPerformance.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportInstrumentPerformance.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportInstrumentPerformance.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportInstrumentPerformance.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testReportInstrumentPerformance.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testReportInstrumentPerformance.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testReportInstrumentPerformance.getUptime()).isEqualTo(UPDATED_UPTIME);
        assertThat(testReportInstrumentPerformance.getDowntime()).isEqualTo(UPDATED_DOWNTIME);
        assertThat(testReportInstrumentPerformance.getServiceStatus()).isEqualTo(UPDATED_SERVICE_STATUS);
        assertThat(testReportInstrumentPerformance.getCaliberationStatus()).isEqualTo(UPDATED_CALIBERATION_STATUS);
        assertThat(testReportInstrumentPerformance.getFunctionality()).isEqualTo(UPDATED_FUNCTIONALITY);
        assertThat(testReportInstrumentPerformance.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportInstrumentPerformance.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void patchNonExistingReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();
        reportInstrumentPerformance.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportInstrumentPerformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportInstrumentPerformance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();
        reportInstrumentPerformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInstrumentPerformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportInstrumentPerformance() throws Exception {
        int databaseSizeBeforeUpdate = reportInstrumentPerformanceRepository.findAll().size();
        reportInstrumentPerformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportInstrumentPerformanceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportInstrumentPerformance))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportInstrumentPerformance in the database
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReportInstrumentPerformance() throws Exception {
        // Initialize the database
        reportInstrumentPerformanceRepository.saveAndFlush(reportInstrumentPerformance);

        int databaseSizeBeforeDelete = reportInstrumentPerformanceRepository.findAll().size();

        // Delete the reportInstrumentPerformance
        restReportInstrumentPerformanceMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportInstrumentPerformance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportInstrumentPerformance> reportInstrumentPerformanceList = reportInstrumentPerformanceRepository.findAll();
        assertThat(reportInstrumentPerformanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
