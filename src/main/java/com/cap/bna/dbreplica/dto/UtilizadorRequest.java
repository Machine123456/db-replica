package com.cap.bna.dbreplica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilizadorRequest {
    private String nome;
    private String email;
    private String senha;
    private String nomeExibicao;

    private boolean clienteBancario;
    private String dadosComplementares;
}
