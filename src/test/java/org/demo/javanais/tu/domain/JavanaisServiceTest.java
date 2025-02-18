package org.demo.javanais.tu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = JavanaisService.class)
class JavanaisServiceTest {

    @Autowired
    private JavanaisService javanaisService;

    @ParameterizedTest
    @MethodSource("valeursJavanais")
    @DisplayName("Teste que la méthode 'calculerJavanais' retourne les phrases/les mots en format javanais")
    void calculerJavanais(String valeur, String valeurAttendue) {
        // WHEN
        String javanais = javanaisService.calculerJavanais(valeur);
        // THEN
        assertThat(javanais).isEqualTo(valeurAttendue);
    }

    @ParameterizedTest
    @MethodSource("valeursJavanais")
    @DisplayName("Teste que la méthode 'calculerFrancais' retourne les phrases/les mots en format français")
    void calculerFrancais(String valeurAttendue, String valeur) {
        // WHEN
        String francais = javanaisService.calculerFrancais(valeur);
        // THEN
        assertThat(francais).isEqualTo(valeurAttendue);
    }

    public static Stream<Arguments> valeursJavanais() {
        return Stream.of(
                Arguments.of("au", "avau"),
                Arguments.of("moyen", "mavoyen"),
                Arguments.of("chante", "chavantave"),
                Arguments.of("train", "travain"),
                Arguments.of("bonjour", "bavonjavour"),
                Arguments.of("exemple", "avexavemplave"),
                Arguments.of("allumettes", "avallavumavettaves"),
                Arguments.of("avril", "avavravil"),
                Arguments.of("avoir", "avavavoir"),
                Arguments.of("cadavre", "cavadavavrave"),
                Arguments.of("je suis ici", "jave savuis avicavi")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "av, av",
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