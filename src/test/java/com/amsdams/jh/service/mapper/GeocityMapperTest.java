package com.amsdams.jh.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GeocityMapperTest {

    private GeocityMapper geocityMapper;

    @BeforeEach
    public void setUp() {
        geocityMapper = new GeocityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(geocityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geocityMapper.fromId(null)).isNull();
    }
}
