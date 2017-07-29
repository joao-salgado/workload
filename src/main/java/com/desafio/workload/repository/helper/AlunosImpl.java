package com.desafio.workload.repository.helper;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.desafio.workload.dto.AlunoDTO;
import com.desafio.workload.model.Aluno;
import com.desafio.workload.repository.filter.AlunoFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class AlunosImpl implements AlunosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Aluno> filtrar(AlunoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Aluno.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));

	}
	
	private Long total(AlunoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Aluno.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(AlunoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if(isCursoPresente(filtro)) {
				criteria.add(Restrictions.eq("curso", filtro.getCurso()));
			}
			
			if(!StringUtils.isEmpty(filtro.getRa())) {
				criteria.add(Restrictions.eq("ra", filtro.getRa()));
			}
		
		}
	}
	
	private boolean isCursoPresente(AlunoFilter filtro) {
		return filtro.getCurso() != null && filtro.getCurso().getCodigo() != null;
	}

	@Override
	public List<AlunoDTO> porRaOuNome(String raOuNome) {
		String jpql = "select new com.desafio.workload.dto.AlunoDTO(codigo, ra, nome, email) "
				+ "from Aluno where ra like lower(:raOuNome) or lower(nome) like lower(:raOuNome)";
		List<AlunoDTO> alunosFiltrados = manager.createQuery(jpql, AlunoDTO.class)
				.setParameter("raOuNome", raOuNome + "%")
				.getResultList();
		return alunosFiltrados;
	}

}
