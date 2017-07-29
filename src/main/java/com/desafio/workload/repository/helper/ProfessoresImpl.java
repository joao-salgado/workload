package com.desafio.workload.repository.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Usuario;

public class ProfessoresImpl implements ProfessoresQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional(readOnly = true)
	@Override
	public Usuario buscarComGrupos(Long codigo) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);	 
		criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
		
		return (Usuario) criteria.uniqueResult();
	}

		
}
