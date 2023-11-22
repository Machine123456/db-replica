package com.cap.bna.dbreplica.model.PIF;

import java.util.Set;
import java.util.UUID;

import com.cap.bna.dbreplica.model.Aplicacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERFIS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID_PERFIL")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "APLICACAO")
    private Aplicacao aplicacao;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PERFIS_FUNCIONALIDADES", joinColumns = @JoinColumn(name = "ID_PERFIL"), inverseJoinColumns = @JoinColumn(name = "ID_FUNCIONALIDADE"))
    Set<Funcionalidade> funcionalidades;

    /*
     * @ManyToMany(mappedBy = "perfis")
     * Set<Utilizador> utilizadors;
     */
    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO_CURTA")
    private String descricaoCurta;

    @Column(name = "DESCRICAO_LONGA")
    private String descricaoLonga;

    @Column(name = "HIERARQUICO")
    private boolean hierarquico;

}
