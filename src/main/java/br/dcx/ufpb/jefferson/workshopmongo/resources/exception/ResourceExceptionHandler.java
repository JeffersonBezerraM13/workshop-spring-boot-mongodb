package br.dcx.ufpb.jefferson.workshopmongo.resources.exception;

import br.dcx.ufpb.jefferson.workshopmongo.services.exception.ObjectNotFoundExeception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExeception.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExeception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(),status.value(),"Resource not found",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
