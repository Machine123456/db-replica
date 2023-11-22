package com.cap.bna.dbreplica.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.bna.dbreplica.model.EstadoNormativo;

public interface EstadoNormativoRepository extends JpaRepository<EstadoNormativo, UUID>{

}
