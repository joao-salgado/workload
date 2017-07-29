package com.desafio.workload.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.workload.model.Usuario;
import com.desafio.workload.repository.Usuarios;
import com.desafio.workload.security.UsuarioSistema;
import com.desafio.workload.service.CadastroUsuarioService;
import com.desafio.workload.service.exception.EmailUsuarioJaCadastradoException;

@Controller
@RequestMapping("/altera")
public class AlteracoesUsuariosController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private Usuarios usuarios;
	
	@RequestMapping("/novo")
	public ModelAndView editar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/AlterarUsuario");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView alterando(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if (result.hasErrors()) {
			return editar(usuario);
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch(EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return editar(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Perfil alterado com sucesso");
		ModelAndView mv = new ModelAndView("redirect:/altera/" + usuarioSistema.getUsuario().getCodigo());
		return mv;
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView altere(@PathVariable Long codigo) {
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		ModelAndView mv = editar(usuario);
		mv.addObject(usuario);
		return mv;
	}
	
}
