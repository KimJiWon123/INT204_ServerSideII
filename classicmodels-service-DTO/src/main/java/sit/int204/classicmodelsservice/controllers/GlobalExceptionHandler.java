package sit.int204.classicmodelsservice.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sit.int204.classicmodelsservice.exception.ErrorResponse;
import sit.int204.classicmodelsservice.exception.ItemNotFoundException;

@RestControllerAdvice(assignableTypes = {ProductController.class , OfficeController.class})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ItemNotFoundException.class , ResponseStatusException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(
            RuntimeException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.UNPROCESSABLE_ENTITY.value(),
//                "Validation error. Check 'errors' field for details.",
//                request.getDescription(false)
//        );
//        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
//            errorResponse.addValidationError(fieldError.getField(),
//                    fieldError.getDefaultMessage());
//        }
//        return ResponseEntity.unprocessableEntity().body(errorResponse);
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlerJpaException(
            DataAccessException exception, WebRequest request) {
        return buildErrorResponse(
                exception, HttpStatus.BAD_REQUEST, request
        );
    }
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception, HttpStatus httpStatus, WebRequest request) {
        return buildErrorResponse( exception, exception.getMessage(), httpStatus, request);
    }
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception, String message, HttpStatus httpStatus, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message,
                request.getDescription(false)
        );
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }}