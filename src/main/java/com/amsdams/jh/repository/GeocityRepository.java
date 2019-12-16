package com.amsdams.jh.repository;

import java.util.List;

import org.geolatte.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amsdams.jh.domain.Geocity;

/**
 * Spring Data repository for the Geocity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeocityRepository extends JpaRepository<Geocity, Long> {

	@Query("SELECT c FROM Geocity c WHERE dwithin(c.location, :point, :dist)=true")
	public List<Geocity> findCityWithinCircle(@Param("point") Geometry point, @Param("dist") float dist);

}
