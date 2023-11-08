package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.LaboratoryStock;
import com.crepo.reports.repository.LaboratoryStockRepository;
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
 * Integration tests for the {@link LaboratoryStockResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LaboratoryStockResourceIT {

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

    private static final String ENTITY_API_URL = "/api/laboratory-stocks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LaboratoryStockRepository laboratoryStockRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLaboratoryStockMockMvc;

    private LaboratoryStock laboratoryStock;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryStock createEntity(EntityManager em) {
        LaboratoryStock laboratoryStock = new LaboratoryStock()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .stockItemId(DEFAULT_STOCK_ITEM_ID)
            .stockItem(DEFAULT_STOCK_ITEM)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT);
        return laboratoryStock;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryStock createUpdatedEntity(EntityManager em) {
        LaboratoryStock laboratoryStock = new LaboratoryStock()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .stockItemId(UPDATED_STOCK_ITEM_ID)
            .stockItem(UPDATED_STOCK_ITEM)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);
        return laboratoryStock;
    }

    @BeforeEach
    public void initTest() {
        laboratoryStock = createEntity(em);
    }

    @Test
    @Transactional
    void createLaboratoryStock() throws Exception {
        int databaseSizeBeforeCreate = laboratoryStockRepository.findAll().size();
        // Create the LaboratoryStock
        restLaboratoryStockMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isCreated());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeCreate + 1);
        LaboratoryStock testLaboratoryStock = laboratoryStockList.get(laboratoryStockList.size() - 1);
        assertThat(testLaboratoryStock.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testLaboratoryStock.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testLaboratoryStock.getStockItemId()).isEqualTo(DEFAULT_STOCK_ITEM_ID);
        assertThat(testLaboratoryStock.getStockItem()).isEqualTo(DEFAULT_STOCK_ITEM);
        assertThat(testLaboratoryStock.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testLaboratoryStock.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
    }

    @Test
    @Transactional
    void createLaboratoryStockWithExistingId() throws Exception {
        // Create the LaboratoryStock with an existing ID
        laboratoryStock.setId(1L);

        int databaseSizeBeforeCreate = laboratoryStockRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaboratoryStockMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryStockRepository.findAll().size();
        // set the field null
        laboratoryStock.setLaboratoryId(null);

        // Create the LaboratoryStock, which fails.

        restLaboratoryStockMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStockItemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryStockRepository.findAll().size();
        // set the field null
        laboratoryStock.setStockItemId(null);

        // Create the LaboratoryStock, which fails.

        restLaboratoryStockMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLaboratoryStocks() throws Exception {
        // Initialize the database
        laboratoryStockRepository.saveAndFlush(laboratoryStock);

        // Get all the laboratoryStockList
        restLaboratoryStockMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laboratoryStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].stockItemId").value(hasItem(DEFAULT_STOCK_ITEM_ID)))
            .andExpect(jsonPath("$.[*].stockItem").value(hasItem(DEFAULT_STOCK_ITEM)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)));
    }

    @Test
    @Transactional
    void getLaboratoryStock() throws Exception {
        // Initialize the database
        laboratoryStockRepository.saveAndFlush(laboratoryStock);

        // Get the laboratoryStock
        restLaboratoryStockMockMvc
            .perform(get(ENTITY_API_URL_ID, laboratoryStock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(laboratoryStock.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.stockItemId").value(DEFAULT_STOCK_ITEM_ID))
            .andExpect(jsonPath("$.stockItem").value(DEFAULT_STOCK_ITEM))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT));
    }

    @Test
    @Transactional
    void getNonExistingLaboratoryStock() throws Exception {
        // Get the laboratoryStock
        restLaboratoryStockMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLaboratoryStock() throws Exception {
        // Initialize the database
        laboratoryStockRepository.saveAndFlush(laboratoryStock);

        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();

        // Update the laboratoryStock
        LaboratoryStock updatedLaboratoryStock = laboratoryStockRepository.findById(laboratoryStock.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLaboratoryStock are not directly saved in db
        em.detach(updatedLaboratoryStock);
        updatedLaboratoryStock
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .stockItemId(UPDATED_STOCK_ITEM_ID)
            .stockItem(UPDATED_STOCK_ITEM)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryStockMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLaboratoryStock.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLaboratoryStock))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryStock testLaboratoryStock = laboratoryStockList.get(laboratoryStockList.size() - 1);
        assertThat(testLaboratoryStock.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryStock.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryStock.getStockItemId()).isEqualTo(UPDATED_STOCK_ITEM_ID);
        assertThat(testLaboratoryStock.getStockItem()).isEqualTo(UPDATED_STOCK_ITEM);
        assertThat(testLaboratoryStock.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryStock.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void putNonExistingLaboratoryStock() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();
        laboratoryStock.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryStockMockMvc
            .perform(
                put(ENTITY_API_URL_ID, laboratoryStock.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLaboratoryStock() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();
        laboratoryStock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryStockMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLaboratoryStock() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();
        laboratoryStock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryStockMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLaboratoryStockWithPatch() throws Exception {
        // Initialize the database
        laboratoryStockRepository.saveAndFlush(laboratoryStock);

        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();

        // Update the laboratoryStock using partial update
        LaboratoryStock partialUpdatedLaboratoryStock = new LaboratoryStock();
        partialUpdatedLaboratoryStock.setId(laboratoryStock.getId());

        partialUpdatedLaboratoryStock
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID);

        restLaboratoryStockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoryStock.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLaboratoryStock))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryStock testLaboratoryStock = laboratoryStockList.get(laboratoryStockList.size() - 1);
        assertThat(testLaboratoryStock.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryStock.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryStock.getStockItemId()).isEqualTo(DEFAULT_STOCK_ITEM_ID);
        assertThat(testLaboratoryStock.getStockItem()).isEqualTo(DEFAULT_STOCK_ITEM);
        assertThat(testLaboratoryStock.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryStock.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
    }

    @Test
    @Transactional
    void fullUpdateLaboratoryStockWithPatch() throws Exception {
        // Initialize the database
        laboratoryStockRepository.saveAndFlush(laboratoryStock);

        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();

        // Update the laboratoryStock using partial update
        LaboratoryStock partialUpdatedLaboratoryStock = new LaboratoryStock();
        partialUpdatedLaboratoryStock.setId(laboratoryStock.getId());

        partialUpdatedLaboratoryStock
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .stockItemId(UPDATED_STOCK_ITEM_ID)
            .stockItem(UPDATED_STOCK_ITEM)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryStockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoryStock.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLaboratoryStock))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryStock testLaboratoryStock = laboratoryStockList.get(laboratoryStockList.size() - 1);
        assertThat(testLaboratoryStock.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryStock.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryStock.getStockItemId()).isEqualTo(UPDATED_STOCK_ITEM_ID);
        assertThat(testLaboratoryStock.getStockItem()).isEqualTo(UPDATED_STOCK_ITEM);
        assertThat(testLaboratoryStock.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryStock.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void patchNonExistingLaboratoryStock() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();
        laboratoryStock.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryStockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, laboratoryStock.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLaboratoryStock() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();
        laboratoryStock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryStockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLaboratoryStock() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryStockRepository.findAll().size();
        laboratoryStock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryStockMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryStock))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LaboratoryStock in the database
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLaboratoryStock() throws Exception {
        // Initialize the database
        laboratoryStockRepository.saveAndFlush(laboratoryStock);

        int databaseSizeBeforeDelete = laboratoryStockRepository.findAll().size();

        // Delete the laboratoryStock
        restLaboratoryStockMockMvc
            .perform(delete(ENTITY_API_URL_ID, laboratoryStock.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LaboratoryStock> laboratoryStockList = laboratoryStockRepository.findAll();
        assertThat(laboratoryStockList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
