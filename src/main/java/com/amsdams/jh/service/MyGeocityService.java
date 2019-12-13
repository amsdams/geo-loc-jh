package com.amsdams.jh.service;


import java.util.List;

import com.amsdams.jh.service.dto.GeocityDTO;

public interface MyGeocityService extends GeocityService {

	List<GeocityDTO> saveAll(List<GeocityDTO> geocityDTOs);
}
