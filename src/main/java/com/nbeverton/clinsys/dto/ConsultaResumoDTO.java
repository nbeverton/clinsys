package com.nbeverton.clinsys.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaResumoDTO {

    private String pacienteNome;
    private LocalDateTime dataHora;
    private String status;
    private String pagamento;

}
