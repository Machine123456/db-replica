package com.cap.bna.dbreplica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.bna.dbreplica.model.Aplicacao;

public interface AplicacaoRepository  extends JpaRepository<Aplicacao, UUID>{

}
