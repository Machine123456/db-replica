package com.cap.bna.dbreplica.model.PIF;

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
@Table(name = "INSTITUICOES_FINANCEIRAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstituicaoFinanceira {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_INSTITUICAOFINANCEIRA")
    private UUID id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CODIGO")
    private String codigo;

    /* 
    @OneToMany(mappedBy="instituicaoFinanceira")
    private Set<Utilizador> utilizadores;
    */
}
