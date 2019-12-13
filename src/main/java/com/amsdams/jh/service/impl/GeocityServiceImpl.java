package com.amsdams.jh.service.impl;

import com.amsdams.jh.service.GeocityService;
import com.amsdams.jh.domain.Geocity;
import com.amsdams.jh.repository.GeocityRepository;
import com.amsdams.jh.repository.search.GeocitySearchRepository;
import com.amsdams.jh.service.dto.GeocityDTO;
import com.amsdams.jh.service.mapper.GeocityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Geocity}.
 */
@Service
@Primary
@Transactional
public class GeocityServiceImpl implements GeocityService {

    private final Logger log = LoggerFactory.getLogger(GeocityServiceImpl.class);

    private final GeocityRepository geocityRepository;

    private final GeocityMapper geocityMapper;

    private final GeocitySearchRepository geocitySearchRepository;

    public GeocityServiceImpl(GeocityRepository geocityRepository, GeocityMapper geocityMapper, GeocitySearchRepository geocitySearchRepository) {
        this.geocityRepository = geocityRepository;
        this.geocityMapper = geocityMapper;
        this.geocitySearchRepository = geocitySearchRepository;
    }

    /**
     * Save a geocity.
     *
     * @param geocityDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GeocityDTO save(GeocityDTO geocityDTO) {
        log.debug("Request to save Geocity : {}", geocityDTO);
        Geocity geocity = geocityMapper.toEntity(geocityDTO);
        geocity = geocityRepository.save(geocity);
        GeocityDTO result = geocityMapper.toDto(geocity);
        geocitySearchRepository.save(geocity);
        return result;
    }

    /**
     * Get all the geocities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GeocityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Geocities");
        return geocityRepository.findAll(pageable)
            .map(geocityMapper::toDto);
    }


    /**
     * Get one geocity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GeocityDTO> findOne(Long id) {
        log.debug("Request to get Geocity : {}", id);
        return geocityRepository.findById(id)
            .map(geocityMapper::toDto);
    }

    /**
     * Delete the geocity by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Geocity : {}", id);
        geocityRepository.deleteById(id);
        geocitySearchRepository.deleteById(id);
    }

    /**
     * Search for the geocity corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GeocityDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Geocities for query {}", query);
        return geocitySearchRepository.search(queryStringQuery(query), pageable)
            .map(geocityMapper::toDto);
    }
}
