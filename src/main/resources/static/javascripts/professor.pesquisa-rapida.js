Workload = Workload || {};

Workload.PesquisaRapidaProfessor = (function() {
	
	function PesquisaRapidaProfessor() {
		this.pesquisaRapidaProfessoresModal = $('#pesquisaRapidaProfessores');
		this.nomeInput = $('#nomeRapido');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-professores-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaProfessores');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-professor').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro-professor');
	}
	
	PesquisaRapidaProfessor.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaProfessoresModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaProfessoresModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
		
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaProfessorPesquisaRapida = new Workload.TabelaProfessorPesquisaRapida(this.pesquisaRapidaProfessoresModal);
		tabelaProfessorPesquisaRapida.iniciar();

	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaProfessor;
	
}());

Workload.TabelaProfessorPesquisaRapida = (function() {
	
	function TabelaProfessorPesquisaRapida(modal) {
		this.modalProfessor = modal;
		this.professor = $('.js-professor-pesquisa-rapida');
	}
	
	TabelaProfessorPesquisaRapida.prototype.iniciar = function() {
		this.professor.on('click', onProfessorSelecionado.bind(this));
	}
	
	function onProfessorSelecionado(evento) {
		this.modalProfessor.modal('hide');		
		var professorSelecionado = $(evento.currentTarget);
		$('#professor').val(professorSelecionado.data('nome'));
		$('#codigoProfessor').val(professorSelecionado.data('codigo'));

	}
	
	return TabelaProfessorPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaProfessor = new Workload.PesquisaRapidaProfessor();
	pesquisaRapidaProfessor.iniciar();
});