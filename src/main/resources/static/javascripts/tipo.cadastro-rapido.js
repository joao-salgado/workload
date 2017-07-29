var Workload = Workload || {};

Workload.TipoCadastroRapido = (function() {
	
	function TipoCadastroRapido() {
		this.modal = $('#modalCadastroRapidoTipo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-tipo-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeTipo = $('#nomeTipo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-tipo'); 
	}
	
	TipoCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));	
	}
	
	function onModalShow() {
		this.inputNomeTipo.focus();
	}
	
	function onModalClose() {
		this.inputNomeTipo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeTipo = this.inputNomeTipo.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ descricao: nomeTipo }),
			error: onErrorSalvandoTipo.bind(this),
			success: onTipoSalvo.bind(this)
		});
	}
	
	function onErrorSalvandoTipo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}	
	
	function onTipoSalvo(tipo) {
		var comboTipo = $('#tipo');
		comboTipo.append('<option value=' + tipo.codigo + '>' + tipo.descricao + '</option>');
		comboTipo.val(tipo.codigo);
		this.modal.modal('hide');
	}
	
	return TipoCadastroRapido;
	
}());

$(function() {
	var tipoCadastroRapido = new Workload.TipoCadastroRapido();
	tipoCadastroRapido.iniciar();
});