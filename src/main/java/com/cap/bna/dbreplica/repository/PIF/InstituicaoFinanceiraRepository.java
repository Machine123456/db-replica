package com.cap.bna.dbreplica.repository.PIF;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.bna.dbreplica.model.PIF.InstituicaoFinanceira;

public interface InstituicaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceira, UUID>{
    
}
