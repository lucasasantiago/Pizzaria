//Js para renderizar somente os dados da tabela de ingrediente

$(document).ready(function() {
	aplicarListeners();

	aplicarListenerBtnSalvar();
});

var aplicarListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();
		
		$.post(url, dadosIngrediente).done(function(pagina) {
			$('#secao-ingrediente').html(pagina);
			aplicarListeners();
		}).fail(function() {
			alert('Erro ao salvar.');
		}).always(function() {
			$('#modal-ingrediente').modal('hide');
		});

		/*
		$.ajax({
			method : "POST",
			url: url,
			data: dadosIngrediente,
			//processData: false,
	        //contentType: false,
			success : function(pagina) {
				alert("success");
				$('#secao-ingrediente').html(pagina);
				aplicarListeners();
			},
		}).fail(function() {
			alert('Erro ao salvar.');
		}).always(function() {
			$('#modal-ingrediente').modal('hide');
		});
		*/

	});
}

var limparModal = function() {
	$('#id').val('');
	$('#nome').val('');
	$('#categoria').val('');
}

var aplicarListeners = function() {

	$('#modal-ingrediente').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'ingredientes/' + id;

		$.ajax({
			method : "GET",
			url : url,
			success : function(ingrediente) {

				$('#id').val(ingrediente.id);
				$('#nome').val(ingrediente.nome);
				$('#categoria').val(ingrediente.categoria);

				$('#modal-ingrediente').modal('show');
			}
		});

		/*
		 * $.get(url) .success(function(ingrediente) {
		 * console.log(ingrediente.id); console.log(ingrediente.nome);
		 * console.log(ingrediente.categoria); });
		 */
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var ingredientes = $('#quantidade-ingredientes').text();

		$.ajax({
			url : "ingredientes/" + id,
			type : "DELETE",
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
				$('#quantidade-ingredientes').text(ingredientes - 1);
			}
		});
	});
}