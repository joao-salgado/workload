package com.desafio.workload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrosController {

	@RequestMapping("/404")
	public String notFound() {
		return "/error/404";
	}
	
	@RequestMapping("/500")
	public String internalServerError() {
		return "/error/500";
	}
	
}
