package org.demo.javanais.domain;

import org.apache.logging.log4j.util.Strings;
import org.demo.javanais.domain.exception.ControleJavanaisException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JavanaisService {

    public String calculerFrancais(String phrase, String syllabesJavanais) throws Exception {
        String syllabes = recupererSyllabes(syllabesJavanais);

        String phraseEnFrancais = phrase.replace(syllabes, Strings.EMPTY);

        controlerFormatJavanais(phrase, phraseEnFrancais, syllabes);

        return phraseEnFrancais;
    }

    public String calculerJavanais(String phrase, String syllabesJavanais) {
        StringBuilder phraseEnJavanais = new StringBuilder();
        String syllabes = recupererSyllabes(syllabesJavanais);

        for (int indice = 0; indice < phrase.length(); indice++) {
            char caractere = phrase.charAt(indice);

            if (estConsonne(caractere)) {
                phraseEnJavanais.append(caractere);
            } else {
                int indexConsonne = recupererIndiceProchaineConsonne(indice, phrase);
                phraseEnJavanais.append(syllabes);
                phraseEnJavanais.append(phrase, indice, indexConsonne + 1);
                indice = indexConsonne;
            }
        }
        return phraseEnJavanais.toString();
    }

    private int recupererIndiceProchaineConsonne(int indiceDebut, String phrase) {
        int taille = phrase.length();
        int indiceDernierCaractere = taille - 1;

        for (int indice = indiceDebut; indice < taille; indice++) {
            if (estConsonne(phrase.charAt(indice))) {
                return indice;
            }
        }
        return indiceDernierCaractere;
    }

    private boolean estConsonne(char caracter) {
        return !String.valueOf(caracter).matches("[aeiou]");
    }

    private void controlerFormatJavanais(String phraseActuelle, String phraseEnFrancais, String syllabes) throws ControleJavanaisException {
        String phraseEnJavanais = calculerJavanais(phraseEnFrancais, syllabes);

        if(!Objects.equals(phraseActuelle, phraseEnJavanais)) {
            String erreur = String.format("Le mot/La phrase %s n'est pas au format javanais", phraseActuelle);
            throw new ControleJavanaisException(erreur);
        }
    }
    private String recupererSyllabes(String syllabes) {
        return Strings.isBlank(syllabes) ? "av" : syllabes;
    }
}
