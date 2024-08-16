package com.jrolab.medic_app.exception;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleModelAlldExceptions(ModelNotFoundException ex,
            WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(),
                "ID NOT FOUND",
                request.getDescription(false));

        return new ResponseEntity<CustomErrorResponse>(err, HttpStatus.NOT_FOUND);
    }

    // Spring boot 3
    @ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex,
            WebRequest request) {
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model Not Found Exception").type(URI.create(request.getDescription(false)))
                .property("extra1", LocalDateTime.now()).build();
    }

    // Desde Spring boot 3
    /**
     * @ExceptionHandler(ModelNotFoundException.class)
     *                                                 public ProblemDetail
     *                                                 handleModelNotFoundException(ModelNotFoundException
     *                                                 ex,
     *                                                 WebRequest request) {
     *                                                 ProblemDetail pd =
     *                                                 ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
     *                                                 ex.getMessage());
     * 
     *                                                 pd.setDetail("Model not Found
     *                                                 Exception");
     *                                                 pd.setType(URI.create(request.getDescription(false)));
     *                                                 pd.setProperty("date",
     *                                                 LocalDateTime.now());
     *                                                 pd.setProperty("random",
     *                                                 (int)(Math.random() * 100) +
     *                                                 1);
     *                                                 return pd;
     *                                                 }
     */
    /*
     * @ExceptionHandler(ModelNotFoundException.class)
     * public ResponseEntity<CustomErrorResponse>
     * handleModelNotFoundException(ModelNotFoundException ex,
     * WebRequest request) {
     * CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(),
     * "ID NOT FOUND",
     * request.getDescription(false));
     * 
     * return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
     * }
     */

}
