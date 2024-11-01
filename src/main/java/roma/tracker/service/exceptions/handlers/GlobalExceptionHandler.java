package roma.tracker.service.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import roma.tracker.service.dto.ApiErrorResponse;
import roma.tracker.service.exceptions.ApiException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiErrorResponse> handler(ApiException exception) {
        return new ResponseEntity<>(new ApiErrorResponse(exception.getTitle(), exception.getDescription(), exception.getTime())
                , exception.getStatus());
    }

}