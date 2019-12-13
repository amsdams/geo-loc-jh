package com.amsdams.jh.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amsdams.jh.service.GeocityService;
import com.amsdams.jh.service.dto.GeocityDTO;
import com.amsdams.jh.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.amsdams.jh.domain.Geocity}.
 */
@RestController
@RequestMapping("/api")
public class GeocityResource {

    private final Logger log = LoggerFactory.getLogger(GeocityResource.class);

    private static final String ENTITY_NAME = "geocity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeocityService geocityService;

    public GeocityResource(GeocityService geocityService) {
        this.geocityService = geocityService;
    }

    /**
     * {@code POST  /geocities} : Create a new geocity.
     *
     * @param geocityDTO the geocityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geocityDTO, or with status {@code 400 (Bad Request)} if the geocity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geocities")
    public ResponseEntity<GeocityDTO> createGeocity(@RequestBody GeocityDTO geocityDTO) throws URISyntaxException {
        log.debug("REST request to save Geocity : {}", geocityDTO);
        if (geocityDTO.getId() != null) {
            throw new BadRequestAlertException("A new geocity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeocityDTO result = geocityService.save(geocityDTO);
        return ResponseEntity.created(new URI("/api/geocities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geocities} : Updates an existing geocity.
     *
     * @param geocityDTO the geocityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geocityDTO,
     * or with status {@code 400 (Bad Request)} if the geocityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geocityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geocities")
    public ResponseEntity<GeocityDTO> updateGeocity(@RequestBody GeocityDTO geocityDTO) throws URISyntaxException {
        log.debug("REST request to update Geocity : {}", geocityDTO);
        if (geocityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeocityDTO result = geocityService.save(geocityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, geocityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geocities} : get all the geocities.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geocities in body.
     */
    @GetMapping("/geocities")
    public ResponseEntity<List<GeocityDTO>> getAllGeocities(Pageable pageable) {
        log.debug("REST request to get a page of Geocities");
        Page<GeocityDTO> page = geocityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /geocities/:id} : get the "id" geocity.
     *
     * @param id the id of the geocityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geocityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geocities/{id}")
    public ResponseEntity<GeocityDTO> getGeocity(@PathVariable Long id) {
        log.debug("REST request to get Geocity : {}", id);
        Optional<GeocityDTO> geocityDTO = geocityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geocityDTO);
    }

    /**
     * {@code DELETE  /geocities/:id} : delete the "id" geocity.
     *
     * @param id the id of the geocityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geocities/{id}")
    public ResponseEntity<Void> deleteGeocity(@PathVariable Long id) {
        log.debug("REST request to delete Geocity : {}", id);
        geocityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/geocities?query=:query} : search for the geocity corresponding
     * to the query.
     *
     * @param query the query of the geocity search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/geocities")
    public ResponseEntity<List<GeocityDTO>> searchGeocities(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Geocities for query {}", query);
        Page<GeocityDTO> page = geocityService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
