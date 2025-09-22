package hw8.rest;

import hw8.dto.ErrorDto;
import hw8.exception.NotEnoughMoneyException;
import hw8.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ErrorDto handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ErrorDto(ex.getMessage(), BAD_REQUEST.value());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorDto handleRecordNotFoundException(RecordNotFoundException ex) {
        return new ErrorDto("Запись не найдена: " + ex.getMessage(), NOT_FOUND.value());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(new ErrorDto("Невалидный запрос: " + ex.getMessage(), BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(PAYMENT_REQUIRED)
    protected ErrorDto handleNotEnoughMoneyException(NotEnoughMoneyException ex) {
        return new ErrorDto(ex.getMessage(), PAYMENT_REQUIRED.value());
    }
}