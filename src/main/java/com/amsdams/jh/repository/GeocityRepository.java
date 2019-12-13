package com.amsdams.jh.repository;
import com.amsdams.jh.domain.Geocity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Geocity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeocityRepository extends JpaRepository<Geocity, Long> {

}
