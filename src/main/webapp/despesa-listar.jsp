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

<title>SevenCows | Minhas Despesas</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div class="container table-responsive">

		<h2 class="my-4">Minhas despesas</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<div class="container my-4 px-5 bg-light rounded py-3 text-center">

			<h3>Selecione o período</h3>

			<form action="DespesaController" method="get">

				<input type="hidden" name="acao" value="listar">

				<div class="d-flex justify-content-center my-3">

					<div class="mb-3 me-5">
						<label for="id-data-inicial" class="form-label fs-5">Data
							inicial</label> <input type="date" class="form-control"
							id="id-data-inicial" name="data-inicial" value="${dtInicial}">
					</div>

					<div class="mb-3 ms-5">
						<label for="id-data-final" class="form-label fs-5">Data final</label> <input
							type="date" class="form-control" id="id-data-final"
							name="data-final" value="${dtFinal}">
					</div>

				</div>

				<button type="submit" class="btn btn-primary">Buscar</button>

			</form>

		</div>

		<div class="container my-4 px-5 bg-light rounded py-3">

			<h3>Lista de despesas</h3>

			<p>De ${dataInicial} a ${dataFinal}</p>

			<c:if test="${not empty despesas}">

				<table class="table table-striped align-middle table-hover">

					<thead>
						<tr class="table-success">
							<th scope="col">Descrição</th>
							<th scope="col">Data</th>
							<th scope="col">Valor</th>
							<th scope="col">Frequência</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${despesas}" var="d">

							<tr>

								<td>${d.descricao}</td>
								<td><fmt:parseDate value="${d.data}" pattern="yyyy-MM-dd"
										var="data" type="date" /> <fmt:formatDate value="${data}"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatNumber value="${d.valor}" type="currency" /></td>
								<td>${d.frequencia.descricao}</td>
								<td><c:url value="DespesaController" var="link">
										<c:param name="acao" value="abrir-form-edicao" />
										<c:param name="codigo" value="${d.codigo}" />
									</c:url> <a href="${link}" class="btn btn-primary">Editar</a></td>
								<td>
									<form action="DespesaController" method="post">

										<input type="hidden" name="acao" value="excluir"> <input
											type="hidden" value="${d.codigo}" name="codigo">
										
										<button type="submit" class="btn btn-danger">Excluir</button>

									</form>

								</td>

							</tr>

						</c:forEach>

					</tbody>

				</table>
				
				<p class="fs-3">Total despesas: <fmt:formatNumber value="${relatorio.valorTotalDespesas}" type="currency" /></p>

			</c:if>

			<c:if test="${empty despesas}">

				<p class="alert alert-danger">Você não possui despesas
					cadastradas no período selecionado</p>

			</c:if>


		</div>

		<a href="DespesaController?acao=abrir-form-cadastro"
			class="btn btn-primary">Adicionar Despesa</a>

	</div>


	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>
