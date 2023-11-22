package com.cap.bna.dbreplica.model.PIF;

import java.util.UUID;

import com.cap.bna.dbreplica.model.Aplicacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FUNCIONALIDADES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionalidade {

    @Id
    @Column(name = "ID_FUNCIONALIDADE")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "APLICACAO")
    private Aplicacao aplicacao;

    /* 
    @ManyToMany(mappedBy = "funcionalidades")
    Set<Perfil> perfis;
    */
}