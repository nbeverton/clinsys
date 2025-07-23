package com.nbeverton.clinsys.dto;

import com.nbeverton.clinsys.dto.dashboard.LastAppointmentsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardDTO {

    private long totalPacientes;
    private long consultasHoje;
    private long consultasSemana;
    private List<LastAppointmentsDTO> ultimosAgendamentos;
    private long consultasConcluidas;
    private long consultasPendentes;
    private long pagamentosConcluidos;
    private long pagamentosPendentes;

}
