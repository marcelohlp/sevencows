<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>SevenCows | Minhas Reservas</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div class="container table-responsive">

		<h2 class="my-4">Minhas reservas</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<div class="container my-4 px-5 bg-light rounded py-3">

			<h3>Lista de reservas</h3>

			<c:if test="${not empty reservas}">

				<table class="table table-striped align-middle table-hover">

					<thead>
						<tr class="table-success">
							<th scope="col">Descrição</th>
							<th scope="col">Objetivo</th>
							<th scope="col">Valor Atual (R$)</th>
							<th scope="col">Valor Meta (R$)</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${reservas}" var="r">

							<tr>

								<td>${r.descricao}</td>
								<td>${r.objetivo}</td>
								<td><fmt:formatNumber value="${r.valor}" type="currency" /></td>
								<td><fmt:formatNumber value="${r.valorObjetivo}"
										type="currency" /></td>
								<td><c:url value="ReservaController" var="link">
										<c:param name="acao" value="abrir-form-edicao" />
										<c:param name="codigo" value="${r.codigo}" />
									</c:url> <a href="${link}" class="btn btn-primary">Editar</a></td>
								<td>
									<form action="ReservaController" method="post">

										<input type="hidden" name="acao" value="excluir"> <input
											type="hidden" value="${r.codigo}" name="codigo">

										<button type="submit" class="btn btn-danger">Excluir</button>

									</form>

								</td>

							</tr>

						</c:forEach>

					</tbody>

				</table>
				
				<p class="fs-3">Total atual: <fmt:formatNumber value="${relatorio.valorAtualReservas}" type="currency" /></p>
				<p class="fs-3">Total meta: <fmt:formatNumber value="${relatorio.valorObjetivoReservas}" type="currency" /></p>

			</c:if>

			<c:if test="${empty reservas}">

				<p class="alert alert-danger">Você não possui reservas</p>

			</c:if>


		</div>

		<a href="ReservaController?acao=abrir-form-cadastro"
			class="btn btn-primary">Adicionar Reserva</a>

	</div>


	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>
