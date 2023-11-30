<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>SevenCows | Login</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<h1 class="container d-flex justify-content-center my-5 fs-1 fw-bold"><span class="text-success">Seven</span>Cows</h1>

	</header>

	<div class="container my-4 px-5 col-sm-10 col-md-8 col-lg-6 col-xl-4 bg-light rounded py-1">

		<h2 class="my-4">Login</h2>

		<p>Informe seus dados</p>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<form action="UsuarioController" method="get" >
		
			<input type="hidden" name="acao" value="login">

			<div class="mb-3">
				<label for="id-email" class="form-label">Email</label> <input
					type="email" class="form-control" id="id-email" name="email"
					value="exemplo@email.com" onfocus="this.value='';" required>
			</div>

			<div class="mb-3">
				<label for="id-senha" class="form-label">Senha</label> <input
					type="password" class="form-control" id="id-senha" name="senha"
					value="*********" onfocus="this.value='';" required>
			</div>

			<div class="d-flex justify-content-center">

				<button type="submit" class="btn btn-primary">Entrar</button>

			</div>


		</form>


		<div class="container mt-5 d-flex justify-content-center">

			<p>
				Não é cadastrado? <a href="usuario-cadastro.jsp">Cadastre-se!</a>
			</p>

		</div>


	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>