<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Objects"%>

<%
String nome = (String) session.getAttribute("nome");
if (Objects.isNull(nome)) {
	response.sendRedirect("cadastro-usuario.jsp");
}
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>SevenCows | Editar Reserva</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div
		class="container my-4 px-5 col-sm-10 col-md-8 col-lg-6 col-xl-4 bg-light rounded py-3">

		<h2 class="my-4">Editar Reserva</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<form action="ReservaController" method="post">

			<input type="hidden" name="acao" value="editar"> <input
				type="hidden" value="${reserva.codigo}" name="codigo"> <input
				type="hidden" value="${reserva.codigoUsuario}" name="codigoUsuario">

			<div class="mb-3">
				<label for="id-descricao" class="form-label">Descrição</label> <input
					type="text" class="form-control" id="id-descricao" name="descricao"
					onfocus="this.value='';" value="${reserva.descricao}">
			</div>

			<div class="mb-3">
				<label for="id-objetivo" class="form-label">Objetivo</label> <input
					type="text" class="form-control" id="id-objetivo" name="objetivo"
					onfocus="this.value='';" value="${reserva.objetivo}">
			</div>

			<div class="mb-3">
				<label for="id-valor-atual" class="form-label">Valor atual
					(R$)</label> <input type="number" class="form-control" id="id-valor-atual"
					name="valor-atual" onfocus="this.value='';"
					value="${reserva.valor}" step="0.01" inputmode="decimal" min="0.00">
			</div>

			<div class="mb-3">
				<label for="id-valor-meta" class="form-label">Valor meta
					(R$)</label> <input type="number" class="form-control" id="id-valor-meta"
					name="valor-meta" onfocus="this.value='';"
					value="${reserva.valorObjetivo}" step="0.01" inputmode="decimal" min="0.00">
			</div>


			<button type="submit" class="btn btn-primary">Editar</button>

			<a href="ReservaController?acao=listar" class="btn btn-danger">Voltar</a>

		</form>

	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>