package com.cap.bna.dbreplica.repository.PIF;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.bna.dbreplica.model.PIF.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, UUID>{
    Optional<Utilizador> findFirstByNome(String nome);
}
