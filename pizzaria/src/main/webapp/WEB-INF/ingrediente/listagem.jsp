<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titulo}</title>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"></c:set>

<style type="text/css">
@import url("${path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css");

@import url("${path}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css");
</style>
</head>
<body>
			<div class="container">
						<jsp:include page="../../menu.jsp"></jsp:include>
						<c:if test="${not empty mensagemErro}">
									<div>
												<div class="alert alert-danger alert-dismissible fade show">
															<button type="button" class="close" data-dismiss="alert" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
															</button>
															${mensagemErro}
												</div>
									</div>
						</c:if>
						<c:if test="${not empty mensagemSucesso}">
									<div>
												<div class="alert alert-success alert-dismissible fade show" role="alert">
															<button type="button" class="close" data-dismiss="alert" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
															</button>
															${mensagemSucesso}
												</div>
									</div>
						</c:if>

						<section id="secao-ingredientes">
									<jsp:include page="tabela-ingrediente.jsp"></jsp:include>
						</section>

						<jsp:include page="modal-ingrediente.jsp"></jsp:include>
			</div>
			
			<script type="text/javascript" src="${path}/static/js/jquery-3.2.1.min.js"></script>
			<script type="text/javascript" src="${path}/static/js/tether.min.js"></script>
			<script type="text/javascript"
						src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="${path}/static/js/ingredientes.js"></script>
</body>
</html>