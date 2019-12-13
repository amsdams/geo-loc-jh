package com.amsdams.jh.web.rest;

import com.amsdams.jh.GeoLocJhApp;
import com.amsdams.jh.domain.Geocity;
import com.amsdams.jh.repository.GeocityRepository;
import com.amsdams.jh.repository.search.GeocitySearchRepository;
import com.amsdams.jh.service.GeocityService;
import com.amsdams.jh.service.dto.GeocityDTO;
import com.amsdams.jh.service.mapper.GeocityMapper;
import com.amsdams.jh.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.amsdams.jh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeocityResource} REST controller.
 */
@SpringBootTest(classes = GeoLocJhApp.class)
public class GeocityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASCIINAME = "AAAAAAAAAA";
    private static final String UPDATED_ASCIINAME = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATENAMES = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATENAMES = "BBBBBBBBBB";

    private static final Float DEFAULT_LOCATION = 1F;
    private static final Float UPDATED_LOCATION = 2F;

    private static final Double DEFAULT_LAT = 1D;
    private static final Double UPDATED_LAT = 2D;

    private static final Double DEFAULT_LON = 1D;
    private static final Double UPDATED_LON = 2D;

    private static final String DEFAULT_FEATURECLASS = "AAAAAAAAAA";
    private static final String UPDATED_FEATURECLASS = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURETYPE = "AAAAAAAAAA";
    private static final String UPDATED_FEATURETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRYCODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRYCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CC_2 = "AAAAAAAAAA";
    private static final String UPDATED_CC_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_1_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_1_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_2_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_2_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_3_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_3_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_4_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_4_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_POPULATION = 1;
    private static final Integer UPDATED_POPULATION = 2;

    private static final Integer DEFAULT_ELEVATION = 1;
    private static final Integer UPDATED_ELEVATION = 2;

    private static final String DEFAULT_DEM = "AAAAAAAAAA";
    private static final String UPDATED_DEM = "BBBBBBBBBB";

    @Autowired
    private GeocityRepository geocityRepository;

    @Autowired
    private GeocityMapper geocityMapper;

    @Autowired
    private GeocityService geocityService;

    /**
     * This repository is mocked in the com.amsdams.jh.repository.search test package.
     *
     * @see com.amsdams.jh.repository.search.GeocitySearchRepositoryMockConfiguration
     */
    @Autowired
    private GeocitySearchRepository mockGeocitySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGeocityMockMvc;

    private Geocity geocity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeocityResource geocityResource = new GeocityResource(geocityService);
        this.restGeocityMockMvc = MockMvcBuilders.standaloneSetup(geocityResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Geocity createEntity(EntityManager em) {
        Geocity geocity = new Geocity()
            .name(DEFAULT_NAME)
            .asciiname(DEFAULT_ASCIINAME)
            .alternatenames(DEFAULT_ALTERNATENAMES)
            .location(DEFAULT_LOCATION)
            .lat(DEFAULT_LAT)
            .lon(DEFAULT_LON)
            .featureclass(DEFAULT_FEATURECLASS)
            .featuretype(DEFAULT_FEATURETYPE)
            .countrycode(DEFAULT_COUNTRYCODE)
            .cc2(DEFAULT_CC_2)
            .admin1code(DEFAULT_ADMIN_1_CODE)
            .admin2code(DEFAULT_ADMIN_2_CODE)
            .admin3code(DEFAULT_ADMIN_3_CODE)
            .admin4code(DEFAULT_ADMIN_4_CODE)
            .population(DEFAULT_POPULATION)
            .elevation(DEFAULT_ELEVATION)
            .dem(DEFAULT_DEM);
        return geocity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Geocity createUpdatedEntity(EntityManager em) {
        Geocity geocity = new Geocity()
            .name(UPDATED_NAME)
            .asciiname(UPDATED_ASCIINAME)
            .alternatenames(UPDATED_ALTERNATENAMES)
            .location(UPDATED_LOCATION)
            .lat(UPDATED_LAT)
            .lon(UPDATED_LON)
            .featureclass(UPDATED_FEATURECLASS)
            .featuretype(UPDATED_FEATURETYPE)
            .countrycode(UPDATED_COUNTRYCODE)
            .cc2(UPDATED_CC_2)
            .admin1code(UPDATED_ADMIN_1_CODE)
            .admin2code(UPDATED_ADMIN_2_CODE)
            .admin3code(UPDATED_ADMIN_3_CODE)
            .admin4code(UPDATED_ADMIN_4_CODE)
            .population(UPDATED_POPULATION)
            .elevation(UPDATED_ELEVATION)
            .dem(UPDATED_DEM);
        return geocity;
    }

    @BeforeEach
    public void initTest() {
        geocity = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeocity() throws Exception {
        int databaseSizeBeforeCreate = geocityRepository.findAll().size();

        // Create the Geocity
        GeocityDTO geocityDTO = geocityMapper.toDto(geocity);
        restGeocityMockMvc.perform(post("/api/geocities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geocityDTO)))
            .andExpect(status().isCreated());

        // Validate the Geocity in the database
        List<Geocity> geocityList = geocityRepository.findAll();
        assertThat(geocityList).hasSize(databaseSizeBeforeCreate + 1);
        Geocity testGeocity = geocityList.get(geocityList.size() - 1);
        assertThat(testGeocity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGeocity.getAsciiname()).isEqualTo(DEFAULT_ASCIINAME);
        assertThat(testGeocity.getAlternatenames()).isEqualTo(DEFAULT_ALTERNATENAMES);
        assertThat(testGeocity.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testGeocity.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testGeocity.getLon()).isEqualTo(DEFAULT_LON);
        assertThat(testGeocity.getFeatureclass()).isEqualTo(DEFAULT_FEATURECLASS);
        assertThat(testGeocity.getFeaturetype()).isEqualTo(DEFAULT_FEATURETYPE);
        assertThat(testGeocity.getCountrycode()).isEqualTo(DEFAULT_COUNTRYCODE);
        assertThat(testGeocity.getCc2()).isEqualTo(DEFAULT_CC_2);
        assertThat(testGeocity.getAdmin1code()).isEqualTo(DEFAULT_ADMIN_1_CODE);
        assertThat(testGeocity.getAdmin2code()).isEqualTo(DEFAULT_ADMIN_2_CODE);
        assertThat(testGeocity.getAdmin3code()).isEqualTo(DEFAULT_ADMIN_3_CODE);
        assertThat(testGeocity.getAdmin4code()).isEqualTo(DEFAULT_ADMIN_4_CODE);
        assertThat(testGeocity.getPopulation()).isEqualTo(DEFAULT_POPULATION);
        assertThat(testGeocity.getElevation()).isEqualTo(DEFAULT_ELEVATION);
        assertThat(testGeocity.getDem()).isEqualTo(DEFAULT_DEM);

        // Validate the Geocity in Elasticsearch
        verify(mockGeocitySearchRepository, times(1)).save(testGeocity);
    }

    @Test
    @Transactional
    public void createGeocityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geocityRepository.findAll().size();

        // Create the Geocity with an existing ID
        geocity.setId(1L);
        GeocityDTO geocityDTO = geocityMapper.toDto(geocity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeocityMockMvc.perform(post("/api/geocities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geocityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Geocity in the database
        List<Geocity> geocityList = geocityRepository.findAll();
        assertThat(geocityList).hasSize(databaseSizeBeforeCreate);

        // Validate the Geocity in Elasticsearch
        verify(mockGeocitySearchRepository, times(0)).save(geocity);
    }


    @Test
    @Transactional
    public void getAllGeocities() throws Exception {
        // Initialize the database
        geocityRepository.saveAndFlush(geocity);

        // Get all the geocityList
        restGeocityMockMvc.perform(get("/api/geocities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geocity.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].asciiname").value(hasItem(DEFAULT_ASCIINAME)))
            .andExpect(jsonPath("$.[*].alternatenames").value(hasItem(DEFAULT_ALTERNATENAMES)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.doubleValue())))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.doubleValue())))
            .andExpect(jsonPath("$.[*].lon").value(hasItem(DEFAULT_LON.doubleValue())))
            .andExpect(jsonPath("$.[*].featureclass").value(hasItem(DEFAULT_FEATURECLASS)))
            .andExpect(jsonPath("$.[*].featuretype").value(hasItem(DEFAULT_FEATURETYPE)))
            .andExpect(jsonPath("$.[*].countrycode").value(hasItem(DEFAULT_COUNTRYCODE)))
            .andExpect(jsonPath("$.[*].cc2").value(hasItem(DEFAULT_CC_2)))
            .andExpect(jsonPath("$.[*].admin1code").value(hasItem(DEFAULT_ADMIN_1_CODE)))
            .andExpect(jsonPath("$.[*].admin2code").value(hasItem(DEFAULT_ADMIN_2_CODE)))
            .andExpect(jsonPath("$.[*].admin3code").value(hasItem(DEFAULT_ADMIN_3_CODE)))
            .andExpect(jsonPath("$.[*].admin4code").value(hasItem(DEFAULT_ADMIN_4_CODE)))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION)))
            .andExpect(jsonPath("$.[*].elevation").value(hasItem(DEFAULT_ELEVATION)))
            .andExpect(jsonPath("$.[*].dem").value(hasItem(DEFAULT_DEM)));
    }
    
    @Test
    @Transactional
    public void getGeocity() throws Exception {
        // Initialize the database
        geocityRepository.saveAndFlush(geocity);

        // Get the geocity
        restGeocityMockMvc.perform(get("/api/geocities/{id}", geocity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(geocity.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.asciiname").value(DEFAULT_ASCIINAME))
            .andExpect(jsonPath("$.alternatenames").value(DEFAULT_ALTERNATENAMES))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.doubleValue()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.doubleValue()))
            .andExpect(jsonPath("$.lon").value(DEFAULT_LON.doubleValue()))
            .andExpect(jsonPath("$.featureclass").value(DEFAULT_FEATURECLASS))
            .andExpect(jsonPath("$.featuretype").value(DEFAULT_FEATURETYPE))
            .andExpect(jsonPath("$.countrycode").value(DEFAULT_COUNTRYCODE))
            .andExpect(jsonPath("$.cc2").value(DEFAULT_CC_2))
            .andExpect(jsonPath("$.admin1code").value(DEFAULT_ADMIN_1_CODE))
            .andExpect(jsonPath("$.admin2code").value(DEFAULT_ADMIN_2_CODE))
            .andExpect(jsonPath("$.admin3code").value(DEFAULT_ADMIN_3_CODE))
            .andExpect(jsonPath("$.admin4code").value(DEFAULT_ADMIN_4_CODE))
            .andExpect(jsonPath("$.population").value(DEFAULT_POPULATION))
            .andExpect(jsonPath("$.elevation").value(DEFAULT_ELEVATION))
            .andExpect(jsonPath("$.dem").value(DEFAULT_DEM));
    }

    @Test
    @Transactional
    public void getNonExistingGeocity() throws Exception {
        // Get the geocity
        restGeocityMockMvc.perform(get("/api/geocities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeocity() throws Exception {
        // Initialize the database
        geocityRepository.saveAndFlush(geocity);

        int databaseSizeBeforeUpdate = geocityRepository.findAll().size();

        // Update the geocity
        Geocity updatedGeocity = geocityRepository.findById(geocity.getId()).get();
        // Disconnect from session so that the updates on updatedGeocity are not directly saved in db
        em.detach(updatedGeocity);
        updatedGeocity
            .name(UPDATED_NAME)
            .asciiname(UPDATED_ASCIINAME)
            .alternatenames(UPDATED_ALTERNATENAMES)
            .location(UPDATED_LOCATION)
            .lat(UPDATED_LAT)
            .lon(UPDATED_LON)
            .featureclass(UPDATED_FEATURECLASS)
            .featuretype(UPDATED_FEATURETYPE)
            .countrycode(UPDATED_COUNTRYCODE)
            .cc2(UPDATED_CC_2)
            .admin1code(UPDATED_ADMIN_1_CODE)
            .admin2code(UPDATED_ADMIN_2_CODE)
            .admin3code(UPDATED_ADMIN_3_CODE)
            .admin4code(UPDATED_ADMIN_4_CODE)
            .population(UPDATED_POPULATION)
            .elevation(UPDATED_ELEVATION)
            .dem(UPDATED_DEM);
        GeocityDTO geocityDTO = geocityMapper.toDto(updatedGeocity);

        restGeocityMockMvc.perform(put("/api/geocities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geocityDTO)))
            .andExpect(status().isOk());

        // Validate the Geocity in the database
        List<Geocity> geocityList = geocityRepository.findAll();
        assertThat(geocityList).hasSize(databaseSizeBeforeUpdate);
        Geocity testGeocity = geocityList.get(geocityList.size() - 1);
        assertThat(testGeocity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGeocity.getAsciiname()).isEqualTo(UPDATED_ASCIINAME);
        assertThat(testGeocity.getAlternatenames()).isEqualTo(UPDATED_ALTERNATENAMES);
        assertThat(testGeocity.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testGeocity.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testGeocity.getLon()).isEqualTo(UPDATED_LON);
        assertThat(testGeocity.getFeatureclass()).isEqualTo(UPDATED_FEATURECLASS);
        assertThat(testGeocity.getFeaturetype()).isEqualTo(UPDATED_FEATURETYPE);
        assertThat(testGeocity.getCountrycode()).isEqualTo(UPDATED_COUNTRYCODE);
        assertThat(testGeocity.getCc2()).isEqualTo(UPDATED_CC_2);
        assertThat(testGeocity.getAdmin1code()).isEqualTo(UPDATED_ADMIN_1_CODE);
        assertThat(testGeocity.getAdmin2code()).isEqualTo(UPDATED_ADMIN_2_CODE);
        assertThat(testGeocity.getAdmin3code()).isEqualTo(UPDATED_ADMIN_3_CODE);
        assertThat(testGeocity.getAdmin4code()).isEqualTo(UPDATED_ADMIN_4_CODE);
        assertThat(testGeocity.getPopulation()).isEqualTo(UPDATED_POPULATION);
        assertThat(testGeocity.getElevation()).isEqualTo(UPDATED_ELEVATION);
        assertThat(testGeocity.getDem()).isEqualTo(UPDATED_DEM);

        // Validate the Geocity in Elasticsearch
        verify(mockGeocitySearchRepository, times(1)).save(testGeocity);
    }

    @Test
    @Transactional
    public void updateNonExistingGeocity() throws Exception {
        int databaseSizeBeforeUpdate = geocityRepository.findAll().size();

        // Create the Geocity
        GeocityDTO geocityDTO = geocityMapper.toDto(geocity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeocityMockMvc.perform(put("/api/geocities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geocityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Geocity in the database
        List<Geocity> geocityList = geocityRepository.findAll();
        assertThat(geocityList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Geocity in Elasticsearch
        verify(mockGeocitySearchRepository, times(0)).save(geocity);
    }

    @Test
    @Transactional
    public void deleteGeocity() throws Exception {
        // Initialize the database
        geocityRepository.saveAndFlush(geocity);

        int databaseSizeBeforeDelete = geocityRepository.findAll().size();

        // Delete the geocity
        restGeocityMockMvc.perform(delete("/api/geocities/{id}", geocity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Geocity> geocityList = geocityRepository.findAll();
        assertThat(geocityList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Geocity in Elasticsearch
        verify(mockGeocitySearchRepository, times(1)).deleteById(geocity.getId());
    }

    @Test
    @Transactional
    public void searchGeocity() throws Exception {
        // Initialize the database
        geocityRepository.saveAndFlush(geocity);
        when(mockGeocitySearchRepository.search(queryStringQuery("id:" + geocity.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(geocity), PageRequest.of(0, 1), 1));
        // Search the geocity
        restGeocityMockMvc.perform(get("/api/_search/geocities?query=id:" + geocity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geocity.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].asciiname").value(hasItem(DEFAULT_ASCIINAME)))
            .andExpect(jsonPath("$.[*].alternatenames").value(hasItem(DEFAULT_ALTERNATENAMES)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.doubleValue())))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.doubleValue())))
            .andExpect(jsonPath("$.[*].lon").value(hasItem(DEFAULT_LON.doubleValue())))
            .andExpect(jsonPath("$.[*].featureclass").value(hasItem(DEFAULT_FEATURECLASS)))
            .andExpect(jsonPath("$.[*].featuretype").value(hasItem(DEFAULT_FEATURETYPE)))
            .andExpect(jsonPath("$.[*].countrycode").value(hasItem(DEFAULT_COUNTRYCODE)))
            .andExpect(jsonPath("$.[*].cc2").value(hasItem(DEFAULT_CC_2)))
            .andExpect(jsonPath("$.[*].admin1code").value(hasItem(DEFAULT_ADMIN_1_CODE)))
            .andExpect(jsonPath("$.[*].admin2code").value(hasItem(DEFAULT_ADMIN_2_CODE)))
            .andExpect(jsonPath("$.[*].admin3code").value(hasItem(DEFAULT_ADMIN_3_CODE)))
            .andExpect(jsonPath("$.[*].admin4code").value(hasItem(DEFAULT_ADMIN_4_CODE)))
            .andExpect(jsonPath("$.[*].population").value(hasItem(DEFAULT_POPULATION)))
            .andExpect(jsonPath("$.[*].elevation").value(hasItem(DEFAULT_ELEVATION)))
            .andExpect(jsonPath("$.[*].dem").value(hasItem(DEFAULT_DEM)));
    }
}
