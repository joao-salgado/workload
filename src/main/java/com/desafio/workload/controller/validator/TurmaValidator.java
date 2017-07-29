package com.desafio.workload.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.desafio.workload.model.Turma;

@Component
public class TurmaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Turma.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "professor.codigo", "", "Selecione um professor na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "disciplina.codigo", "", "Selecione uma disciplina na pesquisa rápida");
		
		Turma turma = (Turma) target;
		
		validarSeInformouSigla(errors, turma);
		
		validarSeInformouAno(errors, turma);
		
		validarSeInformouSemestre(errors, turma);
		
		validarSeTemPeloMenos10AlunosNaTurma(errors, turma);
	
	}

	private void validarSeTemPeloMenos10AlunosNaTurma(Errors errors, Turma turma) {
		if(turma.getQuantidadeAluno() < 1) {
			errors.reject("", "Adicione pelo menos 10 alunos a uma turma");
		}
	}

	private void validarSeInformouSemestre(Errors errors, Turma turma) {
		if(turma.getPeriodo().getSemestre() == null) {
			errors.rejectValue("periodo.semestre", "", "Informe o semestre");
		}
	}

	private void validarSeInformouAno(Errors errors, Turma turma) {
		if(turma.getPeriodo().getAno() == null) {
			errors.rejectValue("periodo.ano", "", "Informe o ano");
		}
	}

	private void validarSeInformouSigla(Errors errors, Turma turma) {
		if(StringUtils.isEmpty(turma.getSigla())) {
			errors.rejectValue("sigla", "", "Informe uma sigla para a turma");
		}
	}

}
