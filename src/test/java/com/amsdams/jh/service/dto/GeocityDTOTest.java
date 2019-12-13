package com.amsdams.jh.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.amsdams.jh.web.rest.TestUtil;

public class GeocityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeocityDTO.class);
        GeocityDTO geocityDTO1 = new GeocityDTO();
        geocityDTO1.setId(1L);
        GeocityDTO geocityDTO2 = new GeocityDTO();
        assertThat(geocityDTO1).isNotEqualTo(geocityDTO2);
        geocityDTO2.setId(geocityDTO1.getId());
        assertThat(geocityDTO1).isEqualTo(geocityDTO2);
        geocityDTO2.setId(2L);
        assertThat(geocityDTO1).isNotEqualTo(geocityDTO2);
        geocityDTO1.setId(null);
        assertThat(geocityDTO1).isNotEqualTo(geocityDTO2);
    }
}
