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

<title>SevenCows | Excluir Conta</title>

<%@ include file="/WEB-INF/includes/header.jsp"%>

</head>

<body class="bg-success text-dark bg-opacity-10">

	<header>

		<%@ include file="/WEB-INF/includes/menu.jsp"%>

	</header>

	<div class="container my-4 px-5 col-sm-10 col-md-8 col-lg-6 col-xl-4 bg-light rounded py-1">

		<h2 class="my-4">Excluir conta</h2>

		<p>Leia atentamente!</p>

		<p>Ao excluir sua conta, você não tera mais acesso as suas
			informações cadastradas! Todos os dados serão destruídos ao confirmar
			a exclusão. Mas fique tranquilo, você pode voltar a qualquer momento!</p>
			
		<p>Preencha o formulário para excluir sua conta.</p>
		
		<%@ include file="/WEB-INF/includes/mensagem.jsp"%>

		<div class="container">

			<form action="UsuarioController" method="post" class="my-5">

				<input type="hidden" name="acao" value="excluir-conta">
				
				<div class="mb-3">
					<label for="id-email" class="form-label">Email</label> <input
						type="text" class="form-control" id="id-email" name="email"
						value="exemplo@email.com" onfocus="this.value='';" required>
				</div>

				<div class="mb-3">
					<label for="id-senha" class="form-label">Senha</label> <input
						type="password" class="form-control" id="id-senha" name="senha"
						value="********" onfocus="this.value='';" required>
				</div>

				<button type="submit" class="btn btn-primary">Excluir</button>
				
				<a href="usuario-configuracao.jsp" class="btn btn-danger">Voltar</a>

			</form>

		</div>

	</div>

	<footer>

		<%@ include file="/WEB-INF/includes/footer.jsp"%>

	</footer>

</body>

</html>
