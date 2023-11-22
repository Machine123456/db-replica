package com.cap.bna.dbreplica.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESTADOS_NORMATIVOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoNormativo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID_ESTADO")
    private UUID id;

    @Column(name = "DESCRICAO")
    private String descricao; 
    /* 
    @OneToMany(mappedBy="aplicacao")
    private Set<Aplicacao> aplicacoes;
    */
}
