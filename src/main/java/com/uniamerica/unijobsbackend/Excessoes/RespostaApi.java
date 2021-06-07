package com.uniamerica.unijobsbackend.Excessoes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RespostaApi {
    private Integer statusHttp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[xxx]")
    private LocalDateTime timestamp;
    private String titulo;
    private String path;
    private List<Campo> campos;

    public RespostaApi(Integer statusHttp, LocalDateTime timestamp, String titulo, String path) {
        this.statusHttp = statusHttp;
        this.timestamp = timestamp;
        this.titulo = titulo;
        this.path = path;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Campo {
        private String nome;
        private String mensagem;
    }
}
