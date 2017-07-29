package com.desafio.workload.repository.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.desafio.workload.model.Turma;
import com.desafio.workload.repository.filter.TurmaFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class TurmasImpl implements TurmasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Turma> filtrar(TurmaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Turma.class);
		
		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Turma buscarComAlunos(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Turma.class);	 
		criteria.createAlias("alunos", "a", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
		
		return (Turma) criteria.uniqueResult();
	}
	
	private Long total(TurmaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Turma.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(TurmaFilter filtro, Criteria criteria) {
		criteria.createAlias("disciplina", "d");
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getSigla())) {
				criteria.add(Restrictions.ilike("sigla", filtro.getSigla(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getNomeDisciplina())) {
				criteria.add(Restrictions.ilike("d.nome", filtro.getNomeDisciplina(), MatchMode.ANYWHERE));
			}
			
			if(filtro.getAno() != null) {
				criteria.add(Restrictions.eq("periodo.ano", filtro.getAno()));
			}
			
			if(filtro.getSemestre() != null) {
				criteria.add(Restrictions.eq("periodo.semestre", filtro.getSemestre()));
			}
		
		}
	}


}
