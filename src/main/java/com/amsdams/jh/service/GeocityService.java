package com.amsdams.jh.service;

import com.amsdams.jh.service.dto.GeocityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.amsdams.jh.domain.Geocity}.
 */
public interface GeocityService {

    /**
     * Save a geocity.
     *
     * @param geocityDTO the entity to save.
     * @return the persisted entity.
     */
    GeocityDTO save(GeocityDTO geocityDTO);

    /**
     * Get all the geocities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GeocityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" geocity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeocityDTO> findOne(Long id);

    /**
     * Delete the "id" geocity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the geocity corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GeocityDTO> search(String query, Pageable pageable);
}
