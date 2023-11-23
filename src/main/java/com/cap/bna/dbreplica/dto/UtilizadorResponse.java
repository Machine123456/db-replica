package com.cap.bna.dbreplica.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilizadorResponse {
      
      private String nome;
      private String email;
      private String nomeExibicao;

      private String instituicaoFinanceira;

      Set<String> perfis;

      private Character clienteBancario;

      private String dadosComplementares;

      private Date dataCriacao;

      private Date dataAltSenha;



}
