package org.demo.javanais.tu.domain;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JavanaisService {

    public static final String JAVANAIS_SYLLABE = "av";
    public static final String REGEX_JAVANAIS_AVEC_VOYELLE = "av([aeiouAEIOU])";
    public static final String REGEX_VOYELLE_DEBUT_PHRASE = "^[aeiouAEIOU].*";
    public static final String REGEX_CONSONNE_AVEC_VOYELLES = "([^aeiouAEIOU])([aeiouAEIOU]+)";

    public String calculerFrancais(String phrase) {
        String phraseFrancais = recupererPhraseEnFrancais(phrase);
        String phraseEnJavanais = calculerJavanais(phraseFrancais);

        return Objects.equals(phrase, phraseEnJavanais) ? phraseFrancais : phrase;
    }

    public String calculerJavanais(String phrase) {
        String phraseEnJavanais = recupererPhraseEnJavanais(phrase);

        if (phraseEnJavanais.matches(REGEX_VOYELLE_DEBUT_PHRASE)) {
            phraseEnJavanais = JAVANAIS_SYLLABE.concat(phraseEnJavanais);
        }
        return phraseEnJavanais;
    }

    private String recupererPhraseEnJavanais(String phrase) {
        String phraseJavanais = phrase;

        Pattern pattern = Pattern.compile(REGEX_CONSONNE_AVEC_VOYELLES);
        Matcher matcher = pattern.matcher(phrase);

        while (matcher.find()) {
            String syllabe = matcher.group(0);
            String consonne = matcher.group(1);
            String voyelles = matcher.group(2);

            String syllabeJavanais = consonne
                    .concat(JAVANAIS_SYLLABE)
                    .concat(voyelles);

            phraseJavanais = phraseJavanais.replace(syllabe, syllabeJavanais);
        }

        return phraseJavanais;
    }

    private String recupererPhraseEnFrancais(String phrase) {
        String phraseFrancais = phrase;

        Pattern pattern = Pattern.compile(REGEX_JAVANAIS_AVEC_VOYELLE);
        Matcher matcher = pattern.matcher(phrase);

        while (matcher.find()) {
            String syllabe = matcher.group(0);
            String voyelle = matcher.group(1);
            phraseFrancais = phraseFrancais.replace(syllabe, voyelle);
        }

        return phraseFrancais;
    }
}
