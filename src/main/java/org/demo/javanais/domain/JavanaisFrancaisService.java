package org.demo.javanais.domain;

import org.demo.javanais.domain.exception.ControleJavanaisException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JavanaisFrancaisService {

    public String calculerFrancais(String mot) throws Exception {
        String motEnFrancais = mot.replace("av", "");
        controlerFormatJavanais(mot, motEnFrancais);
        return motEnFrancais;
    }

    public String calculerJavanais(String mot) {
        StringBuilder motEnJavanais = new StringBuilder();

        for (int indice = 0; indice < mot.length(); indice++) {
            char caractere = mot.charAt(indice);

            if (estConsonne(caractere)) {
                motEnJavanais.append(caractere);
            } else {
                int indexConsonne = recupererIndiceProchaineConsonne(indice, mot);
                motEnJavanais.append("av");
                motEnJavanais.append(mot, indice, indexConsonne + 1);
                indice = indexConsonne;
            }
        }
        return motEnJavanais.toString();
    }

    private int recupererIndiceProchaineConsonne(int indiceDebut, String mot) {
        int taille = mot.length();
        int indiceDernierCaractere = taille - 1;

        for (int indice = indiceDebut; indice < taille; indice++) {
            if (estConsonne(mot.charAt(indice))) {
                return indice;
            }
        }
        return indiceDernierCaractere;
    }

    private boolean estConsonne(char caracter) {
        return !String.valueOf(caracter).matches("[aeiou]");
    }

    private void controlerFormatJavanais(String mot, String motEnFrancais) throws ControleJavanaisException {
        String motEnJavanais = calculerJavanais(motEnFrancais);

        if(!Objects.equals(mot, motEnJavanais)) {
            String erreur = String.format("Le mot/La phrase %s n'est pas au format javanais", mot);
            throw new ControleJavanaisException(erreur);
        }
    }
}
