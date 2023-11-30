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

<title>SevenCows | Configurações Conta</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div class="container">

		<h2 class="my-4">Configurações da conta</h2>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<div class="container my-4 px-5 bg-light rounded py-3">

			<h3>Editar nome</h3>

			<p>Você pode alterar seu nome de usário.</p>

			<a href="UsuarioController?acao=abrir-editar-nome" class="btn btn-primary">Alterar nome</a>

		</div>

		<div class="container my-4 px-5 bg-light rounded py-3">

			<h3>Alterar senha</h3>

			<p>Você pode alterar sua senha.</p>

			<a href="UsuarioController?acao=abrir-editar-senha" class="btn btn-primary">Alterar senha</a>

		</div>

		<div class="container my-4 px-5 bg-light rounded py-3">

			<h3>Excluir conta</h3>

			<p>Você pode excluir sua conta. Ao excluir sua conta, todas as
				suas informações cadastradas serão perdidas. Só clique em "Excluir
				conta" se tiver certeza!</p>

			<a href="UsuarioController?acao=abrir-excluir-usuario" class="btn btn-primary">Excluir conta</a>

		</div>

	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>
