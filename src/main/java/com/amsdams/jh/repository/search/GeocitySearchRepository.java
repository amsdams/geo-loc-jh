package com.amsdams.jh.repository.search;
import com.amsdams.jh.domain.Geocity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Geocity} entity.
 */
public interface GeocitySearchRepository extends ElasticsearchRepository<Geocity, Long> {
}
