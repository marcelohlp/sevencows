package br.com.sevencows.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sevencows.dao.ReservaDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.exception.ValorException;
import br.com.sevencows.factory.DAOFactory;
import br.com.sevencows.model.RelatorioFinanceiro;
import br.com.sevencows.model.Reserva;
import br.com.sevencows.util.ConversorNumerico;
import br.com.sevencows.util.OrdenadorLista;
import br.com.sevencows.util.ValidadorDado;

@WebServlet("/ReservaController")
public class ReservaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReservaDAO reservaDAO;

	public ReservaController() {
		super();
	}

	public void init() {
		reservaDAO = DAOFactory.getReservaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession hs = request.getSession();

		int codigoUsuario = (int) hs.getAttribute("codigoUsuario");

		String acao = request.getParameter("acao");

		switch (acao) {

		case "listar":

			listar(request, response, codigoUsuario);
			break;

		case "abrir-form-cadastro":

			abrirFormCadastro(request, response);
			break;

		case "abrir-form-edicao":

			abrirFormEdicao(request, response);
			break;

		}

	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = ConversorNumerico.stringInt(request.getParameter("codigo"));

		Reserva reserva = reservaDAO.buscar(codigo);

		request.setAttribute("reserva", reserva);

		request.getRequestDispatcher("reserva-editar.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response, int codigoUsuario)
			throws ServletException, IOException {

		List<Reserva> lista = reservaDAO.listar(codigoUsuario);
		
		OrdenadorLista.ordenarValor(lista);
		
		RelatorioFinanceiro relatorio = new RelatorioFinanceiro(null, null, lista);

		request.setAttribute("reservas", lista);
		
		request.setAttribute("relatorio", relatorio);

		request.getRequestDispatcher("reserva-listar.jsp").forward(request, response);
	}

	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("reserva-adicionar.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession hs = request.getSession();

		int codigoUsuario = (int) hs.getAttribute("codigoUsuario");

		String acao = request.getParameter("acao");

		switch (acao) {

		case "adicionar":

			adicionar(request, response, codigoUsuario);
			break;

		case "editar":

			editar(request, response, codigoUsuario);
			break;

		case "excluir":

			excluir(request, response, codigoUsuario);
			break;

		}

	}

	private void excluir(HttpServletRequest request, HttpServletResponse response, int codigoUsuario)
			throws ServletException, IOException {
		int codigo = ConversorNumerico.stringInt(request.getParameter("codigo"));

		try {

			reservaDAO.excluir(codigo);

			request.setAttribute("mensagem", "Reserva removida com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao excluir reserva!");

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Dados inválidos!");

		}

		listar(request, response, codigoUsuario);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response, int codigoUsuario)
			throws ServletException, IOException {
		try {

			int codigo = ConversorNumerico.stringInt(request.getParameter("codigo"));
			String descricao = request.getParameter("descricao");
			double valor = ConversorNumerico.stringDouble(request.getParameter("valor-atual"));
			String objetivo = request.getParameter("objetivo");
			double valorMeta = ConversorNumerico.stringDouble(request.getParameter("valor-meta"));

			ValidadorDado.validaValor(valor);
			ValidadorDado.validaValor(valorMeta);
			
			Reserva reserva = new Reserva(codigo, codigoUsuario, descricao, valor, objetivo, valorMeta);

			reservaDAO.atualizar(reserva);

			request.setAttribute("mensagem", "Reserva atualizada com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao atualizar reserva!");

		} catch (ValorException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Valor menor do que zero!");

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Dados inválidos!");

		}

		listar(request, response, codigoUsuario);
	}

	private void adicionar(HttpServletRequest request, HttpServletResponse response, int codigoUsuario)
			throws ServletException, IOException {
		
		try {

			String descricao = request.getParameter("descricao");
			String objetivo = request.getParameter("objetivo");
			double valorAtual = ConversorNumerico.stringDouble(request.getParameter("valor-atual"));
			double valorMeta = ConversorNumerico.stringDouble(request.getParameter("valor-meta"));

			ValidadorDado.validaValor(valorAtual);
			ValidadorDado.validaValor(valorMeta);
			
			Reserva reserva = new Reserva(codigoUsuario, descricao, valorAtual, objetivo, valorMeta);

			reservaDAO.inserir(reserva);

			request.setAttribute("mensagem", "Reserva cadastrada com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao cadastrar reserva!");

		} catch (ValorException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Valor menor do que zero!");

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Dados inválidos!");

		}

		abrirFormCadastro(request, response);

	}

}
