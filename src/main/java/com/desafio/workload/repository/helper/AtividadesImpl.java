package com.desafio.workload.repository.helper;

import java.time.LocalDate;
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

import com.desafio.workload.dto.AtividadePorMes;
import com.desafio.workload.model.Atividade;
import com.desafio.workload.repository.filter.AtividadeFilter;
import com.desafio.workload.repository.paginacao.PaginacaoUtil;

public class AtividadesImpl implements AtividadesQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Atividade> filtrar(AtividadeFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Atividade.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AtividadePorMes> totalPorMesInicio() {
		List<AtividadePorMes> atividadesMes = manager.createNamedQuery("Atividades.totalPorMesInicio").getResultList();
		
		LocalDate hoje = LocalDate.now();
		for(int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", hoje.getYear(), hoje.getMonthValue());
			
			boolean possuiMes = atividadesMes.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			
			if(!possuiMes) {
				atividadesMes.add(i - 1, new AtividadePorMes(mesIdeal, 0));
			}
			
			hoje = hoje.minusMonths(1);
		}
		
		
		return atividadesMes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AtividadePorMes> totalPorMesTermino() {
		List<AtividadePorMes> atividadesMes = manager.createNamedQuery("Atividades.totalPorMesTermino").getResultList();
		
		LocalDate hoje = LocalDate.now();
		for(int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", hoje.getYear(), hoje.getMonthValue());
			
			boolean possuiMes = atividadesMes.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			
			if(!possuiMes) {
				atividadesMes.add(i - 1, new AtividadePorMes(mesIdeal, 0));
			}
			
			hoje = hoje.minusMonths(1);
		}
		
		
		return atividadesMes;
	}
	
	private Long total(AtividadeFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Atividade.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(AtividadeFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}

			if(isAreaPresente(filtro)) {
				criteria.add(Restrictions.eq("area", filtro.getArea()));
			}
			
			if(isRestricaoPresente(filtro)) {
				criteria.add(Restrictions.eq("restricao", filtro.getRestricao()));
			}
			
			if(filtro.getPontuacao() != null) {
				criteria.add(Restrictions.eq("pontuacao", filtro.getPontuacao()));
			}
		
		}
	}
	
	private boolean isAreaPresente(AtividadeFilter filtro) {
		return filtro.getArea() != null && filtro.getArea().getCodigo() != null;
	}
	
	private boolean isRestricaoPresente(AtividadeFilter filtro) {
		return filtro.getRestricao() != null && filtro.getRestricao().getCodigo() != null;
	}
	
}
