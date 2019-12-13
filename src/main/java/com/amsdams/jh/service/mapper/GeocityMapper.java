package com.amsdams.jh.service.mapper;

import com.amsdams.jh.domain.*;
import com.amsdams.jh.service.dto.GeocityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Geocity} and its DTO {@link GeocityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeocityMapper extends EntityMapper<GeocityDTO, Geocity> {



    default Geocity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Geocity geocity = new Geocity();
        geocity.setId(id);
        return geocity;
    }
}
