package ar.edu.tp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        var errorDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        errorDetail.setTitle("Error de integridad");
        errorDetail.setDetail("Ocurrio un error al ejecutar la peticion");
        return ResponseEntity.badRequest().body(errorDetail);
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        var errorDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorDetail.setTitle("Error interno");
        errorDetail.setDetail("Ocurrio un error al ejecutar la peticion");
        return ResponseEntity.internalServerError().body(errorDetail);
    }


}

