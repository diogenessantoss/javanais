package org.demo.javanais.tu.domain;

import org.junit.jupiter.api.DisplayName;
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
            "au, avau",
            "chante, chavantave",
            "train, travain",
            "bonjour, bavonjavour",
            "exemple, avexavemplave",
            "allumettes, avallavumavettaves",
            "avril, avavravil",
            "avoir, avavavoir",
            "cadavre, cavadavavrave",
            "je suis ici, jave savuis avicavi"
    })
    @DisplayName("Teste que la méthode 'calculerJavanais' retourne les phrases/les mots en format javanais")
    void calculerJavanais(String valeur, String valeurAttendue) {
        // WHEN
        String javanais = javanaisService.calculerJavanais(valeur);
        // THEN
        assertThat(javanais).isEqualTo(valeurAttendue);
    }

    @ParameterizedTest
    @CsvSource({
            "avau, au",
            "chavantave, chante",
            "travain, train",
            "bavonjavour, bonjour",
            "avexavemplave, exemple",
            "avallavumavettaves, allumettes",
            "jave savuis avicavi, je suis ici",
            "avavravil, avril",
            "avavavoir, avoir",
            "cavadavavrave, cadavre",
            "avril, avril",
            "avocat, avocat"
    })
    @DisplayName("Teste que la méthode 'calculerFrancais' retourne les phrases/les mots en format français")
    void calculerFrancais(String valeur, String valeurAttendue) {
        // WHEN
        String francais = javanaisService.calculerFrancais(valeur);
        // THEN
        assertThat(francais).isEqualTo(valeurAttendue);
    }

    @ParameterizedTest
    @CsvSource({
            "avril, avril",
            "avocat, avocat",
            "cadavre, cadavre",
            "France Travail, France Travail"
    })
    @DisplayName("Teste que la méthode 'calculerFrancais' retourne la même phrase/mot lorsqu'on n'a pas une phrase/mot en format javanais")
    void calculerFrancaisAvecNonJavanais(String valeur, String valeurAttendue) {
        // WHEN
        String francais = javanaisService.calculerFrancais(valeur);
        // THEN
        assertThat(francais).isEqualTo(valeurAttendue);
    }
}