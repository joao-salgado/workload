package com.desafio.workload.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Usuario;
import com.desafio.workload.repository.helper.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public List<Usuario> findByCodigoIn(Long[] codigos);

	public List<Usuario> findByNomeIgnoreCaseStartingWithAndAtivoTrueAndProfessorIsNotNull(String nome);

	public Optional<Usuario> findByEmailOrCodigo(String email, Long codigo);

	
	 @Query(
        value = "SELECT codigo_professor FROM usuario t where t.codigo = :codigo", 
        nativeQuery=true
	)
	public Long retornaProfessor(@Param("codigo") Long codigo);


	
}
