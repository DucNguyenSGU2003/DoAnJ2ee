package com.UniCharity.UniCharity.exception;

import com.UniCharity.UniCharity.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handlingException(Exception exception) {
        log.error("Unhandled exception: ", exception);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        apiResponse.setErrorDetail(exception.getMessage()); // Bổ sung chi tiết lỗi

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        log.error("AppException: ", exception);
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setErrorDetail(exception.getMessage()); // Bổ sung chi tiết lỗi

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        log.error("Validation exception: ", exception);

        // Lấy tất cả lỗi từ các trường không hợp lệ
        List<String> errorMessages = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage("Validation failed");
        apiResponse.setErrorDetail(String.join(", ", errorMessages)); // Bổ sung chi tiết tất cả lỗi

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
