Workload = Workload || {};

Workload.Autocomplete = (function() {
	
	function Autocomplete() {
		this.raInput = $('.js-ra-aluno-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(ra) {
				return this.raInput.data('url') + '?raOuNome=' + ra;
			}.bind(this),
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type:'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		
		this.raInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.raInput.getSelectedItemData());
		this.raInput.val('');
		this.raInput.focus();
	}
	
	function template(nome, aluno) {
		return this.template(aluno);
	}
	
	return Autocomplete;
	
}());


