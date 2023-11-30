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

<title>SevenCows | Editar Despesa</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div
		class="container my-4 px-5 col-sm-10 col-md-8 col-lg-6 col-xl-4 bg-light rounded py-3">

		<h2 class="my-4">Editar daspesa</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<form action="DespesaController" method="post">

			<input type="hidden" name="acao" value="editar"> <input
				type="hidden" value="${despesa.codigo}" name="codigo"> <input
				type="hidden" value="${despesa.codigoUsuario}" name="codigoUsuario"
				required>

			<div class="mb-3">
				<label for="id-descricao" class="form-label">Descrição</label> <input
					type="text" class="form-control" id="id-descricao" name="descricao"
					onfocus="this.value='';" value="${despesa.descricao}" required>
			</div>

			<div class="mb-3">
				<label for="id-data" class="form-label">Data</label> <input
					type="date" class="form-control" id="id-data" name="data" required
					value="${data}">
			</div>

			<div class="mb-3">
				<label for="id-valor" class="form-label">Valor (R$)</label> <input
					type="number" class="form-control" id="id-valor" name="valor"
					onfocus="this.value='';" value="${despesa.valor}" step="0.01"
					inputmode="decimal" min="0.00">
			</div>

			<div class="mb-3">
				<label for="id-frequencia">Frequência</label> <select
					name="frequencia" id="id-frequencia" class="form-control">
					<option disabled value="">Selecione uma opção</option>
					<c:forEach items="${frequencias}" var="f">
						<option value="${f.codigo}">${f.descricao}</option>
					</c:forEach>
				</select>
			</div>


			<button type="submit" class="btn btn-primary">Editar</button>

			<a href="DespesaController?acao=listar" class="btn btn-danger">Voltar</a>

		</form>

	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>