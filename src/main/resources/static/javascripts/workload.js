	var Workload = Workload || {};

Workload.MaskMoney = (function() {
	
	function MaskMoney() {
		this.plain = $('.js-plain');		//FORMATAÇÃO PARA NÚMEROS INTEIROS		
	}
	
	MaskMoney.prototype.enable = function() {
		this.plain.maskMoney({ precision: 0, thousands: '.' }); //NENHUMA CASA DECIMAL COM SEPARADOR DE MILHAR		
	}
	
	return MaskMoney;
	
}());

Workload.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

Workload.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

Workload.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

$(function() {
	var maskMoney = new Workload.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNumber = new Workload.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskDate = new Workload.MaskDate();
	maskDate.enable();
	
	var security = new Workload.Security();
	security.enable();
	
});