package unipar.integrador.mywallet.application.dto.dashboard;

import java.util.UUID;

public record BancosDTO(UUID id, String nome, double saldo) {
}
