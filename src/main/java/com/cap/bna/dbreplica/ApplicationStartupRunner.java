package com.cap.bna.dbreplica;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cap.bna.dbreplica.model.Aplicacao;
import com.cap.bna.dbreplica.model.EstadoNormativo;
import com.cap.bna.dbreplica.model.PIF.Funcionalidade;
import com.cap.bna.dbreplica.model.PIF.InstituicaoFinanceira;
import com.cap.bna.dbreplica.model.PIF.Perfil;
import com.cap.bna.dbreplica.model.PIF.Utilizador;
import com.cap.bna.dbreplica.repository.AplicacaoRepository;
import com.cap.bna.dbreplica.repository.EstadoNormativoRepository;
import com.cap.bna.dbreplica.repository.PIF.FuncionalidadeRepository;
import com.cap.bna.dbreplica.repository.PIF.InstituicaoFinanceiraRepository;
import com.cap.bna.dbreplica.repository.PIF.PerfilRepository;
import com.cap.bna.dbreplica.repository.PIF.UtilizadorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {

	private final FuncionalidadeRepository funcionalidadeRepository;
	private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
	private final PerfilRepository perfilRepository;
	private final UtilizadorRepository utilizadorRepository;
	private final AplicacaoRepository aplicacaoRepository;
	private final EstadoNormativoRepository estadoNormativoRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		if (estadoNormativoRepository.count() > 0)
			return;

		System.out.println("Criando Estados Normativos ...");

		var estado1 = createEstadoNormativo("Em Vigor");
		var estado2 = createEstadoNormativo("Revogada");

		System.out.println("Criando Aplicacoes ...");

		var SSIF = createAplicacao("SSIF", "SSIF", "Sistema de Supervisão das IFs", estado1);
		var BI = createAplicacao("BI", "Oracle BI", "Oracle Business Intelligence", estado2);
		var SCC = createAplicacao("SCC", "SINOC", "Sistema Integração de Operações Cambiais", estado1);
		var PIF = createAplicacao("PIF", "Portal", "Portal de Instituição Financeira", estado1);
		var CIRC = createAplicacao("CIRC", "CIRC", "Central de Informação e Risco de Crédito", estado1);
		var AMC = createAplicacao("AMC", "AMC", "Aplicação de Monitorização Continua", estado2);
		var SICO = createAplicacao("SICO", "SICON", "Sistema de Controlo de Operações das Instituições Não Bancárias",
				estado2);
		
		System.out.println("Criando Funcionalidades ...");

		var funcionalidade1 = creatFuncionalidade("PROPOR_NOTA", AMC);
		var funcionalidade2 = creatFuncionalidade("REPORTAR_NOTA", AMC);
		var funcionalidade3 = creatFuncionalidade("ACESSO_BACKOFFICE", AMC);
		var funcionalidade4 = creatFuncionalidade("APROVAR_PLANEAMENTO", AMC);


		System.out.println("Criando Perfis ...");

		var perfil1 = createPerfil("AMCAdministrador", "Administrador do Portal AMC", null, AMC, false, funcionalidade1,
				funcionalidade2, funcionalidade3, funcionalidade4);
		var perfil2 = createPerfil("AMCTecnicoSupervisao", "Técnoco de Supervisão", null, AMC, false, funcionalidade1);
		var perfil3 = createPerfil("AMCChefeSector", "Chefe de Sector", null, AMC, false, funcionalidade1);
		var perfil4 = createPerfil("AMCChefeDivisao", "Chefe de Divisão", null, AMC, false, funcionalidade1,
				funcionalidade2, funcionalidade4);
		var perfil5 = createPerfil("BnaAdmin", "BNA/DSI-Direcção", null, SSIF, false);
		var perfil6 = createPerfil("hier", "hierarquico", null, BI, true);
		var perfil7 = createPerfil("SSCAdministrador", "BNA/DCC - Administrador", null, SCC, false);
		var perfil8 = createPerfil("PortifAdmin", "Administrador do Portal da Instituição Financeira", null, PIF,
				false);
		var perfil9 = createPerfil("CIRCAdmin", "Administrador CIRC - DEMO", null, CIRC, false);
		var perfil10 = createPerfil("SICON_ADMIN", "Administração do SICON", null, SICO, false);

		System.out.println("Criando Instituicao Financeira ...");
		var instituicaoFinanceira1 = creatInstituicaoFinanceira("0001", "Banco Nacional de Angola");

		System.out.println("Criando Utilizador ...");
		var utilizador1 = createUtilizador("admincap", "Administrador Capgemini","123",
				"apoio.operacional.bna.pt@capgemini.com", false, instituicaoFinanceira1,
				perfil8, perfil5, perfil1);

	}

	private EstadoNormativo createEstadoNormativo(String descricao) {
		return estadoNormativoRepository.save(EstadoNormativo.builder()
				.descricao(descricao)
				.build());
	}

	private Aplicacao createAplicacao(String codigoInterno, String descricaoCurta, String descricaoLonga,
			EstadoNormativo estado) {
		return aplicacaoRepository.save(Aplicacao.builder()
				.codigoInterno(codigoInterno)
				.descricaoCurta(descricaoCurta)
				.descricaoLonga(descricaoLonga)
				.estado(estado)
				.build());
	}

	private Funcionalidade creatFuncionalidade(String nome, Aplicacao aplicacao) {
		return funcionalidadeRepository.save(Funcionalidade.builder()
				.nome(nome)
				.aplicacao(aplicacao)
				.build());
	}

	private Perfil createPerfil(String nome, String descricaoCurta, String descricaoLonga, Aplicacao aplicacao,
			boolean hierarquico, Funcionalidade... funcionalidades) {
		return perfilRepository.save(Perfil.builder()
				.nome(nome)
				.descricaoCurta(descricaoCurta)
				.descricaoLonga(descricaoLonga)
				.aplicacao(aplicacao)
				.hierarquico(hierarquico)
				.funcionalidades(new HashSet<Funcionalidade>(Arrays.asList(funcionalidades)))
				.build());
	}

	private InstituicaoFinanceira creatInstituicaoFinanceira(String codigo, String nome) {
		return instituicaoFinanceiraRepository.save(InstituicaoFinanceira.builder()
				.codigo(codigo)
				.nome(nome)
				.build());
	}

	private Utilizador createUtilizador(String nome, String nomeExibicao,String senha, String email, boolean clienteBancario,
			InstituicaoFinanceira instituicaoFinanceira, Perfil... perfis) {
		return utilizadorRepository.save(Utilizador.builder()
				.nome(nome)
				.email(email)
				.senha(senha)
				.clienteBancario(clienteBancario)
				.dadosComplementares(null)
				.instituicaoFinanceira(instituicaoFinanceira)
				.perfis(new HashSet<Perfil>(Arrays.asList(perfis)))
				.nomeExibicao(nomeExibicao)
				.build());
	}
}
