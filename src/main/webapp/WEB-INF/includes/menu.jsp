<nav class="navbar navbar-expand-lg bg-success">
	<div class="container-fluid">
		<h1>
			<a class="navbar-brand text-light fs-3 lh-lg" href="#">SevenCows</a>
		</h1>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link text-light"
					aria-current="page" href="RelatorioFinanceiroController">Relatório</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					aria-current="page" href="ReservaController?acao=listar">Reservas</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					aria-current="page" href="ReceitaController?acao=listar">Receitas</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					aria-current="page" href="DespesaController?acao=listar">Despesas</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					aria-current="page" href="usuario-configuracao.jsp">Configurações</a></li>
			</ul>

			<p class="d-flex me-2 mb-2 mb-lg-0 text-light d-none d-lg-block">Olá,
				${nome}</p>

			<form action="UsuarioController" class="d-flex" method="get">
			
				<input type="hidden" name="acao" value="logout">

				<button class="btn btn-outline-light" type="submit">Sair</button>
			</form>
		</div>
	</div>
</nav>