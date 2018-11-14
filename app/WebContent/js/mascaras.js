$(document).ready(function(){
	$('#cep').mask('00000-000');
	$('#data').mask('0000-00-00');
	$('#numero').mask('#');
	var maskBehavior = function (val) {
		return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
	},options = {onKeyPress: function(val, e, field, options) {
			field.mask(maskBehavior.apply({}, arguments), options);
	}
	}; 
	$('#telefone').mask(maskBehavior, options);
});