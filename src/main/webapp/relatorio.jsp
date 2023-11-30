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

<title>SevenCows | Meu Relatório</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div class="container table-responsive">

		<h2 class="my-4">Relatório</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<div class="container my-4 px-5 bg-light rounded py-3 text-center">

			<h3>Selecione o período</h3>

			<form action="RelatorioFinanceiroController" method="get">

				<div class="d-flex justify-content-center my-3">

					<div class="mb-3 me-3">
						<label for="id-data-inicial" class="form-label fs-5">Data
							inicial</label> <input type="date" class="form-control"
							id="id-data-inicial" name="data-inicial" value="${dtInicial}">
					</div>

					<div class="mb-3">
						<label for="id-data-final" class="form-label fs-5">Data final</label> <input
							type="date" class="form-control" id="id-data-final"
							name="data-final" value="${dtFinal}">
					</div>

				</div>

				<button type="submit" class="btn btn-primary">Buscar</button>

			</form>

		</div>

		<div class="container my-4 px-5 bg-light rounded py-3">

			<h3>Seu relatório</h3>

			<p>De ${dataInicial} a ${dataFinal}</p>
			
			<h4>Tabela de resultado financeiro</h4>

			<table class="table table-striped align-middle mb-5 table-hover">

				<thead>
					<tr class="table-success">
						<th scope="col">Total receitas (R$)</th>
						<th scope="col">Total despesas (R$)</th>
						<th scope="col">Resultado (R$)</th>
					</tr>
				</thead>

				<tbody>

					<tr>
						<td><fmt:formatNumber value="${relatorio.valorTotalReceitas}"
								type="currency" /></td>
						<td><fmt:formatNumber value="${relatorio.valorTotalDespesas}"
								type="currency" /></td>
						<td><fmt:formatNumber value="${relatorio.valorResultado}"
								type="currency" /></td>
					</tr>


				</tbody>

			</table>

			<h4>Tabela de resultado de reservas</h4>

			<table class="table table-striped align-middle mb-3 table-hover">

				<thead>
					<tr class="table-success">
						<th scope="col">Valor atual de reservas (R$)</th>
						<th scope="col">Valor meta de reservas (R$)</th>
						<th scope="col">Diferença (R$)</th>
					</tr>
				</thead>


				<tbody>

					<tr>
						<td><fmt:formatNumber value="${relatorio.valorAtualReservas}"
								type="currency" /></td>
						<td><fmt:formatNumber value="${relatorio.valorObjetivoReservas}"
								type="currency" /></td>
						<td><fmt:formatNumber value="${relatorio.valorDiferencaReserva}"
								type="currency" /></td>
					</tr>


				</tbody>

			</table>

		</div>

	</div>


	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>
