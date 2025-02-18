package org.demo.javanais.domain;

import lombok.SneakyThrows;
import org.demo.javanais.domain.exception.ControleJavanaisException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
            "je suis ici, jave savuis avicavi"
    })
    void calculerJavanais(String valeur, String valeurAttendue) {
        // WHEN
        String javanais = javanaisService.calculerJavanais(valeur, null);
        // THEN
        assertThat(javanais).isEqualTo(valeurAttendue);
    }

    @ParameterizedTest
    @CsvSource({
            "avau, au",
            "chavantave, chante",
            "travain,train",
            "bavonjavour, bonjour",
            "avexavemplave, exemple",
            "avallavumavettaves, allumettes",
            "jave savuis avicavi, je suis ici"
    })
    @SneakyThrows
    void calculerFrancais(String valeur, String valeurAttendue) {
        // WHEN
        String francais = javanaisService.calculerFrancais(valeur, null);
        // THEN
        assertThat(francais).isEqualTo(valeurAttendue);
    }

    @ParameterizedTest
    @CsvSource({"aaa", "allumettes", "je suis ici"})
    @SneakyThrows
    void calculerFrancaisException(String valeur) {
        // GIVEN
        String messageErreur = String.format("Le mot/La phrase %s n'est pas au format javanais", valeur);
        // WHEN && THEN
        assertThatExceptionOfType(ControleJavanaisException.class)
                .isThrownBy(() -> javanaisService.calculerFrancais(valeur, null))
                .withMessage(messageErreur);
    }
}