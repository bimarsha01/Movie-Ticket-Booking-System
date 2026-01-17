package com.example.userauth.ExceptionHandling;

import com.example.userauth.API.ApiError;
import com.example.userauth.API.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        BindingResult bindingResult = (ex.getBindingResult());
        List<FieldError> errorList = bindingResult.getFieldErrors();
        List<String> messageList = new ArrayList<>();
        for (FieldError fieldError : errorList) {
            String message = fieldError.getField() + " should be " + fieldError.getDefaultMessage();
            messageList.add(message);
        }
        return new ResponseEntity<>(new ApiResponse("Data validation failed !!", false, messageList), BAD_REQUEST);
    }

    @ExceptionHandler(HandleDataException.class)
    public ResponseEntity<ApiResponse> handleResourceException(HandleDataException ex){
        ApiResponse error = new ApiResponse(ex.getMessage() , Boolean.FALSE , "");
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> unauthorizedException(UnauthorizedException ex){
        ApiResponse error = new ApiResponse(ex.getMessage() , Boolean.FALSE , "");
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HandleTimeExceedException.class)
    public ResponseEntity<ApiError> handleTimeExceedException(HandleTimeExceedException ex){
        ApiError error = new ApiError(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(error , BAD_REQUEST);

    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiError> AlreadyExistException(AlreadyExistException ex){
        ApiError error = new ApiError(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(error , BAD_REQUEST);

    }

    @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<ApiError> notAvailableException(NotAvailableException ex){
        ApiError error = new ApiError(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(error , BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> invalidCredentialsException(InvalidCredentialsException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                Boolean.FALSE,
                ex.getErrorCode()
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundException(NotFoundException ex){
        ApiError error = new ApiError("NOT_FOUND", Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException err){
        String errorMessage = "Data integrity violation occurred";
        String errorCode = "DATA_INTEGRITY_VIOLATION";

        if (err.getMostSpecificCause() != null) {
            errorMessage = err.getMostSpecificCause().getMessage();
        } else if (err.getMessage() != null) {
            errorMessage = err.getMessage();
        }

        if (err.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) err.getCause();
            String key = cve.getConstraintName();
            if (key != null && key.contains(".")) {
                String[] constraintMessage = key.split("\\.");
                if (constraintMessage.length > 1) {
                    errorCode = constraintMessage[constraintMessage.length - 1];
                }
            }
        }

        ApiError error = new ApiError(errorCode, Boolean.FALSE, errorMessage);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse> handleException(Exception ex){
//        ApiResponse error = new ApiResponse("something went wrong" , Boolean.FALSE , "");
//        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
