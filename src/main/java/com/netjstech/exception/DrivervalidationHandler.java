package com.netjstech.exception; 
 
import java.util.HashMap; 
import java.util.Map; 
 
import org.springframework.http.HttpHeaders; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntities; 
import org.springframework.validation.FieldError; 
import org.springframework.web.bind.MethodArgumentNotValidException; 
import org.springframework.web.bind.annotation.ControllerAdvice; 
import org.springframework.web.context.request.WebRequest; 
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntitiesExceptionHandler; 
 
@ControllerAdvice 
public class DrivervalidationHandler extends ResponseEntitiesExceptionHandler{ 
 
@Override 
 
protected ResponseEntities<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
HttpHeaders headers, HttpStatus status, WebRequest request) { 
Map<String, String> errors = new HashMap<>(); 
ex.getBindingResult().getAllErrors().forEach((error) ->{ 
 
String fieldName = ((FieldError) error).getField(); 
String message = error.getDefaultMessage(); 
errors.put(fieldName, message); 
}); 
return new ResponseEntities<Object>(errors, HttpStatus.BAD_REQUEST); 
} 
} 
