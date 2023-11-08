package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.ReportTestPeformance;
import com.crepo.reports.domain.enumeration.CriticalResult;
import com.crepo.reports.repository.ReportTestPeformanceRepository;
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
 * Integration tests for the {@link ReportTestPeformanceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportTestPeformanceResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_SAMPLE_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SAMPLE_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SAMPLE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SAMPLE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_TEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TEST = "AAAAAAAAAA";
    private static final String UPDATED_TEST = "BBBBBBBBBB";

    private static final String DEFAULT_TURN_AROUND_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TURN_AROUND_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_TESTED = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_TESTED = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_DISPATCHED = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_DISPATCHED = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_REJECTED = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_REJECTED = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT = "BBBBBBBBBB";

    private static final CriticalResult DEFAULT_CRITICAL_RESULTS = CriticalResult.YES;
    private static final CriticalResult UPDATED_CRITICAL_RESULTS = CriticalResult.NO;

    private static final String DEFAULT_NUMBER_CRITICAL = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_CRITICAL = "BBBBBBBBBB";

    private static final String DEFAULT_CRITICAL_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_CRITICAL_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REPORTING_PERIOD_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPORTING_PERIOD_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/report-test-peformances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportTestPeformanceRepository reportTestPeformanceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportTestPeformanceMockMvc;

    private ReportTestPeformance reportTestPeformance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportTestPeformance createEntity(EntityManager em) {
        ReportTestPeformance reportTestPeformance = new ReportTestPeformance()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT)
            .sampleTypeId(DEFAULT_SAMPLE_TYPE_ID)
            .sampleType(DEFAULT_SAMPLE_TYPE)
            .testId(DEFAULT_TEST_ID)
            .test(DEFAULT_TEST)
            .turnAroundTime(DEFAULT_TURN_AROUND_TIME)
            .numberTested(DEFAULT_NUMBER_TESTED)
            .numberDispatched(DEFAULT_NUMBER_DISPATCHED)
            .numberRejected(DEFAULT_NUMBER_REJECTED)
            .instrumentId(DEFAULT_INSTRUMENT_ID)
            .instrument(DEFAULT_INSTRUMENT)
            .criticalResults(DEFAULT_CRITICAL_RESULTS)
            .numberCritical(DEFAULT_NUMBER_CRITICAL)
            .criticalComment(DEFAULT_CRITICAL_COMMENT)
            .reportingPeriodId(DEFAULT_REPORTING_PERIOD_ID);
        return reportTestPeformance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportTestPeformance createUpdatedEntity(EntityManager em) {
        ReportTestPeformance reportTestPeformance = new ReportTestPeformance()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleType(UPDATED_SAMPLE_TYPE)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .turnAroundTime(UPDATED_TURN_AROUND_TIME)
            .numberTested(UPDATED_NUMBER_TESTED)
            .numberDispatched(UPDATED_NUMBER_DISPATCHED)
            .numberRejected(UPDATED_NUMBER_REJECTED)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .criticalResults(UPDATED_CRITICAL_RESULTS)
            .numberCritical(UPDATED_NUMBER_CRITICAL)
            .criticalComment(UPDATED_CRITICAL_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);
        return reportTestPeformance;
    }

    @BeforeEach
    public void initTest() {
        reportTestPeformance = createEntity(em);
    }

    @Test
    @Transactional
    void createReportTestPeformance() throws Exception {
        int databaseSizeBeforeCreate = reportTestPeformanceRepository.findAll().size();
        // Create the ReportTestPeformance
        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isCreated());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeCreate + 1);
        ReportTestPeformance testReportTestPeformance = reportTestPeformanceList.get(reportTestPeformanceList.size() - 1);
        assertThat(testReportTestPeformance.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportTestPeformance.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportTestPeformance.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testReportTestPeformance.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testReportTestPeformance.getSampleTypeId()).isEqualTo(DEFAULT_SAMPLE_TYPE_ID);
        assertThat(testReportTestPeformance.getSampleType()).isEqualTo(DEFAULT_SAMPLE_TYPE);
        assertThat(testReportTestPeformance.getTestId()).isEqualTo(DEFAULT_TEST_ID);
        assertThat(testReportTestPeformance.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testReportTestPeformance.getTurnAroundTime()).isEqualTo(DEFAULT_TURN_AROUND_TIME);
        assertThat(testReportTestPeformance.getNumberTested()).isEqualTo(DEFAULT_NUMBER_TESTED);
        assertThat(testReportTestPeformance.getNumberDispatched()).isEqualTo(DEFAULT_NUMBER_DISPATCHED);
        assertThat(testReportTestPeformance.getNumberRejected()).isEqualTo(DEFAULT_NUMBER_REJECTED);
        assertThat(testReportTestPeformance.getInstrumentId()).isEqualTo(DEFAULT_INSTRUMENT_ID);
        assertThat(testReportTestPeformance.getInstrument()).isEqualTo(DEFAULT_INSTRUMENT);
        assertThat(testReportTestPeformance.getCriticalResults()).isEqualTo(DEFAULT_CRITICAL_RESULTS);
        assertThat(testReportTestPeformance.getNumberCritical()).isEqualTo(DEFAULT_NUMBER_CRITICAL);
        assertThat(testReportTestPeformance.getCriticalComment()).isEqualTo(DEFAULT_CRITICAL_COMMENT);
        assertThat(testReportTestPeformance.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void createReportTestPeformanceWithExistingId() throws Exception {
        // Create the ReportTestPeformance with an existing ID
        reportTestPeformance.setId(1L);

        int databaseSizeBeforeCreate = reportTestPeformanceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportTestPeformanceRepository.findAll().size();
        // set the field null
        reportTestPeformance.setLaboratoryId(null);

        // Create the ReportTestPeformance, which fails.

        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSampleTypeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportTestPeformanceRepository.findAll().size();
        // set the field null
        reportTestPeformance.setSampleTypeId(null);

        // Create the ReportTestPeformance, which fails.

        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportTestPeformanceRepository.findAll().size();
        // set the field null
        reportTestPeformance.setTestId(null);

        // Create the ReportTestPeformance, which fails.

        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInstrumentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportTestPeformanceRepository.findAll().size();
        // set the field null
        reportTestPeformance.setInstrumentId(null);

        // Create the ReportTestPeformance, which fails.

        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReportingPeriodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportTestPeformanceRepository.findAll().size();
        // set the field null
        reportTestPeformance.setReportingPeriodId(null);

        // Create the ReportTestPeformance, which fails.

        restReportTestPeformanceMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReportTestPeformances() throws Exception {
        // Initialize the database
        reportTestPeformanceRepository.saveAndFlush(reportTestPeformance);

        // Get all the reportTestPeformanceList
        restReportTestPeformanceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportTestPeformance.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].sampleTypeId").value(hasItem(DEFAULT_SAMPLE_TYPE_ID)))
            .andExpect(jsonPath("$.[*].sampleType").value(hasItem(DEFAULT_SAMPLE_TYPE)))
            .andExpect(jsonPath("$.[*].testId").value(hasItem(DEFAULT_TEST_ID)))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST)))
            .andExpect(jsonPath("$.[*].turnAroundTime").value(hasItem(DEFAULT_TURN_AROUND_TIME)))
            .andExpect(jsonPath("$.[*].numberTested").value(hasItem(DEFAULT_NUMBER_TESTED)))
            .andExpect(jsonPath("$.[*].numberDispatched").value(hasItem(DEFAULT_NUMBER_DISPATCHED)))
            .andExpect(jsonPath("$.[*].numberRejected").value(hasItem(DEFAULT_NUMBER_REJECTED)))
            .andExpect(jsonPath("$.[*].instrumentId").value(hasItem(DEFAULT_INSTRUMENT_ID)))
            .andExpect(jsonPath("$.[*].instrument").value(hasItem(DEFAULT_INSTRUMENT)))
            .andExpect(jsonPath("$.[*].criticalResults").value(hasItem(DEFAULT_CRITICAL_RESULTS.toString())))
            .andExpect(jsonPath("$.[*].numberCritical").value(hasItem(DEFAULT_NUMBER_CRITICAL)))
            .andExpect(jsonPath("$.[*].criticalComment").value(hasItem(DEFAULT_CRITICAL_COMMENT)))
            .andExpect(jsonPath("$.[*].reportingPeriodId").value(hasItem(DEFAULT_REPORTING_PERIOD_ID)));
    }

    @Test
    @Transactional
    void getReportTestPeformance() throws Exception {
        // Initialize the database
        reportTestPeformanceRepository.saveAndFlush(reportTestPeformance);

        // Get the reportTestPeformance
        restReportTestPeformanceMockMvc
            .perform(get(ENTITY_API_URL_ID, reportTestPeformance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportTestPeformance.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.sampleTypeId").value(DEFAULT_SAMPLE_TYPE_ID))
            .andExpect(jsonPath("$.sampleType").value(DEFAULT_SAMPLE_TYPE))
            .andExpect(jsonPath("$.testId").value(DEFAULT_TEST_ID))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST))
            .andExpect(jsonPath("$.turnAroundTime").value(DEFAULT_TURN_AROUND_TIME))
            .andExpect(jsonPath("$.numberTested").value(DEFAULT_NUMBER_TESTED))
            .andExpect(jsonPath("$.numberDispatched").value(DEFAULT_NUMBER_DISPATCHED))
            .andExpect(jsonPath("$.numberRejected").value(DEFAULT_NUMBER_REJECTED))
            .andExpect(jsonPath("$.instrumentId").value(DEFAULT_INSTRUMENT_ID))
            .andExpect(jsonPath("$.instrument").value(DEFAULT_INSTRUMENT))
            .andExpect(jsonPath("$.criticalResults").value(DEFAULT_CRITICAL_RESULTS.toString()))
            .andExpect(jsonPath("$.numberCritical").value(DEFAULT_NUMBER_CRITICAL))
            .andExpect(jsonPath("$.criticalComment").value(DEFAULT_CRITICAL_COMMENT))
            .andExpect(jsonPath("$.reportingPeriodId").value(DEFAULT_REPORTING_PERIOD_ID));
    }

    @Test
    @Transactional
    void getNonExistingReportTestPeformance() throws Exception {
        // Get the reportTestPeformance
        restReportTestPeformanceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportTestPeformance() throws Exception {
        // Initialize the database
        reportTestPeformanceRepository.saveAndFlush(reportTestPeformance);

        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();

        // Update the reportTestPeformance
        ReportTestPeformance updatedReportTestPeformance = reportTestPeformanceRepository
            .findById(reportTestPeformance.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedReportTestPeformance are not directly saved in db
        em.detach(updatedReportTestPeformance);
        updatedReportTestPeformance
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleType(UPDATED_SAMPLE_TYPE)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .turnAroundTime(UPDATED_TURN_AROUND_TIME)
            .numberTested(UPDATED_NUMBER_TESTED)
            .numberDispatched(UPDATED_NUMBER_DISPATCHED)
            .numberRejected(UPDATED_NUMBER_REJECTED)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .criticalResults(UPDATED_CRITICAL_RESULTS)
            .numberCritical(UPDATED_NUMBER_CRITICAL)
            .criticalComment(UPDATED_CRITICAL_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportTestPeformanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReportTestPeformance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReportTestPeformance))
            )
            .andExpect(status().isOk());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
        ReportTestPeformance testReportTestPeformance = reportTestPeformanceList.get(reportTestPeformanceList.size() - 1);
        assertThat(testReportTestPeformance.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportTestPeformance.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportTestPeformance.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportTestPeformance.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportTestPeformance.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testReportTestPeformance.getSampleType()).isEqualTo(UPDATED_SAMPLE_TYPE);
        assertThat(testReportTestPeformance.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testReportTestPeformance.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testReportTestPeformance.getTurnAroundTime()).isEqualTo(UPDATED_TURN_AROUND_TIME);
        assertThat(testReportTestPeformance.getNumberTested()).isEqualTo(UPDATED_NUMBER_TESTED);
        assertThat(testReportTestPeformance.getNumberDispatched()).isEqualTo(UPDATED_NUMBER_DISPATCHED);
        assertThat(testReportTestPeformance.getNumberRejected()).isEqualTo(UPDATED_NUMBER_REJECTED);
        assertThat(testReportTestPeformance.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testReportTestPeformance.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testReportTestPeformance.getCriticalResults()).isEqualTo(UPDATED_CRITICAL_RESULTS);
        assertThat(testReportTestPeformance.getNumberCritical()).isEqualTo(UPDATED_NUMBER_CRITICAL);
        assertThat(testReportTestPeformance.getCriticalComment()).isEqualTo(UPDATED_CRITICAL_COMMENT);
        assertThat(testReportTestPeformance.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void putNonExistingReportTestPeformance() throws Exception {
        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();
        reportTestPeformance.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportTestPeformanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportTestPeformance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportTestPeformance() throws Exception {
        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();
        reportTestPeformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportTestPeformanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportTestPeformance() throws Exception {
        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();
        reportTestPeformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportTestPeformanceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportTestPeformanceWithPatch() throws Exception {
        // Initialize the database
        reportTestPeformanceRepository.saveAndFlush(reportTestPeformance);

        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();

        // Update the reportTestPeformance using partial update
        ReportTestPeformance partialUpdatedReportTestPeformance = new ReportTestPeformance();
        partialUpdatedReportTestPeformance.setId(reportTestPeformance.getId());

        partialUpdatedReportTestPeformance
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .numberTested(UPDATED_NUMBER_TESTED)
            .numberDispatched(UPDATED_NUMBER_DISPATCHED)
            .numberRejected(UPDATED_NUMBER_REJECTED)
            .numberCritical(UPDATED_NUMBER_CRITICAL)
            .criticalComment(UPDATED_CRITICAL_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportTestPeformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportTestPeformance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportTestPeformance))
            )
            .andExpect(status().isOk());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
        ReportTestPeformance testReportTestPeformance = reportTestPeformanceList.get(reportTestPeformanceList.size() - 1);
        assertThat(testReportTestPeformance.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportTestPeformance.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportTestPeformance.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportTestPeformance.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportTestPeformance.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testReportTestPeformance.getSampleType()).isEqualTo(DEFAULT_SAMPLE_TYPE);
        assertThat(testReportTestPeformance.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testReportTestPeformance.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testReportTestPeformance.getTurnAroundTime()).isEqualTo(DEFAULT_TURN_AROUND_TIME);
        assertThat(testReportTestPeformance.getNumberTested()).isEqualTo(UPDATED_NUMBER_TESTED);
        assertThat(testReportTestPeformance.getNumberDispatched()).isEqualTo(UPDATED_NUMBER_DISPATCHED);
        assertThat(testReportTestPeformance.getNumberRejected()).isEqualTo(UPDATED_NUMBER_REJECTED);
        assertThat(testReportTestPeformance.getInstrumentId()).isEqualTo(DEFAULT_INSTRUMENT_ID);
        assertThat(testReportTestPeformance.getInstrument()).isEqualTo(DEFAULT_INSTRUMENT);
        assertThat(testReportTestPeformance.getCriticalResults()).isEqualTo(DEFAULT_CRITICAL_RESULTS);
        assertThat(testReportTestPeformance.getNumberCritical()).isEqualTo(UPDATED_NUMBER_CRITICAL);
        assertThat(testReportTestPeformance.getCriticalComment()).isEqualTo(UPDATED_CRITICAL_COMMENT);
        assertThat(testReportTestPeformance.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void fullUpdateReportTestPeformanceWithPatch() throws Exception {
        // Initialize the database
        reportTestPeformanceRepository.saveAndFlush(reportTestPeformance);

        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();

        // Update the reportTestPeformance using partial update
        ReportTestPeformance partialUpdatedReportTestPeformance = new ReportTestPeformance();
        partialUpdatedReportTestPeformance.setId(reportTestPeformance.getId());

        partialUpdatedReportTestPeformance
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .sampleTypeId(UPDATED_SAMPLE_TYPE_ID)
            .sampleType(UPDATED_SAMPLE_TYPE)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .turnAroundTime(UPDATED_TURN_AROUND_TIME)
            .numberTested(UPDATED_NUMBER_TESTED)
            .numberDispatched(UPDATED_NUMBER_DISPATCHED)
            .numberRejected(UPDATED_NUMBER_REJECTED)
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrument(UPDATED_INSTRUMENT)
            .criticalResults(UPDATED_CRITICAL_RESULTS)
            .numberCritical(UPDATED_NUMBER_CRITICAL)
            .criticalComment(UPDATED_CRITICAL_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportTestPeformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportTestPeformance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportTestPeformance))
            )
            .andExpect(status().isOk());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
        ReportTestPeformance testReportTestPeformance = reportTestPeformanceList.get(reportTestPeformanceList.size() - 1);
        assertThat(testReportTestPeformance.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportTestPeformance.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportTestPeformance.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportTestPeformance.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportTestPeformance.getSampleTypeId()).isEqualTo(UPDATED_SAMPLE_TYPE_ID);
        assertThat(testReportTestPeformance.getSampleType()).isEqualTo(UPDATED_SAMPLE_TYPE);
        assertThat(testReportTestPeformance.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testReportTestPeformance.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testReportTestPeformance.getTurnAroundTime()).isEqualTo(UPDATED_TURN_AROUND_TIME);
        assertThat(testReportTestPeformance.getNumberTested()).isEqualTo(UPDATED_NUMBER_TESTED);
        assertThat(testReportTestPeformance.getNumberDispatched()).isEqualTo(UPDATED_NUMBER_DISPATCHED);
        assertThat(testReportTestPeformance.getNumberRejected()).isEqualTo(UPDATED_NUMBER_REJECTED);
        assertThat(testReportTestPeformance.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testReportTestPeformance.getInstrument()).isEqualTo(UPDATED_INSTRUMENT);
        assertThat(testReportTestPeformance.getCriticalResults()).isEqualTo(UPDATED_CRITICAL_RESULTS);
        assertThat(testReportTestPeformance.getNumberCritical()).isEqualTo(UPDATED_NUMBER_CRITICAL);
        assertThat(testReportTestPeformance.getCriticalComment()).isEqualTo(UPDATED_CRITICAL_COMMENT);
        assertThat(testReportTestPeformance.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void patchNonExistingReportTestPeformance() throws Exception {
        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();
        reportTestPeformance.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportTestPeformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportTestPeformance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportTestPeformance() throws Exception {
        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();
        reportTestPeformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportTestPeformanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportTestPeformance() throws Exception {
        int databaseSizeBeforeUpdate = reportTestPeformanceRepository.findAll().size();
        reportTestPeformance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportTestPeformanceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportTestPeformance))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportTestPeformance in the database
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReportTestPeformance() throws Exception {
        // Initialize the database
        reportTestPeformanceRepository.saveAndFlush(reportTestPeformance);

        int databaseSizeBeforeDelete = reportTestPeformanceRepository.findAll().size();

        // Delete the reportTestPeformance
        restReportTestPeformanceMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportTestPeformance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportTestPeformance> reportTestPeformanceList = reportTestPeformanceRepository.findAll();
        assertThat(reportTestPeformanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
