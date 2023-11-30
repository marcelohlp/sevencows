<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>SevenCows | Cadastre-se</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<h1 class="container d-flex justify-content-center my-5 fs-1 fw-bold">
			<span class="text-success">Seven</span>Cows
		</h1>

	</header>

	<div
		class="container my-4 px-5 col-sm-10 col-md-8 col-lg-6 col-xl-4 bg-light rounded py-1">

		<h2 class="my-4">Cadastre-se!</h2>

		<p>Informe seus dados para realizar o cadastro</p>

		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<form action="UsuarioController" method="post">

			<input type="hidden" name="acao" value="cadastrar">

			<div class="mb-3">
				<label for="id-nome" class="form-label">Nome</label> <input
					type="text" class="form-control" id="id-nome" name="nome"
					value="nome" onfocus="this.value='';" required>
			</div>

			<div class="mb-3">
				<label for="id-email" class="form-label">Email</label> <input
					type="email" class="form-control" id="id-email" name="email"
					value="exemplo@email.com" onfocus="this.value='';" required>
			</div>

			<div class="mb-3">
				<label for="id-senha" class="form-label">Senha</label> <input
					type="password" class="form-control" id="id-senha" name="senha"
					value="********" onfocus="this.value='';" required>
			</div>

			<div class="mb-3">
				<label for="id-repita-senha" class="form-label">Confirme sua
					senha</label> <input type="password" class="form-control"
					id="id-repita-senha" name="repita-senha" value="********"
					onfocus="this.value='';" required>
			</div>

			<div class="d-flex justify-content-center">

				<button type="submit" class="btn btn-primary">Cadastrar</button>

			</div>


		</form>


		<div class="container mt-5 d-flex justify-content-center">

			<p>
				Tem uma conta? Faça seu <a href="usuario-login.jsp">Login</a>
			</p>

		</div>


	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>