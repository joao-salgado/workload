package com.desafio.workload.repository.helper;

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

import com.desafio.workload.model.Curso;
import com.desafio.workload.repository.filter.CursoFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class CursosImpl implements CursosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(CursoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CursoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if(isTipoPresente(filtro)) {
				criteria.add(Restrictions.eq("tipo", filtro.getTipo()));
			}
			
			if(isDepartamentoPresente(filtro)) {
				criteria.add(Restrictions.eq("departamento", filtro.getDepartamento()));
			}
		
		}
	}
	
	private boolean isTipoPresente(CursoFilter filtro) {
		return filtro.getTipo() != null && filtro.getTipo().getCodigo() != null;
	}
	
	private boolean isDepartamentoPresente(CursoFilter filtro) {
		return filtro.getDepartamento() != null && filtro.getDepartamento().getCodigo() != null;
	}

}
