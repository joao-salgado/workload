package com.desafio.workload.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.desafio.workload.model.Professor;
import com.desafio.workload.model.Usuario;
import com.desafio.workload.repository.Professores;
import com.desafio.workload.repository.Usuarios;
import com.desafio.workload.service.exception.EmailUsuarioJaCadastradoException;
import com.desafio.workload.service.exception.SenhaObrigatoriaUsuarioException;
import com.desafio.workload.service.exception.SiapeProfessorJaCadastradoException;

@Service
public class CadastroProfessorService {
	
	@Autowired
	private Professores professores;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Professor professor, Usuario usuario) {
		
		Optional<Professor> professorExistente = professores.findBySiape(professor.getSiape()); 
		if(professorExistente.isPresent() && !professorExistente.get().equals(professor)) {
			throw new SiapeProfessorJaCadastradoException("SIAPE já cadastrado");
		}
		
		
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
		
				
		if(!usuario.isNovo()) {
			Long aux = usuarios.retornaProfessor(usuario.getCodigo());
			professor.setCodigo(aux);
		} else {
			professor.setUsuario(usuario);
		}
		
		
		professores.save(professor);		
		usuario.setProfessor(professor);		
		usuarios.save(usuario);
	}


}
