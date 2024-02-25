package io.github.pedrovsn.rinhabackend.controller.advice;

import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class RinhaControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMessageNotReadableException(
            HttpServletRequest req, Exception ex
    ) {

        return ResponseEntity.unprocessableEntity().build();
    }

}
