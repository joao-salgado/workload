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

import com.desafio.workload.model.Departamento;
import com.desafio.workload.repository.filter.DepartamentoFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class DepartamentosImpl implements DepartamentosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Departamento> filtrar(DepartamentoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Departamento.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(DepartamentoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Departamento.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(DepartamentoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getSigla())) {
				criteria.add(Restrictions.eq("sigla", filtro.getSigla()));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(filtro.getBloco() != null) {
				criteria.add(Restrictions.eq("bloco", filtro.getBloco()));
			}
		
		}
	}


}
