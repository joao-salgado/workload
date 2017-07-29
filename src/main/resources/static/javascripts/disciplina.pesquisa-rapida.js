Workload = Workload || {};

Workload.PesquisaRapidaDisciplina = (function() {
	
	function PesquisaRapidaDisciplina() {
		this.pesquisaRapidaDisciplinasModal = $('#pesquisaRapidaDisciplinas');
		this.siglaInput = $('#siglaRapida');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-disciplinas-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaDisciplinas');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-disciplina').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro-disciplina');
	}
	
	PesquisaRapidaDisciplina.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaDisciplinasModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow() {
		this.siglaInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
	
		$.ajax({ 
			url: this.pesquisaRapidaDisciplinasModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.siglaInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
		
	}
	
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaDisciplinaPesquisaRapida = new Workload.TabelaDisciplinaPesquisaRapida(this.pesquisaRapidaDisciplinasModal);
		tabelaDisciplinaPesquisaRapida.iniciar();
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}

	
	return PesquisaRapidaDisciplina;
	
}());

Workload.TabelaDisciplinaPesquisaRapida = (function() {
	
	function TabelaDisciplinaPesquisaRapida(modal) {
		this.modalDisciplina = modal;
		this.disciplina = $('.js-disciplina-pesquisa-rapida');
	}
	
	TabelaDisciplinaPesquisaRapida.prototype.iniciar = function() {
		this.disciplina.on('click', onDisciplinaSelecionada.bind(this));
	}
	
	function onDisciplinaSelecionada(evento) {
		this.modalDisciplina.modal('hide');
		var disciplinaSelecionada = $(evento.currentTarget);		
		$('#disciplina').val(disciplinaSelecionada.data('nome'));
		$('#codigoDisciplina').val(disciplinaSelecionada.data('codigo'));
	}
	
	return TabelaDisciplinaPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaDisciplina = new Workload.PesquisaRapidaDisciplina();
	pesquisaRapidaDisciplina.iniciar();
});