package com.example.PokemonAPI.exception;

import com.example.PokemonAPI.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarErroresDeValidacion(MethodArgumentNotValidException exception){
        List<String> detalles = new ArrayList<>();
        //Dentro de este for Iteramos sobre la exception
        for (FieldError error : exception.getBindingResult().getFieldErrors()){
            detalles.add(error.getDefaultMessage());
        }

        //Construimos el DTO del error
        ErrorDTO erroDTO = new ErrorDTO(
                "Error en la validacion de datos enviados",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(erroDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessRuleExcpetion.class)
    public ResponseEntity<ErrorDTO> manejarErrorDeNegocio(BusinessRuleExcpetion exception){
        List<String> detalles = new ArrayList<>();

        detalles.add(exception.getMessage());

        //Construimos el DTO del error
        ErrorDTO erroDTO = new ErrorDTO(
                "Violacion de regla de negocio",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(erroDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceNotTimeException.class)
    public ResponseEntity<ErrorDTO> manejarErrorDeNoEncontrado(ResourceNotTimeException exception){
        List<String> detalles = new ArrayList<>();

        detalles.add(exception.getMessage());

        //Construimos el DTO del error
        ErrorDTO erroDTO = new ErrorDTO(
                "El recurso no existe",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(erroDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarErrorDeServidor(Exception exception){
        List<String> detalles = new ArrayList<>();

        detalles.add(exception.getMessage());

        //Construimos el DTO del error
        ErrorDTO erroDTO = new ErrorDTO(
                "El error esta entre la silla y el escritorio",
                detalles,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(erroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
