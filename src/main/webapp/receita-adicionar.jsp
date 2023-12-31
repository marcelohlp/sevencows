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

<title>SevenCows | Nova Receita</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div
		class="container my-4 px-5 col-sm-10 col-md-8 col-lg-6 col-xl-4 bg-light rounded py-3">

		<h2 class="my-4">Nova receita</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<form action="ReceitaController" method="post">

			<input type="hidden" name="acao" value="adicionar">

			<div class="mb-3">
				<label for="id-descricao" class="form-label">Descri��o</label> <input
					type="text" class="form-control" id="id-descricao" name="descricao"
					required placeholder="Sua receita">
			</div>

			<div class="mb-3">
				<label for="id-data" class="form-label">Data</label> <input
					type="date" class="form-control" id="id-data" name="data" required
					value="${data}">
			</div>

			<div class="mb-3">
				<label for="id-valor" class="form-label">Valor (R$)</label> <input
					type="number" class="form-control" id="id-valor" name="valor"
					step="0.01" inputmode="decimal" required placeholder="0,00" min="0.00">
			</div>

			<div class="mb-3">
				<label for="id-frequencia">Frequ�ncia</label> <select
					name="frequencia" id="id-frequencia" class="form-control">
					<option disabled selected value="">Selecione uma op��o</option>
					<c:forEach items="${frequencias}" var="f">
						<option value="${f.codigo}">${f.descricao}</option>
					</c:forEach>
				</select>
			</div>


			<button type="submit" class="btn btn-primary">Adicionar</button>

			<a href="ReceitaController?acao=listar" class="btn btn-danger">Voltar</a>

		</form>

	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>