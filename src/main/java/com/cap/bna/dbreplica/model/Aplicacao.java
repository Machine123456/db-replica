package com.cap.bna.dbreplica.model;

import java.util.UUID;

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
@Table(name="APLICACOES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aplicacao {
    
    @Id
    @Column(name= "ID_APLICACAO")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ESTADO")
    private EstadoNormativo estado;

    @Column(name= "CODIGO_INTERNO")
    private String codigoInterno;

    @Column(name= "DESCRICAO_CURTA")
    private String descricaoCurta;

    @Column(name= "DESCRICAO_LONGA")
    private String descricaoLonga;

    /* 
    @OneToMany(mappedBy="aplicacao")
    private Set<Funcionalidade> funcionalidades;

    @OneToMany(mappedBy="aplicacao")
    private Set<Perfil> perfis;
    */
}
