package com.amsdams.jh.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amsdams.jh.domain.Geocity;
import com.amsdams.jh.repository.GeocityRepository;
import com.amsdams.jh.repository.search.GeocitySearchRepository;
import com.amsdams.jh.service.MyGeocityService;
import com.amsdams.jh.service.dto.GeocityDTO;
import com.amsdams.jh.service.mapper.GeocityMapper;

@Service("myGeocityService")
@Transactional
public class MyGeocityServiceImpl extends GeocityServiceImpl implements MyGeocityService {

	private final Logger log = LoggerFactory.getLogger(GeocityServiceImpl.class);

	private final GeocityRepository geocityRepository;

	private final GeocityMapper geocityMapper;

	private final GeocitySearchRepository geocitySearchRepository;

	public MyGeocityServiceImpl(GeocityRepository geocityRepository, GeocityMapper geocityMapper,
			GeocitySearchRepository geocitySearchRepository) {
		super(geocityRepository, geocityMapper, geocitySearchRepository);

		this.geocityRepository = geocityRepository;
		this.geocityMapper = geocityMapper;
		this.geocitySearchRepository = geocitySearchRepository;
	}

	@Override
	public List<GeocityDTO> saveAll(List<GeocityDTO> geocityDTOs) {

		log.debug("Request to saveAll Geocitys : {}", geocityDTOs);
		List<Geocity> geocitys = geocityMapper.toEntity(geocityDTOs);
		geocitys = geocityRepository.saveAll(geocitys);
		List<GeocityDTO> result = geocityMapper.toDto(geocitys);

		return result;
	}

}
