package com.desafio.workload.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio.workload.service.exception.DescricaoTipoJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(DescricaoTipoJaCadastradoException.class)
	public ResponseEntity<String> handleDescricaoTipoJaCadastradoException(DescricaoTipoJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());

	}
	
}
