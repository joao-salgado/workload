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

import com.desafio.workload.model.Disciplina;
import com.desafio.workload.repository.filter.DisciplinaFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class DisciplinasImpl implements DisciplinasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Disciplina> filtrar(DisciplinaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Disciplina.class);
		
		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));

	}
	
	private Long total(DisciplinaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Disciplina.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(DisciplinaFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getSigla())) {
				criteria.add(Restrictions.ilike("sigla", filtro.getSigla(), MatchMode.ANYWHERE));
			}
			
			if(isCursoPresente(filtro)) {
				criteria.add(Restrictions.eq("curso", filtro.getCurso()));
			}
			
			if(isDificuldadePresente(filtro)) {
				criteria.add(Restrictions.eq("dificuldade", filtro.getDificuldade()));
			}
			
			if(filtro.getCreditos() != null) {
				criteria.add(Restrictions.eq("creditos", filtro.getCreditos()));
			}
		
		}
	}
	
	private boolean isCursoPresente(DisciplinaFilter filtro) {
		return filtro.getCurso() != null && filtro.getCurso().getCodigo() != null;
	}
	
	private boolean isDificuldadePresente(DisciplinaFilter filtro) {
		return filtro.getDificuldade() != null && filtro.getDificuldade().getCodigo() != null;
	}

}
