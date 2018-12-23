package ninja.tuxtech.api.exceptions.advice;


import ninja.tuxtech.api.exceptions.ErrorDetails;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CrudResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        AtomicReference<String> erroMessage = new AtomicReference<>("");
        ex.getBindingResult().getFieldErrors().forEach(e -> erroMessage.set(erroMessage.get()+e.getObjectName() + " " + e.getField() + " " + e.getDefaultMessage()+"||"));

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
                erroMessage.get());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {

        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), "ConstraintViolationException",
                        ex.getConstraintName().substring(1 , ex.getConstraintName().indexOf(" ")));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        
        if(ex.getCause() instanceof ConstraintViolationException)
            return  handleConstraintViolation(((ConstraintViolationException) ex.getCause()));
        
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), "DataIntegrityViolationException",
                        ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }
}
