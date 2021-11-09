package com.b1nd.dgit.handler;

import com.b1nd.dgit.domain.model.http.Response;
import com.b1nd.dgit.domain.model.http.ResponseData;
import com.b1nd.dgit.domain.model.http.errors.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ResponseData<List<FieldError>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("타입 검증 중 오류 발생", e);
    return ResponseData.res(HttpStatus.BAD_REQUEST, "타입 검증 중 오류 발생", e.getFieldErrors());
  }

  @ExceptionHandler(BindException.class)
  protected ResponseEntity<Response> handleBindException(BindException e) {
    log.error("바인딩 중 오류 발생", e);
    return Response.res(HttpStatus.BAD_REQUEST, "바인딩 중 오류 발생");
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<Response> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    log.error("허용되지 않은 HTTP METHOD 요청", e);
    return Response.res(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP METHOD 요청");
  }

  @ExceptionHandler(CustomError.class)
  protected ResponseEntity<Response> handleBusinessException(final CustomError e) {
    log.error("handleEntityNotFoundException", e);
    return Response.res(e.getErrorCode().getStatus(), e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Response> handleException(Exception e) {
    log.error("서버 오류 발생", e);
    return Response.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 발생");
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException e) {
    log.error("찾지 못하였습니다", e);
    return Response.res(HttpStatus.BAD_REQUEST, "찾지 못하였습니다");
  }
}
