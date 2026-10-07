package in.varma.edutrack.advice;

import in.varma.edutrack.apiResponce.ApiResponse;
import in.varma.edutrack.exception.EmailSendingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<ApiResponse> handleEmailException(EmailSendingException e) {

        ApiResponse response = new ApiResponse(
                500,
                "faild",
                e.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}