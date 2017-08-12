<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<fmt:setLocale value="pt_BR" />
<table class="table table-hover table-condensed table-striped table-bordered">
		<thead>
				<tr>
						<td style="width: 10%">#</td>
						<td style="width: 30%">Nome</td>
						<td style="width: 10%">Preço</td>
						<td style="width: 10%">Categoria</td>
						<td style="width: 20%">Ingredientes</td>
						<td style="width: 10%">Editar</td>
						<td style="width: 10%">Deletar</td>
				</tr>
		</thead>
		<tbody>
				<c:forEach items="${pizzas}" var="pizza">
						<tr data-id="${pizza.id}">
								<td>${pizza.id}</td>
								<td>${pizza.nome}</td>
								<td><fmt:formatNumber value="${pizza.preco}" type="currency" /></td>
								<td>${pizza.categoria}</td>
								<td>
										<c:forEach items="${pizza.ingredientes}" var="ingrediente">
												${ingrediente.nome}<br />
										</c:forEach>
								</td>
								<td><button type="button" class="btn btn-warning btn-editar">
										<spring:message code="views.pizzas.tabela.coluna.editar"></spring:message>	
								</button></td>
								<td><button type="button" class="btn btn-danger btn-deletar">
										<spring:message code="views.pizzas.tabela.coluna.remover"></spring:message>
								</button></td>
						</tr>
				</c:forEach>
		</tbody>
		<tfoot>
				<tr>
						<td colspan="7">Pizzas Cadastrados: <span id="quantidade-pizzas">${pizzas.size()}</span></td>
				</tr>
				<tr>
						<td colspan="7">
								<button type="button" class="btn btn-primary" data-toggle="modal"
										data-target="#modal-pizza">Cadastrar Pizzas</button>
						</td>
				</tr>
		</tfoot>
</table>