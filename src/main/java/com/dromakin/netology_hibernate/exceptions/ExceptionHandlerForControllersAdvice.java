/*
 * File:     ExceptionHandlerForControllersAdvice
 * Package:  com.dromakin.netology_jdbc_dao.exceptions
 * Project:  netology_jdbc_dao
 *
 * Created by dromakin as 06.10.2023
 *
 * author - dromakin
 * maintainer - dromakin
 * version - 2023.10.06
 * copyright - ORGANIZATION_NAME Inc. 2023
 */
package com.dromakin.netology_hibernate.exceptions;

import com.dromakin.netology_hibernate.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerForControllersAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(DataNotFoundException exception) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message("No data: " + exception)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
