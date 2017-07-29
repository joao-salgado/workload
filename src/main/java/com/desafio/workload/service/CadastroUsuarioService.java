package com.desafio.workload.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.desafio.workload.model.Usuario;
import com.desafio.workload.repository.Usuarios;
import com.desafio.workload.service.exception.EmailUsuarioJaCadastradoException;
import com.desafio.workload.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		
		Optional<Usuario> usuarioOptional = usuarios.findByEmailOrCodigo(usuario.getEmail(), usuario.getCodigo());
		
		if(usuarioOptional.isPresent() && !usuarioOptional.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("Este e-mail já está cadastrado");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatório para um novo usuário");
		}
		
		if(usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if(StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioOptional.get().getSenha());
			
		}
		
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if(!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioOptional.get().getAtivo());
		}
	
		usuarios.save(usuario);
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}
	
}
