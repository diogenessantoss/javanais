package org.demo.javanais.web;

import lombok.AllArgsConstructor;
import org.demo.javanais.domain.JavanaisFrancaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JavanaisFrancaisController {

    private final JavanaisFrancaisService javanaisFrancaisService;

    @GetMapping("/javanais/{valeur}")
    public ResponseEntity<String> recupererJavanais(@PathVariable String valeur) {

        String javanais = javanaisFrancaisService.calculerJavanais(valeur);

        return new ResponseEntity<>(javanais, HttpStatus.OK);
    }

    @GetMapping("/francais/{valeur}")
    public ResponseEntity<String> recupererFrancais(@PathVariable String valeur) throws Exception {

        String francais = javanaisFrancaisService.calculerFrancais(valeur);

        return new ResponseEntity<>(francais, HttpStatus.OK);
    }
}
