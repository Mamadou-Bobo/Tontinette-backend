package com.bobo.tontinette.shared.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Mamadou Bobo on 02/11/2023
 * @project Tontine
 */
public interface ResponseHandlerService {
    ResponseEntity<String> handleError(String errorMessage, HttpStatus httpStatus);
    ResponseEntity<String> handleSuccess(String successMessage, HttpStatus httpStatus);
}
