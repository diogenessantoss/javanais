package org.demo.javanais.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JavanaisService {

    public String calculerJavanais(String valeur) {
        char[] caracteres = valeur.toCharArray();
        StringBuilder retour = new StringBuilder();

        for (int indice = 0; indice < caracteres.length; indice++) {
            char caractere = caracteres[indice];

            if (estConsonne(caractere)) {
                retour.append(caractere);
            } else {
                int indexConsonne = recupererIndiceProchaineConsonne(indice, caracteres);
                retour.append("av");
                retour.append(valeur, indice, indexConsonne + 1);
                indice = indexConsonne;
            }
        }
        return retour.toString();
    }

    private int recupererIndiceProchaineConsonne(int indiceDebut, char[] caracteres) {
        int indiceConsonne = caracteres.length - 1;
        for (int indice = indiceDebut; indice < caracteres.length; indice++) {
            if (estConsonne(caracteres[indice])) {
                indiceConsonne = indice;
                break;
            }
        }
        return indiceConsonne;
    }

    private boolean estConsonne(char caracter) {
        return !(caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u');
    }
}
