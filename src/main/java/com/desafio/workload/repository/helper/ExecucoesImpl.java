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

import com.desafio.workload.model.Execucao;
import com.desafio.workload.model.Professor;
import com.desafio.workload.repository.filter.ExecucaoFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class ExecucoesImpl {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Page<Execucao> filtrar(ExecucaoFilter filtro, Pageable pageable, Professor professor) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Execucao.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria, professor);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro, professor));

	}
	
	private Long total(ExecucaoFilter filtro, Professor professor) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Execucao.class);
		adicionarFiltro(filtro, criteria, professor);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(ExecucaoFilter filtro, Criteria criteria, Professor professor) {
		criteria.createAlias("atividade", "a");

		if(filtro != null) {
			
			criteria.add(Restrictions.eq("professor", professor));
			
			/*if (filtro.getDesde() != null) {
				LocalDateTime desde = LocalDateTime.of(filtro.getDesde(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("inicio", desde));
			}
			
			if (filtro.getAte() != null) {
				LocalDateTime ate = LocalDateTime.of(filtro.getAte(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("termino", ate));
			}*/
			
			if(!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("a.descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}

			if(isAreaPresente(filtro)) {
				criteria.add(Restrictions.eq("a.area", filtro.getArea()));
			}
			
			if(isRestricaoPresente(filtro)) {
				criteria.add(Restrictions.eq("a.restricao", filtro.getRestricao()));
			}
			
			if(filtro.getPontuacao() != null) {
				criteria.add(Restrictions.eq("a.pontuacao", filtro.getPontuacao()));
			}
			
			
		
		}
	}
	
	private boolean isAreaPresente(ExecucaoFilter filtro) {
		return filtro.getArea() != null && filtro.getArea().getCodigo() != null;
	}
	
	private boolean isRestricaoPresente(ExecucaoFilter filtro) {
		return filtro.getRestricao() != null && filtro.getRestricao().getCodigo() != null;
	}
	
	
	
}
