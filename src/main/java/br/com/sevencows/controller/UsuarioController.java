package br.com.sevencows.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sevencows.dao.DespesaDAO;
import br.com.sevencows.dao.ReceitaDAO;
import br.com.sevencows.dao.ReservaDAO;
import br.com.sevencows.dao.UsuarioDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.factory.DAOFactory;
import br.com.sevencows.model.Usuario;
import br.com.sevencows.util.Criptografia;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO;

	public UsuarioController() {
		super();
	}

	public void init() {
		usuarioDAO = DAOFactory.getUsuarioDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession hs = request.getSession();

		String acao = request.getParameter("acao");

		switch (acao) {

		case "login":

			login(request, response, hs);
			break;

		case "logout":

			logout(response, hs);
			break;

		case "abrir-editar-nome":

			abrirEditarNome(request, response, hs);
			break;
			
		case "abrir-editar-senha":
			
			abrirEditarSenha(request, response);
			break;
			
		case "abrir-excluir-usuario":
			
			abrirExcluirConta(request, response);
			break;

		}

	}

	private void abrirExcluirConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("usuario-excluir-conta.jsp").forward(request, response);
	}

	private void abrirEditarSenha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("usuario-editar-senha.jsp").forward(request, response);
	}

	private void abrirEditarNome(HttpServletRequest request, HttpServletResponse response, HttpSession hs)
			throws ServletException, IOException {
		
		int codigoUsuario = (int) hs.getAttribute("codigoUsuario");
		
		Usuario usuario = usuarioDAO.buscar(codigoUsuario);

		request.setAttribute("usuario", usuario);

		request.getRequestDispatcher("usuario-editar-nome.jsp").forward(request, response);
	}

	private void logout(HttpServletResponse response, HttpSession hs) throws IOException {
		
		hs.invalidate();

		response.sendRedirect("index.jsp");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response, HttpSession hs)
			throws ServletException, IOException {
		
		try {

			String email = (String) request.getParameter("email");
			String senha = (String) request.getParameter("senha");

			Usuario usuario = usuarioDAO.buscar(email);

			if (usuario != null) {

				if (usuario.validaSenha(senha)) {

					hs.setAttribute("nome", usuario.getNome());
					hs.setAttribute("codigoUsuario", usuario.getCodigo());
					response.sendRedirect("RelatorioFinanceiroController");

				} else {

					request.setAttribute("erro", "Senha invalida!");

					request.getRequestDispatcher("usuario-login.jsp").forward(request, response);

				}

			} else {

				request.setAttribute("erro", "Email invalido!");

				request.getRequestDispatcher("usuario-login.jsp").forward(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao realizar o login!");

			request.getRequestDispatcher("usuario-login.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession hs = request.getSession();

		String acao = request.getParameter("acao");

		switch (acao) {

		case "cadastrar":

			cadastrar(request, response);
			break;
			
		case "editar-nome":
			
			editarNome(request, response, hs);
			break;
			
		case "editar-senha":
			
			editarSenha(request, response, hs);
			break;
			
		case "excluir-conta":
			
			excluir(request, response, hs);
			break;
			
		}
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response, HttpSession hs)
			throws ServletException, IOException {
		DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
		ReceitaDAO receitaDAO = DAOFactory.getReceitaDAO();
		ReservaDAO reservaDAO = DAOFactory.getReservaDAO();
		
		try {
			
			int codigoUsuario = (int) hs.getAttribute("codigoUsuario");

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			Usuario usuario = usuarioDAO.buscar(codigoUsuario);

			if (email.equals(usuario.getEmail())) {

				if (usuario.validaSenha(senha)) {

					despesaDAO.excluirTodos(codigoUsuario);
					receitaDAO.excluirTodos(codigoUsuario);
					reservaDAO.excluirTodos(codigoUsuario);
					usuarioDAO.excluir(codigoUsuario);
					
					hs.invalidate();
					
					request.setAttribute("mensagem", "Sua conta foi excluída com sucesso!");
					
				} else {

					request.setAttribute("erro", "Senha inválida!");
					
					abrirExcluirConta(request, response);

				}

			} else {

				request.setAttribute("erro", "Email inválido!");
				
				abrirExcluirConta(request, response);

			}

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao excluir usuário!");
			
			abrirExcluirConta(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Dados inválidos!");
			
			abrirExcluirConta(request, response);

		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private void editarSenha(HttpServletRequest request, HttpServletResponse response, HttpSession hs)
			throws ServletException, IOException {
		int codigoUsuario = (int) hs.getAttribute("codigoUsuario");
		
		try {

			String senha = request.getParameter("senha");
			String novaSenha = request.getParameter("nova-senha");
			String novaSenhaRepetir = request.getParameter("nova-senha-repetir");
			
			if (novaSenha.equals(novaSenhaRepetir)) {
				
				Usuario usuario = usuarioDAO.buscar(codigoUsuario);
				
				if (usuario.validaSenha(senha)) {
					
					String emailUsuario = usuario.getEmail();
					String nmUsuario = usuario.getNome();
					String novaSenhaCripto = Criptografia.criptografar(novaSenha);
					
					Usuario usuarioAtualizado = new Usuario(codigoUsuario, emailUsuario, nmUsuario, novaSenhaCripto);
					
					usuarioDAO.atualizar(usuarioAtualizado);
					
					request.setAttribute("mensagem", "Senha atualizada com sucesso!");
					
				} else {
					
					request.setAttribute("erro", "Senha inválida!");
					
					abrirEditarSenha(request, response);
					
				}
				
			} else {
				
				request.setAttribute("erro", "Você deve repetir a senha exatamente como no campo anterior!");
				
				abrirEditarSenha(request, response);
				
			}

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao atualizar usuário!");
			
			abrirEditarSenha(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Dados inválidos!");
			
			abrirEditarSenha(request, response);

		}

		request.getRequestDispatcher("usuario-configuracao.jsp").forward(request, response);
	}

	private void editarNome(HttpServletRequest request, HttpServletResponse response, HttpSession hs)
			throws ServletException, IOException {
		try {
			
			int codigoUsuario = (int) hs.getAttribute("codigoUsuario");

			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");

			Usuario usuario = usuarioDAO.buscar(codigoUsuario);

			if (usuario.validaSenha(senha)) {

				int cdUsuario = usuario.getCodigo();
				String nmUsuario = nome;
				String emailUsuario = usuario.getEmail();

				Usuario usuarioAtualizado = new Usuario(cdUsuario, emailUsuario, nmUsuario, usuario.getSenha());

				usuarioDAO.atualizar(usuarioAtualizado);

				hs.setAttribute("nome", usuarioAtualizado.getNome());
				hs.setAttribute("codigoUsuario", usuario.getCodigo());

				request.setAttribute("mensagem", "Nome atualizado com sucesso!");

			} else {

				request.setAttribute("erro", "Senha inválida!");
				
				abrirEditarNome(request, response, hs);
				
			}

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao atualizar usuário!");
			
			abrirEditarNome(request, response, hs);
			
		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Dados inválidos!");
			
			abrirEditarNome(request, response, hs);
			
		}

		request.getRequestDispatcher("usuario-configuracao.jsp").forward(request, response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String senhaConfirmacao = request.getParameter("repita-senha");

		if (usuarioDAO.buscar(email) == null) {

			if (senha.equals(senhaConfirmacao)) {

				String senhaCriptografada = Criptografia.criptografar(senha);

				Usuario usuario = new Usuario(email, nome, senhaCriptografada);

				try {

					usuarioDAO.inserir(usuario);

					request.setAttribute("mensagem",
							"Cadastro realizado com sucesso! Faça o login para acessar sua conta.");

					request.getRequestDispatcher("usuario-login.jsp").forward(request, response);

				} catch (DatabaseException e) {

					e.printStackTrace();
					System.err.println(e.getMessage());

					request.setAttribute("erro", "Erro ao cadastrar Usuário!");

					request.getRequestDispatcher("usuario-cadastro.jsp").forward(request, response);

				} catch (Exception e) {

					e.printStackTrace();
					System.err.println(e.getMessage());

					request.setAttribute("erro", "Dados inválidos!");

					request.getRequestDispatcher("usuario-cadastro.jsp").forward(request, response);

				}

			} else {

				request.setAttribute("erro", "Você precisa confirmar a sua senha!");

				request.getRequestDispatcher("usuario-cadastro.jsp").forward(request, response);

			}

		} else {

			request.setAttribute("erro", "Este e-mail já pertence a outro usuário!");

			request.getRequestDispatcher("usuario-cadastro.jsp").forward(request, response);

		}
	}

}
