package org.demo.javanais.web;

import lombok.AllArgsConstructor;
import org.demo.javanais.domain.JavanaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JavanaisController {

    private final JavanaisService javanaisService;

    @GetMapping("/javanais/{valeur}")
    public ResponseEntity<String> calculerJavanais(@PathVariable String valeur) {

        String valeurCalculee = javanaisService.calculerJavanais(valeur);

        return new ResponseEntity<>(valeurCalculee, HttpStatus.OK);
    }
}
