package net.oi.swccg.gemp.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.oi.swccg.gemp.exception.ProcessException;

@ControllerAdvice
public class OpenDeckStatsControllerAdvice {
    
    @ExceptionHandler(ProcessException.class)
    public ResponseEntity<Object> handleProcessException(ProcessException pe) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pe);
    }

}
