package com.amsdams.jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.amsdams.jh.web.rest.TestUtil;

public class GeocityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Geocity.class);
        Geocity geocity1 = new Geocity();
        geocity1.setId(1L);
        Geocity geocity2 = new Geocity();
        geocity2.setId(geocity1.getId());
        assertThat(geocity1).isEqualTo(geocity2);
        geocity2.setId(2L);
        assertThat(geocity1).isNotEqualTo(geocity2);
        geocity1.setId(null);
        assertThat(geocity1).isNotEqualTo(geocity2);
    }
}
