package in.varma.edutrack.advice;

import in.varma.edutrack.apiResponce.ApiResponse;
import in.varma.edutrack.exception.CityNotFoundException;
import in.varma.edutrack.exception.CountryNotFoundException;
import in.varma.edutrack.exception.EmailSendingException;
import in.varma.edutrack.exception.StateNotFoundException;
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
                "Email Sending Failed",
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCountryNotFoundException(CountryNotFoundException e) {

        ApiResponse response = new ApiResponse(
                404,
                "Country Not Found",
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<ApiResponse> handleStateNotFoundException(StateNotFoundException e) {

        ApiResponse response = new ApiResponse(
                404,
                "State Not Found",
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCityNotFoundException(CityNotFoundException e) {

        ApiResponse response = new ApiResponse(
                404,
                "City Not Found",
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}