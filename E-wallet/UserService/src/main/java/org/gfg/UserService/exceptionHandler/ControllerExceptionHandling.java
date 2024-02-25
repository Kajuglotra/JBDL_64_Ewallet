package org.gfg.UserService.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandling {

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<Object> handle(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

//    @ExceptionHandler(value =Exception.class)
//    public ResponseEntity<Object> handle(Exception e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handle(MethodArgumentNotValidException e){
            return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
}
