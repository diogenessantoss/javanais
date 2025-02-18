package org.demo.javanais.web;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.demo.javanais.domain.JavanaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class JavanaisController {

    private final JavanaisService javanaisService;

    @GetMapping("/javanais/{valeur}")
    public ResponseEntity<String> recupererJavanais(@PathVariable String valeur,
                                                    @RequestParam(required = false) @NotBlank(message = "{erreur.validation.absence.valeur}") String syllabes) {

        String javanais = javanaisService.calculerJavanais(valeur, syllabes);

        return new ResponseEntity<>(javanais, HttpStatus.OK);
    }

    @GetMapping("/francais/{valeur}")
    public ResponseEntity<String> recupererFrancais(@PathVariable String valeur,
                                                    @RequestParam(required = false) @NotBlank(message = "{erreur.validation.absence.valeur}") String syllabes) throws Exception {

        String francais = javanaisService.calculerFrancais(valeur, syllabes);

        return new ResponseEntity<>(francais, HttpStatus.OK);
    }
}
