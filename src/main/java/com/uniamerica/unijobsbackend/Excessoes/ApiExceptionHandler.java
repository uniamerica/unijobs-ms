package com.uniamerica.unijobsbackend.Excessoes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var statusHttp = status.value();
        var timestamp = LocalDateTime.now();
        var titulo = "Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente!";
        var path = request.getDescription(false).substring(5);
        var corpo = new RespostaApi(statusHttp, timestamp, titulo, path);
        var campos = new ArrayList<RespostaApi.Campo>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){

            var nome = ((FieldError) error).getField();
            var mensagem = error.getDefaultMessage();

            campos.add(new RespostaApi.Campo(nome, mensagem));
        }
        corpo.setCampos(campos);
        return super.handleExceptionInternal(ex, corpo, headers, status, request);
    }

    @ExceptionHandler(RecursoNaoEncontradoExcessao.class)
    public ResponseEntity<RespostaApi> trataRecursoNaoEncontrado(RecursoNaoEncontradoExcessao ex, WebRequest request){
        var statusHttp = HttpStatus.NOT_FOUND;
        var timestamp = LocalDateTime.now();
        var titulo = ex.getMessage();
        var path = request.getDescription(false).substring(5);
        var corpo = new RespostaApi(statusHttp.value(), timestamp, titulo, path);

        return ResponseEntity.status(statusHttp).body(corpo);
    }

  @ExceptionHandler(RegraNegocioExcessao.class)
  public ResponseEntity<RespostaApi> trataRegraNegocioExcessao(RegraNegocioExcessao ex, WebRequest request){
    var statusHttp = HttpStatus.CONFLICT;
    var timestamp = LocalDateTime.now();
    var titulo = ex.getMessage();
    var path = request.getDescription(false).substring(5);
    var corpo = new RespostaApi(statusHttp.value(), timestamp, titulo, path);

    return ResponseEntity.status(statusHttp).body(corpo);
  }
}
