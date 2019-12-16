package com.amsdams.jh.service.impl;

import java.util.List;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Position;
import org.geolatte.geom.crs.CrsRegistry;
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

	@Override
	public List<GeocityDTO> findCitiesWithinCircle(float lat, float lon, int radius) {
		Position g2d = new G2D(lon, lat);
		Point point = new Point(g2d, CrsRegistry.getCoordinateReferenceSystemForEPSG(4326, null));
		float geoRadius = radius / 111.0f; // 111 is the standard distance, in kilometers, of a degree
		List<Geocity> geocitys = geocityRepository.findCityWithinCircle(point, geoRadius);
		List<GeocityDTO> result = geocityMapper.toDto(geocitys);
		return result;
	}

}
