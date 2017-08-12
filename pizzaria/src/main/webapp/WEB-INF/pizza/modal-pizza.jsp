<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal" id="modal-pizza" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" role="document">
				<div class="modal-content">
						<form id="form-pizza" method="post">
								<div class="modal-header">
										<h5 class="modal-title">Informações da Pizza</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
										</button>
								</div>
								<div class="modal-body">
								
										<label for="nome">Nome: </label> 
										<input id="nome" name="nome" class="form-control"> 
										
										<label for="preco">Preço: </label> 
										<input id="preco" name="preco" class="form-control"> 
										
										<label for="categoria">Categoria: </label> 
										<select id="categoria" name="categoria" class="form-control">
												<c:forEach items="${categorias}" var="categoria">
														<option value="${categoria}">${categoria}</option>
												</c:forEach>
										</select> 
										
										<label for="ingredientes">Ingredientes: </label> 
										<select id="ingredientes" name="ingredientes" class="form-control" multiple="multiple">
												<c:forEach items="${ingredientes}" var="ingrediente">
														<option value="${ingrediente.id}">${ingrediente.nome}</option>
												</c:forEach>
										</select> 
										
										<input id="csrf" name="_csrf" value="${_csrf.token}" type="hidden">
										
								</div>
								<div class="modal-footer">
										<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
										<button id="btn-salvar" type="button" class="btn btn-primary">Salvar</button>
								</div>
						</form>
				</div>
		</div>
</div>