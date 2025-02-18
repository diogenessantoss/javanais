package org.demo.javanais.domain;

import org.springframework.stereotype.Service;

@Service
public class JavanaisService {

    public String calculerJavanais(String valeur) {
        StringBuilder javanais = new StringBuilder();

        for (int indice = 0; indice < valeur.length(); indice++) {
            char caractere = valeur.charAt(indice);

            if (estConsonne(caractere)) {
                javanais.append(caractere);
            } else {
                int indexConsonne = recupererIndiceProchaineConsonne(indice, valeur);
                javanais.append("av");
                javanais.append(valeur, indice, indexConsonne + 1);
                indice = indexConsonne;
            }
        }
        return javanais.toString();
    }

    private int recupererIndiceProchaineConsonne(int indiceDebut, String valeur) {
        int taille = valeur.length();
        int indiceDernierCaractere = taille - 1;

        for (int indice = indiceDebut; indice < taille; indice++) {
            if (estConsonne(valeur.charAt(indice))) {
                return indice;
            }
        }
        return indiceDernierCaractere;
    }

    private boolean estConsonne(char caracter) {
        return !String.valueOf(caracter).matches("[aeiou]");
    }
}
