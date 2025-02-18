package org.demo.javanais.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
@RequiredArgsConstructor
public class ApiErreur {

    private final HttpStatus status;
    private final String erreur;

    public static ApiErreur creerAvecWarn(HttpStatus httpStatus, String erreur, Exception e) {
        log.warn(erreur, e);
        return new ApiErreur(httpStatus, erreur);
    }
}
