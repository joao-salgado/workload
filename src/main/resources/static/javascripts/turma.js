Workload.Turma = (function() {
	
	function Turma(tabelaItens) {
		this.tabelaItens = tabelaItens;
	}
	
	Turma.prototype.iniciar = function() {
		
	}
	
	return Turma;
	
}());



$(function() {
	
	var autocomplete = new Workload.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Workload.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var turma = new Workload.Turma(tabelaItens);
	turma.iniciar();
})