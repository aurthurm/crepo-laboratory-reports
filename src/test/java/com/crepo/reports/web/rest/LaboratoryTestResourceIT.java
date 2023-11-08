package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.LaboratoryTest;
import com.crepo.reports.repository.LaboratoryTestRepository;
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
 * Integration tests for the {@link LaboratoryTestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LaboratoryTestResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_TEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TEST = "AAAAAAAAAA";
    private static final String UPDATED_TEST = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/laboratory-tests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLaboratoryTestMockMvc;

    private LaboratoryTest laboratoryTest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryTest createEntity(EntityManager em) {
        LaboratoryTest laboratoryTest = new LaboratoryTest()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .testId(DEFAULT_TEST_ID)
            .test(DEFAULT_TEST)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT);
        return laboratoryTest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LaboratoryTest createUpdatedEntity(EntityManager em) {
        LaboratoryTest laboratoryTest = new LaboratoryTest()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);
        return laboratoryTest;
    }

    @BeforeEach
    public void initTest() {
        laboratoryTest = createEntity(em);
    }

    @Test
    @Transactional
    void createLaboratoryTest() throws Exception {
        int databaseSizeBeforeCreate = laboratoryTestRepository.findAll().size();
        // Create the LaboratoryTest
        restLaboratoryTestMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isCreated());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeCreate + 1);
        LaboratoryTest testLaboratoryTest = laboratoryTestList.get(laboratoryTestList.size() - 1);
        assertThat(testLaboratoryTest.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testLaboratoryTest.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testLaboratoryTest.getTestId()).isEqualTo(DEFAULT_TEST_ID);
        assertThat(testLaboratoryTest.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testLaboratoryTest.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testLaboratoryTest.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
    }

    @Test
    @Transactional
    void createLaboratoryTestWithExistingId() throws Exception {
        // Create the LaboratoryTest with an existing ID
        laboratoryTest.setId(1L);

        int databaseSizeBeforeCreate = laboratoryTestRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaboratoryTestMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryTestRepository.findAll().size();
        // set the field null
        laboratoryTest.setLaboratoryId(null);

        // Create the LaboratoryTest, which fails.

        restLaboratoryTestMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = laboratoryTestRepository.findAll().size();
        // set the field null
        laboratoryTest.setTestId(null);

        // Create the LaboratoryTest, which fails.

        restLaboratoryTestMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLaboratoryTests() throws Exception {
        // Initialize the database
        laboratoryTestRepository.saveAndFlush(laboratoryTest);

        // Get all the laboratoryTestList
        restLaboratoryTestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laboratoryTest.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].testId").value(hasItem(DEFAULT_TEST_ID)))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)));
    }

    @Test
    @Transactional
    void getLaboratoryTest() throws Exception {
        // Initialize the database
        laboratoryTestRepository.saveAndFlush(laboratoryTest);

        // Get the laboratoryTest
        restLaboratoryTestMockMvc
            .perform(get(ENTITY_API_URL_ID, laboratoryTest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(laboratoryTest.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.testId").value(DEFAULT_TEST_ID))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT));
    }

    @Test
    @Transactional
    void getNonExistingLaboratoryTest() throws Exception {
        // Get the laboratoryTest
        restLaboratoryTestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLaboratoryTest() throws Exception {
        // Initialize the database
        laboratoryTestRepository.saveAndFlush(laboratoryTest);

        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();

        // Update the laboratoryTest
        LaboratoryTest updatedLaboratoryTest = laboratoryTestRepository.findById(laboratoryTest.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLaboratoryTest are not directly saved in db
        em.detach(updatedLaboratoryTest);
        updatedLaboratoryTest
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryTestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLaboratoryTest.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLaboratoryTest))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryTest testLaboratoryTest = laboratoryTestList.get(laboratoryTestList.size() - 1);
        assertThat(testLaboratoryTest.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryTest.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryTest.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testLaboratoryTest.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testLaboratoryTest.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryTest.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void putNonExistingLaboratoryTest() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();
        laboratoryTest.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryTestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, laboratoryTest.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLaboratoryTest() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();
        laboratoryTest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryTestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLaboratoryTest() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();
        laboratoryTest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryTestMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(laboratoryTest)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLaboratoryTestWithPatch() throws Exception {
        // Initialize the database
        laboratoryTestRepository.saveAndFlush(laboratoryTest);

        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();

        // Update the laboratoryTest using partial update
        LaboratoryTest partialUpdatedLaboratoryTest = new LaboratoryTest();
        partialUpdatedLaboratoryTest.setId(laboratoryTest.getId());

        partialUpdatedLaboratoryTest
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryTestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoryTest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLaboratoryTest))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryTest testLaboratoryTest = laboratoryTestList.get(laboratoryTestList.size() - 1);
        assertThat(testLaboratoryTest.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryTest.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryTest.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testLaboratoryTest.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testLaboratoryTest.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testLaboratoryTest.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void fullUpdateLaboratoryTestWithPatch() throws Exception {
        // Initialize the database
        laboratoryTestRepository.saveAndFlush(laboratoryTest);

        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();

        // Update the laboratoryTest using partial update
        LaboratoryTest partialUpdatedLaboratoryTest = new LaboratoryTest();
        partialUpdatedLaboratoryTest.setId(laboratoryTest.getId());

        partialUpdatedLaboratoryTest
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .testId(UPDATED_TEST_ID)
            .test(UPDATED_TEST)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT);

        restLaboratoryTestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoryTest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLaboratoryTest))
            )
            .andExpect(status().isOk());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
        LaboratoryTest testLaboratoryTest = laboratoryTestList.get(laboratoryTestList.size() - 1);
        assertThat(testLaboratoryTest.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testLaboratoryTest.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testLaboratoryTest.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testLaboratoryTest.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testLaboratoryTest.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testLaboratoryTest.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
    }

    @Test
    @Transactional
    void patchNonExistingLaboratoryTest() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();
        laboratoryTest.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoryTestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, laboratoryTest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLaboratoryTest() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();
        laboratoryTest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryTestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isBadRequest());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLaboratoryTest() throws Exception {
        int databaseSizeBeforeUpdate = laboratoryTestRepository.findAll().size();
        laboratoryTest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoryTestMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(laboratoryTest))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LaboratoryTest in the database
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLaboratoryTest() throws Exception {
        // Initialize the database
        laboratoryTestRepository.saveAndFlush(laboratoryTest);

        int databaseSizeBeforeDelete = laboratoryTestRepository.findAll().size();

        // Delete the laboratoryTest
        restLaboratoryTestMockMvc
            .perform(delete(ENTITY_API_URL_ID, laboratoryTest.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LaboratoryTest> laboratoryTestList = laboratoryTestRepository.findAll();
        assertThat(laboratoryTestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
