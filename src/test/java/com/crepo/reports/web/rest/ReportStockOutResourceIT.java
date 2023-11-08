package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.ReportStockOut;
import com.crepo.reports.domain.enumeration.StockAvailability;
import com.crepo.reports.repository.ReportStockOutRepository;
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
 * Integration tests for the {@link ReportStockOutResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportStockOutResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_STOCK_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_STOCK_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STOCK_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_STOCK_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final StockAvailability DEFAULT_AVAILABLE = StockAvailability.AVAILABLE;
    private static final StockAvailability UPDATED_AVAILABLE = StockAvailability.NOT_AVAILABLE;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REPORTING_PERIOD_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPORTING_PERIOD_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/report-stock-outs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportStockOutRepository reportStockOutRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportStockOutMockMvc;

    private ReportStockOut reportStockOut;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportStockOut createEntity(EntityManager em) {
        ReportStockOut reportStockOut = new ReportStockOut()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .stockItemId(DEFAULT_STOCK_ITEM_ID)
            .stockItem(DEFAULT_STOCK_ITEM)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT)
            .available(DEFAULT_AVAILABLE)
            .comment(DEFAULT_COMMENT)
            .reportingPeriodId(DEFAULT_REPORTING_PERIOD_ID);
        return reportStockOut;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportStockOut createUpdatedEntity(EntityManager em) {
        ReportStockOut reportStockOut = new ReportStockOut()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .stockItemId(UPDATED_STOCK_ITEM_ID)
            .stockItem(UPDATED_STOCK_ITEM)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .available(UPDATED_AVAILABLE)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);
        return reportStockOut;
    }

    @BeforeEach
    public void initTest() {
        reportStockOut = createEntity(em);
    }

    @Test
    @Transactional
    void createReportStockOut() throws Exception {
        int databaseSizeBeforeCreate = reportStockOutRepository.findAll().size();
        // Create the ReportStockOut
        restReportStockOutMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isCreated());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeCreate + 1);
        ReportStockOut testReportStockOut = reportStockOutList.get(reportStockOutList.size() - 1);
        assertThat(testReportStockOut.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testReportStockOut.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testReportStockOut.getStockItemId()).isEqualTo(DEFAULT_STOCK_ITEM_ID);
        assertThat(testReportStockOut.getStockItem()).isEqualTo(DEFAULT_STOCK_ITEM);
        assertThat(testReportStockOut.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testReportStockOut.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testReportStockOut.getAvailable()).isEqualTo(DEFAULT_AVAILABLE);
        assertThat(testReportStockOut.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testReportStockOut.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void createReportStockOutWithExistingId() throws Exception {
        // Create the ReportStockOut with an existing ID
        reportStockOut.setId(1L);

        int databaseSizeBeforeCreate = reportStockOutRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportStockOutMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportStockOutRepository.findAll().size();
        // set the field null
        reportStockOut.setLaboratoryId(null);

        // Create the ReportStockOut, which fails.

        restReportStockOutMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStockItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportStockOutRepository.findAll().size();
        // set the field null
        reportStockOut.setStockItemId(null);

        // Create the ReportStockOut, which fails.

        restReportStockOutMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReportingPeriodIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = reportStockOutRepository.findAll().size();
        // set the field null
        reportStockOut.setReportingPeriodId(null);

        // Create the ReportStockOut, which fails.

        restReportStockOutMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReportStockOuts() throws Exception {
        // Initialize the database
        reportStockOutRepository.saveAndFlush(reportStockOut);

        // Get all the reportStockOutList
        restReportStockOutMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportStockOut.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].stockItemId").value(hasItem(DEFAULT_STOCK_ITEM_ID)))
            .andExpect(jsonPath("$.[*].stockItem").value(hasItem(DEFAULT_STOCK_ITEM)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].available").value(hasItem(DEFAULT_AVAILABLE.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].reportingPeriodId").value(hasItem(DEFAULT_REPORTING_PERIOD_ID)));
    }

    @Test
    @Transactional
    void getReportStockOut() throws Exception {
        // Initialize the database
        reportStockOutRepository.saveAndFlush(reportStockOut);

        // Get the reportStockOut
        restReportStockOutMockMvc
            .perform(get(ENTITY_API_URL_ID, reportStockOut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportStockOut.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.stockItemId").value(DEFAULT_STOCK_ITEM_ID))
            .andExpect(jsonPath("$.stockItem").value(DEFAULT_STOCK_ITEM))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.available").value(DEFAULT_AVAILABLE.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.reportingPeriodId").value(DEFAULT_REPORTING_PERIOD_ID));
    }

    @Test
    @Transactional
    void getNonExistingReportStockOut() throws Exception {
        // Get the reportStockOut
        restReportStockOutMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReportStockOut() throws Exception {
        // Initialize the database
        reportStockOutRepository.saveAndFlush(reportStockOut);

        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();

        // Update the reportStockOut
        ReportStockOut updatedReportStockOut = reportStockOutRepository.findById(reportStockOut.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReportStockOut are not directly saved in db
        em.detach(updatedReportStockOut);
        updatedReportStockOut
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .stockItemId(UPDATED_STOCK_ITEM_ID)
            .stockItem(UPDATED_STOCK_ITEM)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .available(UPDATED_AVAILABLE)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportStockOutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReportStockOut.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReportStockOut))
            )
            .andExpect(status().isOk());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
        ReportStockOut testReportStockOut = reportStockOutList.get(reportStockOutList.size() - 1);
        assertThat(testReportStockOut.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportStockOut.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportStockOut.getStockItemId()).isEqualTo(UPDATED_STOCK_ITEM_ID);
        assertThat(testReportStockOut.getStockItem()).isEqualTo(UPDATED_STOCK_ITEM);
        assertThat(testReportStockOut.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportStockOut.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportStockOut.getAvailable()).isEqualTo(UPDATED_AVAILABLE);
        assertThat(testReportStockOut.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportStockOut.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void putNonExistingReportStockOut() throws Exception {
        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();
        reportStockOut.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportStockOutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reportStockOut.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReportStockOut() throws Exception {
        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();
        reportStockOut.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportStockOutMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReportStockOut() throws Exception {
        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();
        reportStockOut.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportStockOutMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reportStockOut)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportStockOutWithPatch() throws Exception {
        // Initialize the database
        reportStockOutRepository.saveAndFlush(reportStockOut);

        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();

        // Update the reportStockOut using partial update
        ReportStockOut partialUpdatedReportStockOut = new ReportStockOut();
        partialUpdatedReportStockOut.setId(reportStockOut.getId());

        partialUpdatedReportStockOut
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .available(UPDATED_AVAILABLE)
            .comment(UPDATED_COMMENT);

        restReportStockOutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportStockOut.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportStockOut))
            )
            .andExpect(status().isOk());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
        ReportStockOut testReportStockOut = reportStockOutList.get(reportStockOutList.size() - 1);
        assertThat(testReportStockOut.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportStockOut.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportStockOut.getStockItemId()).isEqualTo(DEFAULT_STOCK_ITEM_ID);
        assertThat(testReportStockOut.getStockItem()).isEqualTo(DEFAULT_STOCK_ITEM);
        assertThat(testReportStockOut.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportStockOut.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportStockOut.getAvailable()).isEqualTo(UPDATED_AVAILABLE);
        assertThat(testReportStockOut.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportStockOut.getReportingPeriodId()).isEqualTo(DEFAULT_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void fullUpdateReportStockOutWithPatch() throws Exception {
        // Initialize the database
        reportStockOutRepository.saveAndFlush(reportStockOut);

        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();

        // Update the reportStockOut using partial update
        ReportStockOut partialUpdatedReportStockOut = new ReportStockOut();
        partialUpdatedReportStockOut.setId(reportStockOut.getId());

        partialUpdatedReportStockOut
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .stockItemId(UPDATED_STOCK_ITEM_ID)
            .stockItem(UPDATED_STOCK_ITEM)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .available(UPDATED_AVAILABLE)
            .comment(UPDATED_COMMENT)
            .reportingPeriodId(UPDATED_REPORTING_PERIOD_ID);

        restReportStockOutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReportStockOut.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReportStockOut))
            )
            .andExpect(status().isOk());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
        ReportStockOut testReportStockOut = reportStockOutList.get(reportStockOutList.size() - 1);
        assertThat(testReportStockOut.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testReportStockOut.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testReportStockOut.getStockItemId()).isEqualTo(UPDATED_STOCK_ITEM_ID);
        assertThat(testReportStockOut.getStockItem()).isEqualTo(UPDATED_STOCK_ITEM);
        assertThat(testReportStockOut.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testReportStockOut.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testReportStockOut.getAvailable()).isEqualTo(UPDATED_AVAILABLE);
        assertThat(testReportStockOut.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testReportStockOut.getReportingPeriodId()).isEqualTo(UPDATED_REPORTING_PERIOD_ID);
    }

    @Test
    @Transactional
    void patchNonExistingReportStockOut() throws Exception {
        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();
        reportStockOut.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportStockOutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reportStockOut.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReportStockOut() throws Exception {
        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();
        reportStockOut.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportStockOutMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReportStockOut() throws Exception {
        int databaseSizeBeforeUpdate = reportStockOutRepository.findAll().size();
        reportStockOut.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportStockOutMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(reportStockOut))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReportStockOut in the database
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReportStockOut() throws Exception {
        // Initialize the database
        reportStockOutRepository.saveAndFlush(reportStockOut);

        int databaseSizeBeforeDelete = reportStockOutRepository.findAll().size();

        // Delete the reportStockOut
        restReportStockOutMockMvc
            .perform(delete(ENTITY_API_URL_ID, reportStockOut.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportStockOut> reportStockOutList = reportStockOutRepository.findAll();
        assertThat(reportStockOutList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
