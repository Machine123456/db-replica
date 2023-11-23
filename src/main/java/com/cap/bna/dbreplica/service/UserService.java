package com.cap.bna.dbreplica.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cap.bna.dbreplica.dto.UtilizadorRequest;
import com.cap.bna.dbreplica.dto.UtilizadorResponse;
import com.cap.bna.dbreplica.model.PIF.Perfil;
import com.cap.bna.dbreplica.model.PIF.Utilizador;
import com.cap.bna.dbreplica.repository.PIF.UtilizadorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UtilizadorRepository userRepository;

    public List<UtilizadorResponse> getAllUsers() {
        List<Utilizador> users = userRepository.findAll();
        return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }

    public Utilizador findUserByUsername(String uname) throws IllegalArgumentException {
        var user = userRepository.findFirstByNome(uname)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + uname));
        return user;
    }

    public Utilizador findUserById(UUID userId) throws IllegalArgumentException {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return user;
    }

    public void createUser(UtilizadorRequest userData) throws IllegalArgumentException {
        userRepository.save(mapToObj(userData));
    }

    public void deleteUser(UUID id) throws IllegalArgumentException {
        userRepository.deleteById(id);
    }

    public void updateUser(UUID id, UtilizadorRequest userData) throws IllegalArgumentException {
        var user = findUserById(id);
        updateUser(user, userData);
    }

    public void updateUser(String name, UtilizadorRequest userData) throws IllegalArgumentException {
        var user = findUserByUsername(name);
        updateUser(user, userData);
    }

    public void updateUser(Utilizador user, UtilizadorRequest userData) throws IllegalArgumentException {

        if(!user.getSenha().equals(userData.getSenha()))
            user.setDataAltSenha(new Date());

        user.setNome(userData.getNome());
        user.setEmail(userData.getEmail());
        user.setSenha(userData.getSenha());
        user.setNomeExibicao(userData.getNomeExibicao());

        user.setClienteBancario(userData.isClienteBancario());
        user.setDadosComplementares(userData.getDadosComplementares());
        //user.setInstituicaoFinanceira(userData.getInstituicaoFinanceira());
        //user.setPerfis(userData.getPerfis());

        userRepository.save(user);
    }


    private UtilizadorResponse mapToDto(Utilizador user){

        return UtilizadorResponse.builder()
        .nome(user.getNome())
        .email(user.getEmail())
        .nomeExibicao(user.getNomeExibicao())
        .instituicaoFinanceira(
            user.getInstituicaoFinanceira() == null ? "" : 
            user.getInstituicaoFinanceira().getNome())
        .perfis(
            user.getPerfis() == null ? Set.of(): 
            new HashSet<>(user.getPerfis().stream().map(Perfil::getNome).collect(Collectors.toList()))
            )

        .clienteBancario(user.isClienteBancario() ? 'S' : 'N')
        .dadosComplementares(user.getDadosComplementares())
        .dataAltSenha(user.getDataAltSenha())
        .dataCriacao(user.getDataCriacao())

        .build();
    }

    private Utilizador mapToObj(UtilizadorRequest userRequest) {

        return Utilizador.builder()
        .nome(userRequest.getNome())
        .email(userRequest.getEmail())
        .senha(userRequest.getSenha())
        .clienteBancario(userRequest.isClienteBancario())
        .dadosComplementares(userRequest.getDadosComplementares())
        .nomeExibicao(userRequest.getNomeExibicao())
        .build();
    }

}
