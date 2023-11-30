<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.Objects"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>SevenCows</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<div class="container text-center my-5 d-flex flex-column">

		<h1
			class="container d-flex justify-content-center my-5 py-4 fs-1 fw-bold">
			<span class="text-success">Seven</span>Cows
		</h1>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<h2 class="my-5 py-3 fs-3">O seu organizador financeiro!</h2>


		<div class="my-5 py-4">

			<a href="usuario-login.jsp" class="btn btn-outline-success me-2">Entrar</a>

			<a href="usuario-cadastro.jsp" class="btn btn-success ms-2">Cadastrar-se</a>

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>


</body>

</html>
