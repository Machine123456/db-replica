package com.cap.bna.dbreplica.model.PIF;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

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
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UTILIZADORES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilizador {

      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      @Column(name = "ID_UTILIZADOR")
      private UUID id;
      
      @Column(name = "NOMEUTILIZADOR")
      private String nome;

      @Column(name = "SENHA")
      private String senha;

      @Column(name = "EMAIL")
      private String email;

      @Column(name = "NOMEEXIBICAO")
      @Builder.Default
      private String nomeExibicao = "Utilizador";

      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "INSTITUICAOFINANCEIRA")
      private InstituicaoFinanceira instituicaoFinanceira;

      @ManyToMany(cascade = CascadeType.ALL)
      @JoinTable(name = "UTILIZADORS_PERFIS", joinColumns = {
                  @JoinColumn(name = "ID_UTILIZADOR") }, inverseJoinColumns = { @JoinColumn(name = "ID_PERFIL") })
      Set<Perfil> perfis;

      @Column(name = "CLIENTEBANCARIO")
      private boolean clienteBancario;

      @Column(name = "DADOSCOMPLEMENTARES")
      private String dadosComplementares;

      @CreationTimestamp
      @Temporal(TemporalType.TIMESTAMP)
      @Column(name = "DATACRIACAO")
      private Date dataCriacao;

      // @UpdateTimestamp
      @Temporal(TemporalType.TIMESTAMP)
      @Column(name = "DATAALTSENHA")
      private Date dataAltSenha;
      
      @Transient
      private String preSenha;

      @PreUpdate
      private void preUpdate() {
            preSenha = senha;
      }

      @PostUpdate
      private void posUpdate() {
            if ((preSenha != null && !preSenha.equals(senha)) || (preSenha == null && senha != null)) {
                  this.dataAltSenha = new Date();
            }
      }

}