package org.demo.javanais.tc;

import lombok.SneakyThrows;
import org.demo.javanais.domain.JavanaisService;
import org.demo.javanais.web.JavanaisController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(JavanaisController.class)
public class JavanaisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JavanaisService javanaisService;

    private static final String ENDPOINT_JAVANAIS = "/javanais/{valeur}";
    private static final String ENDPOINT_FRANCAIS = "/francais/{valeur}";

    @Test
    @SneakyThrows
    void recupererJavanais() {
        // GIVEN
        String valeur = "allumettes";
        String reponseAttendue = "avallavumavettaves";
        // WHEN
        when(javanaisService.calculerJavanais(valeur)).thenReturn(reponseAttendue);
        // THEN
        MockHttpServletResponse response = recupererReponse(ENDPOINT_JAVANAIS, valeur);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(reponseAttendue);
    }

    @Test
    @SneakyThrows
    void recupererFrancais() {
        // GIVEN
        String valeur = "avallavumavettaves";
        String reponseAttendue = "allumettes";
        // WHEN
        when(javanaisService.calculerFrancais(valeur)).thenReturn(reponseAttendue);
        // THEN
        MockHttpServletResponse response = recupererReponse(ENDPOINT_FRANCAIS, valeur);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(reponseAttendue);
    }

    private MockHttpServletResponse recupererReponse(String endpoint, String valeur) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(endpoint, valeur))
                .andReturn()
                .getResponse();
    }
}
