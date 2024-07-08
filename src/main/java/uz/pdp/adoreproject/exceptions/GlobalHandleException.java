package uz.pdp.adoreproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.FieldErrorDTO;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<?> handle(AlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResultDTO.error(e.getMessage()));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handle(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResultDTO.error(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        List<FieldErrorDTO> list = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            list.add(new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResultDTO.errors(list));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResultDTO.error(e.getMessage()));
    }
}
