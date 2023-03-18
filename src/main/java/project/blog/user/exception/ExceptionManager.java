package project.blog.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.blog.utils.dto.ApiError;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(ApiError.class)
    public ResponseEntity<?> appExceptionHandler(ApiError e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode().name() + " " + e.getMessage());
    }
}
