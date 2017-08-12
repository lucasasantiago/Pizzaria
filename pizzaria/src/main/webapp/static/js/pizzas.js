$(document).ready(function() {

	aplicarListeners();
	
	aplicarListenerBtnSalvar();

});

var aplicarListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'pizzas';
		var dadosPizza = $('#form-pizza').serialize();

		$.post(url, dadosPizza).done(function(pagina) {
			$('#secao-pizzas').html(pagina)
			aplicarListeners();

		}).fail(function() {
			alert('Erro ao salvar!');

		}).always(function() {
			$('#modal-pizza').modal('hide');
		});
	});
}

var limparModal = function(){
	$('#id').val('');
	$('#nome').val('');
	$('#preco').val('');
	$('#categoria option').attr('selected', false);
	$('#ingredientes option').attr('selected', false);
};

var aplicarListeners = function() {
	$('#modal-pizza').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'pizzas/'+id;
		
		$.get(url)
			.then(function (pizza){
			    
				$('#id').val(pizza.id);
				$('#nome').val(pizza.nome);
				$('#preco').val(pizza.preco);
				$('#categoria').val(pizza.categoria);
				
				pizza.ingredientes.forEach(function(ingrediente){
					var id = ingrediente.id;
					$('#ingredientes option[value='+id+']').attr('selected', true);
				});
				
				$('#modal-pizza').modal('show');
		   });
	});
	
	$('.btn-deletar').on('click', function() {
			var id = $(this).parents('tr').data('id');
			var csrf = $('#csrf').val();
			
			$.ajax({
				url : "pizzas/" + id,
				type : 'DELETE',
				headers: {'X-CSRF-TOKEN': csrf},
				success : function() {
					$('tr[data-id="' + id + '"]').remove();
					var pizzas = parseInt($('#quantidade-pizzas').text());
					$('#quantidade-pizzas').text(pizzas - 1);
				}
			});
		});
}
