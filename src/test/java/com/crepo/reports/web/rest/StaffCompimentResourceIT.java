package com.crepo.reports.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crepo.reports.IntegrationTest;
import com.crepo.reports.domain.StaffCompiment;
import com.crepo.reports.repository.StaffCompimentRepository;
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
 * Integration tests for the {@link StaffCompimentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StaffCompimentResourceIT {

    private static final String DEFAULT_LABORATORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LABORATORY = "AAAAAAAAAA";
    private static final String UPDATED_LABORATORY = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_SCIENTIST_AVAILABLE = 1L;
    private static final Long UPDATED_SCIENTIST_AVAILABLE = 2L;

    private static final Long DEFAULT_SCIENTIST_REQUIRED = 1L;
    private static final Long UPDATED_SCIENTIST_REQUIRED = 2L;

    private static final Long DEFAULT_MICROSCOPITS_AVAILABLE = 1L;
    private static final Long UPDATED_MICROSCOPITS_AVAILABLE = 2L;

    private static final Long DEFAULT_MICROSCOPITS_REQUIRED = 1L;
    private static final Long UPDATED_MICROSCOPITS_REQUIRED = 2L;

    private static final Long DEFAULT_LAB_TECHS_AVAILABLE = 1L;
    private static final Long UPDATED_LAB_TECHS_AVAILABLE = 2L;

    private static final Long DEFAULT_LAB_TECHS_REQUIRED = 1L;
    private static final Long UPDATED_LAB_TECHS_REQUIRED = 2L;

    private static final Long DEFAULT_GENERAL_HANDS_AVAILABLE = 1L;
    private static final Long UPDATED_GENERAL_HANDS_AVAILABLE = 2L;

    private static final Long DEFAULT_GENERAL_HANDS_REQUIRED = 1L;
    private static final Long UPDATED_GENERAL_HANDS_REQUIRED = 2L;

    private static final String ENTITY_API_URL = "/api/staff-compiments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StaffCompimentRepository staffCompimentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStaffCompimentMockMvc;

    private StaffCompiment staffCompiment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StaffCompiment createEntity(EntityManager em) {
        StaffCompiment staffCompiment = new StaffCompiment()
            .laboratoryId(DEFAULT_LABORATORY_ID)
            .laboratory(DEFAULT_LABORATORY)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .department(DEFAULT_DEPARTMENT)
            .scientistAvailable(DEFAULT_SCIENTIST_AVAILABLE)
            .scientistRequired(DEFAULT_SCIENTIST_REQUIRED)
            .microscopitsAvailable(DEFAULT_MICROSCOPITS_AVAILABLE)
            .microscopitsRequired(DEFAULT_MICROSCOPITS_REQUIRED)
            .labTechsAvailable(DEFAULT_LAB_TECHS_AVAILABLE)
            .labTechsRequired(DEFAULT_LAB_TECHS_REQUIRED)
            .generalHandsAvailable(DEFAULT_GENERAL_HANDS_AVAILABLE)
            .generalHandsRequired(DEFAULT_GENERAL_HANDS_REQUIRED);
        return staffCompiment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StaffCompiment createUpdatedEntity(EntityManager em) {
        StaffCompiment staffCompiment = new StaffCompiment()
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .scientistAvailable(UPDATED_SCIENTIST_AVAILABLE)
            .scientistRequired(UPDATED_SCIENTIST_REQUIRED)
            .microscopitsAvailable(UPDATED_MICROSCOPITS_AVAILABLE)
            .microscopitsRequired(UPDATED_MICROSCOPITS_REQUIRED)
            .labTechsAvailable(UPDATED_LAB_TECHS_AVAILABLE)
            .labTechsRequired(UPDATED_LAB_TECHS_REQUIRED)
            .generalHandsAvailable(UPDATED_GENERAL_HANDS_AVAILABLE)
            .generalHandsRequired(UPDATED_GENERAL_HANDS_REQUIRED);
        return staffCompiment;
    }

    @BeforeEach
    public void initTest() {
        staffCompiment = createEntity(em);
    }

    @Test
    @Transactional
    void createStaffCompiment() throws Exception {
        int databaseSizeBeforeCreate = staffCompimentRepository.findAll().size();
        // Create the StaffCompiment
        restStaffCompimentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isCreated());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeCreate + 1);
        StaffCompiment testStaffCompiment = staffCompimentList.get(staffCompimentList.size() - 1);
        assertThat(testStaffCompiment.getLaboratoryId()).isEqualTo(DEFAULT_LABORATORY_ID);
        assertThat(testStaffCompiment.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testStaffCompiment.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testStaffCompiment.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testStaffCompiment.getScientistAvailable()).isEqualTo(DEFAULT_SCIENTIST_AVAILABLE);
        assertThat(testStaffCompiment.getScientistRequired()).isEqualTo(DEFAULT_SCIENTIST_REQUIRED);
        assertThat(testStaffCompiment.getMicroscopitsAvailable()).isEqualTo(DEFAULT_MICROSCOPITS_AVAILABLE);
        assertThat(testStaffCompiment.getMicroscopitsRequired()).isEqualTo(DEFAULT_MICROSCOPITS_REQUIRED);
        assertThat(testStaffCompiment.getLabTechsAvailable()).isEqualTo(DEFAULT_LAB_TECHS_AVAILABLE);
        assertThat(testStaffCompiment.getLabTechsRequired()).isEqualTo(DEFAULT_LAB_TECHS_REQUIRED);
        assertThat(testStaffCompiment.getGeneralHandsAvailable()).isEqualTo(DEFAULT_GENERAL_HANDS_AVAILABLE);
        assertThat(testStaffCompiment.getGeneralHandsRequired()).isEqualTo(DEFAULT_GENERAL_HANDS_REQUIRED);
    }

    @Test
    @Transactional
    void createStaffCompimentWithExistingId() throws Exception {
        // Create the StaffCompiment with an existing ID
        staffCompiment.setId(1L);

        int databaseSizeBeforeCreate = staffCompimentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStaffCompimentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isBadRequest());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLaboratoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = staffCompimentRepository.findAll().size();
        // set the field null
        staffCompiment.setLaboratoryId(null);

        // Create the StaffCompiment, which fails.

        restStaffCompimentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isBadRequest());

        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStaffCompiments() throws Exception {
        // Initialize the database
        staffCompimentRepository.saveAndFlush(staffCompiment);

        // Get all the staffCompimentList
        restStaffCompimentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staffCompiment.getId().intValue())))
            .andExpect(jsonPath("$.[*].laboratoryId").value(hasItem(DEFAULT_LABORATORY_ID)))
            .andExpect(jsonPath("$.[*].laboratory").value(hasItem(DEFAULT_LABORATORY)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].scientistAvailable").value(hasItem(DEFAULT_SCIENTIST_AVAILABLE.intValue())))
            .andExpect(jsonPath("$.[*].scientistRequired").value(hasItem(DEFAULT_SCIENTIST_REQUIRED.intValue())))
            .andExpect(jsonPath("$.[*].microscopitsAvailable").value(hasItem(DEFAULT_MICROSCOPITS_AVAILABLE.intValue())))
            .andExpect(jsonPath("$.[*].microscopitsRequired").value(hasItem(DEFAULT_MICROSCOPITS_REQUIRED.intValue())))
            .andExpect(jsonPath("$.[*].labTechsAvailable").value(hasItem(DEFAULT_LAB_TECHS_AVAILABLE.intValue())))
            .andExpect(jsonPath("$.[*].labTechsRequired").value(hasItem(DEFAULT_LAB_TECHS_REQUIRED.intValue())))
            .andExpect(jsonPath("$.[*].generalHandsAvailable").value(hasItem(DEFAULT_GENERAL_HANDS_AVAILABLE.intValue())))
            .andExpect(jsonPath("$.[*].generalHandsRequired").value(hasItem(DEFAULT_GENERAL_HANDS_REQUIRED.intValue())));
    }

    @Test
    @Transactional
    void getStaffCompiment() throws Exception {
        // Initialize the database
        staffCompimentRepository.saveAndFlush(staffCompiment);

        // Get the staffCompiment
        restStaffCompimentMockMvc
            .perform(get(ENTITY_API_URL_ID, staffCompiment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(staffCompiment.getId().intValue()))
            .andExpect(jsonPath("$.laboratoryId").value(DEFAULT_LABORATORY_ID))
            .andExpect(jsonPath("$.laboratory").value(DEFAULT_LABORATORY))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.scientistAvailable").value(DEFAULT_SCIENTIST_AVAILABLE.intValue()))
            .andExpect(jsonPath("$.scientistRequired").value(DEFAULT_SCIENTIST_REQUIRED.intValue()))
            .andExpect(jsonPath("$.microscopitsAvailable").value(DEFAULT_MICROSCOPITS_AVAILABLE.intValue()))
            .andExpect(jsonPath("$.microscopitsRequired").value(DEFAULT_MICROSCOPITS_REQUIRED.intValue()))
            .andExpect(jsonPath("$.labTechsAvailable").value(DEFAULT_LAB_TECHS_AVAILABLE.intValue()))
            .andExpect(jsonPath("$.labTechsRequired").value(DEFAULT_LAB_TECHS_REQUIRED.intValue()))
            .andExpect(jsonPath("$.generalHandsAvailable").value(DEFAULT_GENERAL_HANDS_AVAILABLE.intValue()))
            .andExpect(jsonPath("$.generalHandsRequired").value(DEFAULT_GENERAL_HANDS_REQUIRED.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingStaffCompiment() throws Exception {
        // Get the staffCompiment
        restStaffCompimentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStaffCompiment() throws Exception {
        // Initialize the database
        staffCompimentRepository.saveAndFlush(staffCompiment);

        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();

        // Update the staffCompiment
        StaffCompiment updatedStaffCompiment = staffCompimentRepository.findById(staffCompiment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedStaffCompiment are not directly saved in db
        em.detach(updatedStaffCompiment);
        updatedStaffCompiment
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .scientistAvailable(UPDATED_SCIENTIST_AVAILABLE)
            .scientistRequired(UPDATED_SCIENTIST_REQUIRED)
            .microscopitsAvailable(UPDATED_MICROSCOPITS_AVAILABLE)
            .microscopitsRequired(UPDATED_MICROSCOPITS_REQUIRED)
            .labTechsAvailable(UPDATED_LAB_TECHS_AVAILABLE)
            .labTechsRequired(UPDATED_LAB_TECHS_REQUIRED)
            .generalHandsAvailable(UPDATED_GENERAL_HANDS_AVAILABLE)
            .generalHandsRequired(UPDATED_GENERAL_HANDS_REQUIRED);

        restStaffCompimentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStaffCompiment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedStaffCompiment))
            )
            .andExpect(status().isOk());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
        StaffCompiment testStaffCompiment = staffCompimentList.get(staffCompimentList.size() - 1);
        assertThat(testStaffCompiment.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testStaffCompiment.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testStaffCompiment.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testStaffCompiment.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testStaffCompiment.getScientistAvailable()).isEqualTo(UPDATED_SCIENTIST_AVAILABLE);
        assertThat(testStaffCompiment.getScientistRequired()).isEqualTo(UPDATED_SCIENTIST_REQUIRED);
        assertThat(testStaffCompiment.getMicroscopitsAvailable()).isEqualTo(UPDATED_MICROSCOPITS_AVAILABLE);
        assertThat(testStaffCompiment.getMicroscopitsRequired()).isEqualTo(UPDATED_MICROSCOPITS_REQUIRED);
        assertThat(testStaffCompiment.getLabTechsAvailable()).isEqualTo(UPDATED_LAB_TECHS_AVAILABLE);
        assertThat(testStaffCompiment.getLabTechsRequired()).isEqualTo(UPDATED_LAB_TECHS_REQUIRED);
        assertThat(testStaffCompiment.getGeneralHandsAvailable()).isEqualTo(UPDATED_GENERAL_HANDS_AVAILABLE);
        assertThat(testStaffCompiment.getGeneralHandsRequired()).isEqualTo(UPDATED_GENERAL_HANDS_REQUIRED);
    }

    @Test
    @Transactional
    void putNonExistingStaffCompiment() throws Exception {
        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();
        staffCompiment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffCompimentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, staffCompiment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isBadRequest());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStaffCompiment() throws Exception {
        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();
        staffCompiment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffCompimentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isBadRequest());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStaffCompiment() throws Exception {
        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();
        staffCompiment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffCompimentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(staffCompiment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStaffCompimentWithPatch() throws Exception {
        // Initialize the database
        staffCompimentRepository.saveAndFlush(staffCompiment);

        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();

        // Update the staffCompiment using partial update
        StaffCompiment partialUpdatedStaffCompiment = new StaffCompiment();
        partialUpdatedStaffCompiment.setId(staffCompiment.getId());

        partialUpdatedStaffCompiment
            .laboratoryId(UPDATED_LABORATORY_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .scientistRequired(UPDATED_SCIENTIST_REQUIRED)
            .microscopitsAvailable(UPDATED_MICROSCOPITS_AVAILABLE)
            .microscopitsRequired(UPDATED_MICROSCOPITS_REQUIRED)
            .labTechsRequired(UPDATED_LAB_TECHS_REQUIRED)
            .generalHandsAvailable(UPDATED_GENERAL_HANDS_AVAILABLE);

        restStaffCompimentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStaffCompiment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStaffCompiment))
            )
            .andExpect(status().isOk());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
        StaffCompiment testStaffCompiment = staffCompimentList.get(staffCompimentList.size() - 1);
        assertThat(testStaffCompiment.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testStaffCompiment.getLaboratory()).isEqualTo(DEFAULT_LABORATORY);
        assertThat(testStaffCompiment.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testStaffCompiment.getDepartment()).isEqualTo(DEFAULT_DEPARTMENT);
        assertThat(testStaffCompiment.getScientistAvailable()).isEqualTo(DEFAULT_SCIENTIST_AVAILABLE);
        assertThat(testStaffCompiment.getScientistRequired()).isEqualTo(UPDATED_SCIENTIST_REQUIRED);
        assertThat(testStaffCompiment.getMicroscopitsAvailable()).isEqualTo(UPDATED_MICROSCOPITS_AVAILABLE);
        assertThat(testStaffCompiment.getMicroscopitsRequired()).isEqualTo(UPDATED_MICROSCOPITS_REQUIRED);
        assertThat(testStaffCompiment.getLabTechsAvailable()).isEqualTo(DEFAULT_LAB_TECHS_AVAILABLE);
        assertThat(testStaffCompiment.getLabTechsRequired()).isEqualTo(UPDATED_LAB_TECHS_REQUIRED);
        assertThat(testStaffCompiment.getGeneralHandsAvailable()).isEqualTo(UPDATED_GENERAL_HANDS_AVAILABLE);
        assertThat(testStaffCompiment.getGeneralHandsRequired()).isEqualTo(DEFAULT_GENERAL_HANDS_REQUIRED);
    }

    @Test
    @Transactional
    void fullUpdateStaffCompimentWithPatch() throws Exception {
        // Initialize the database
        staffCompimentRepository.saveAndFlush(staffCompiment);

        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();

        // Update the staffCompiment using partial update
        StaffCompiment partialUpdatedStaffCompiment = new StaffCompiment();
        partialUpdatedStaffCompiment.setId(staffCompiment.getId());

        partialUpdatedStaffCompiment
            .laboratoryId(UPDATED_LABORATORY_ID)
            .laboratory(UPDATED_LABORATORY)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .department(UPDATED_DEPARTMENT)
            .scientistAvailable(UPDATED_SCIENTIST_AVAILABLE)
            .scientistRequired(UPDATED_SCIENTIST_REQUIRED)
            .microscopitsAvailable(UPDATED_MICROSCOPITS_AVAILABLE)
            .microscopitsRequired(UPDATED_MICROSCOPITS_REQUIRED)
            .labTechsAvailable(UPDATED_LAB_TECHS_AVAILABLE)
            .labTechsRequired(UPDATED_LAB_TECHS_REQUIRED)
            .generalHandsAvailable(UPDATED_GENERAL_HANDS_AVAILABLE)
            .generalHandsRequired(UPDATED_GENERAL_HANDS_REQUIRED);

        restStaffCompimentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStaffCompiment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStaffCompiment))
            )
            .andExpect(status().isOk());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
        StaffCompiment testStaffCompiment = staffCompimentList.get(staffCompimentList.size() - 1);
        assertThat(testStaffCompiment.getLaboratoryId()).isEqualTo(UPDATED_LABORATORY_ID);
        assertThat(testStaffCompiment.getLaboratory()).isEqualTo(UPDATED_LABORATORY);
        assertThat(testStaffCompiment.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testStaffCompiment.getDepartment()).isEqualTo(UPDATED_DEPARTMENT);
        assertThat(testStaffCompiment.getScientistAvailable()).isEqualTo(UPDATED_SCIENTIST_AVAILABLE);
        assertThat(testStaffCompiment.getScientistRequired()).isEqualTo(UPDATED_SCIENTIST_REQUIRED);
        assertThat(testStaffCompiment.getMicroscopitsAvailable()).isEqualTo(UPDATED_MICROSCOPITS_AVAILABLE);
        assertThat(testStaffCompiment.getMicroscopitsRequired()).isEqualTo(UPDATED_MICROSCOPITS_REQUIRED);
        assertThat(testStaffCompiment.getLabTechsAvailable()).isEqualTo(UPDATED_LAB_TECHS_AVAILABLE);
        assertThat(testStaffCompiment.getLabTechsRequired()).isEqualTo(UPDATED_LAB_TECHS_REQUIRED);
        assertThat(testStaffCompiment.getGeneralHandsAvailable()).isEqualTo(UPDATED_GENERAL_HANDS_AVAILABLE);
        assertThat(testStaffCompiment.getGeneralHandsRequired()).isEqualTo(UPDATED_GENERAL_HANDS_REQUIRED);
    }

    @Test
    @Transactional
    void patchNonExistingStaffCompiment() throws Exception {
        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();
        staffCompiment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffCompimentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, staffCompiment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isBadRequest());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStaffCompiment() throws Exception {
        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();
        staffCompiment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffCompimentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isBadRequest());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStaffCompiment() throws Exception {
        int databaseSizeBeforeUpdate = staffCompimentRepository.findAll().size();
        staffCompiment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffCompimentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(staffCompiment))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StaffCompiment in the database
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStaffCompiment() throws Exception {
        // Initialize the database
        staffCompimentRepository.saveAndFlush(staffCompiment);

        int databaseSizeBeforeDelete = staffCompimentRepository.findAll().size();

        // Delete the staffCompiment
        restStaffCompimentMockMvc
            .perform(delete(ENTITY_API_URL_ID, staffCompiment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StaffCompiment> staffCompimentList = staffCompimentRepository.findAll();
        assertThat(staffCompimentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
