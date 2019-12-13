package com.amsdams.jh.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link GeocitySearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class GeocitySearchRepositoryMockConfiguration {

    @MockBean
    private GeocitySearchRepository mockGeocitySearchRepository;

}
