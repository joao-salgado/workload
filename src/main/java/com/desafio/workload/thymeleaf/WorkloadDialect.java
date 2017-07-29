package com.desafio.workload.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.desafio.workload.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.desafio.workload.thymeleaf.processor.MenuAttributeTagProcessor;
import com.desafio.workload.thymeleaf.processor.MessageElementTagProcessor;
import com.desafio.workload.thymeleaf.processor.OrderElementTagProcessor;
import com.desafio.workload.thymeleaf.processor.PaginationElementTagProcessor;

public class WorkloadDialect extends AbstractProcessorDialect {

	public WorkloadDialect() {
		super("Desafio Workload", "workload", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
