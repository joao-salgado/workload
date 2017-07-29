Workload.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaAlunosContainer = $('.js-tabela-alunos-container');
		this.uuid = $('#uuid').val();
		
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
		bindTabelaAluno.call(this);
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoAluno: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaAlunosContainer.html(html);
		
		bindTabelaAluno.call(this);
		
	}
	
	function onDoubleClick(evento) {
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento) {
		var codigoAluno = $(evento.target).data('codigo-aluno');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoAluno,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindTabelaAluno() {
		$('.js-tabela-aluno').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
	}
	
	return TabelaItens;
	
}());
