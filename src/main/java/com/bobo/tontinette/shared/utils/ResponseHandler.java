package com.bobo.tontinette.shared.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mamadou Bobo on 02/11/2023
 * @project Tontine
 */

@Service
public class ResponseHandler implements ResponseHandlerService {
    @Override
    public ResponseEntity<String> handleError(String errorMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(errorMessage,httpStatus);
    }

    @Override
    public ResponseEntity<String> handleSuccess(String successMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(successMessage,httpStatus);
    }
}