package org.demo.javanais.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JavanaisServiceTest {

    @Autowired
    private JavanaisService javanaisService;

    @ParameterizedTest
    @CsvSource({
            "train, travain",
            "allumettes, avallavumavettaves",
            "aaa, avaaa",
            "bonjour, bavonjavour"
    })
    void calculerJavanais(String valeur, String valeurAttendue) {
        // WHEN
        String valeurActuelle = javanaisService.calculerJavanais(valeur);
        // THEN
        assertThat(valeurActuelle).isEqualTo(valeurAttendue);
    }
}