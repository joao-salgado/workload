var Workload = Workload || {};

Workload.GraficoAtividadePorMes = (function() {
	
	function GraficoAtividadePorMes() {
		this.ctx = $('#graficoAtividadesPorMesInicio')[0].getContext('2d');
	}
	
	GraficoAtividadePorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'atividades/totalPorMesInicio',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(atividadeMes) {
		var meses = [];
		var valores = [];
		atividadeMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoAtividadePorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Atividades por mês',
		    		backgroundColor: "rgba(0,128,255,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoAtividadePorMes;
	
}());

Workload.GraficoAtividadeFinalizada = (function() {
	
	function GraficoAtividadeFinalizada() {
		this.ctx = $('#graficoAtividadesPorMesTermino')[0].getContext('2d');
	}
	
	GraficoAtividadeFinalizada.prototype.iniciar = function() {
		$.ajax({
			url: 'atividades/totalPorMesTermino',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(atividadeMes) {
		var meses = [];
		var valores = [];
		atividadeMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoAtividadePorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Atividades por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoAtividadeFinalizada;
	
}());

$(function() {
	var graficoAtividadePorMes = new Workload.GraficoAtividadePorMes();
	graficoAtividadePorMes.iniciar();
	
	var graficoAtividadeFinalizada = new Workload.GraficoAtividadeFinalizada();
	graficoAtividadeFinalizada.iniciar();
});