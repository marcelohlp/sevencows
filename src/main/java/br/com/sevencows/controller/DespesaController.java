package br.com.sevencows.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sevencows.dao.DespesaDAO;
import br.com.sevencows.dao.FrequenciaDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.exception.ValorException;
import br.com.sevencows.factory.DAOFactory;
import br.com.sevencows.model.Despesa;
import br.com.sevencows.model.Frequencia;
import br.com.sevencows.model.RelatorioFinanceiro;
import br.com.sevencows.util.ConversorData;
import br.com.sevencows.util.ConversorNumerico;
import br.com.sevencows.util.OrdenadorLista;
import br.com.sevencows.util.ValidadorDado;

@WebServlet("/DespesaController")
public class DespesaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DespesaDAO despesaDAO;
	private FrequenciaDAO frequenciaDAO;

	public DespesaController() {
		super();
	}

	public void init() {
		despesaDAO = DAOFactory.getDespesaDAO();
		frequenciaDAO = DAOFactory.getFrequenciaDAO();
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

		Despesa despesa = despesaDAO.buscar(codigo);
		
		String data = ConversorData.localDateFront(despesa.getData());

		request.setAttribute("despesa", despesa);
		request.setAttribute("data", data);

		List<Frequencia> lista = frequenciaDAO.listar();

		request.setAttribute("frequencias", lista);

		request.getRequestDispatcher("despesa-editar.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response, int codigoUsuario)
			throws ServletException, IOException {
		
		String dataInicial = request.getParameter("data-inicial");
		String dataFinal = request.getParameter("data-final");

		dataInicial = ValidadorDado.validarDataInicial(dataInicial);
		dataFinal = ValidadorDado.validarDataFinal(dataFinal);

		List<Despesa> lista = despesaDAO.listar(codigoUsuario, dataInicial, dataFinal);

		RelatorioFinanceiro relatorio = new RelatorioFinanceiro(null, lista, null);
		
		OrdenadorLista.ordenarData(lista);
		
		String dtInicialPadraoFront = ConversorData.stringBackFront(dataInicial);
		String dtFinalPadraoFront = ConversorData.stringBackFront(dataFinal);

		request.setAttribute("dataInicial", dataInicial);
		request.setAttribute("dataFinal", dataFinal);
		
		request.setAttribute("dtInicial", dtInicialPadraoFront);
		request.setAttribute("dtFinal", dtFinalPadraoFront);
		
		request.setAttribute("relatorio", relatorio);

		request.setAttribute("despesas", lista);

		request.getRequestDispatcher("despesa-listar.jsp").forward(request, response);
	}

	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Frequencia> lista = frequenciaDAO.listar();
		
		String data = ConversorData.localDateFront(LocalDate.now());

		request.setAttribute("frequencias", lista);
		
		request.setAttribute("data", data);

		request.getRequestDispatcher("despesa-adicionar.jsp").forward(request, response);

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

			despesaDAO.excluir(codigo);

			request.setAttribute("mensagem", "Despesa removida com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao excluir despesa!");

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
			String data = ConversorData.stringFrontBack(request.getParameter("data"));
			double valor = ConversorNumerico.stringDouble(request.getParameter("valor"));
			int codigoFrequencia = ConversorNumerico.stringInt(request.getParameter("frequencia"));

			ValidadorDado.validaValor(valor);
			
			LocalDate dataLD = ConversorData.stringLocalDate(data);

			Frequencia frequencia = new Frequencia();
			frequencia.setCodigo(codigoFrequencia);

			Despesa despesa = new Despesa(codigo, codigoUsuario, descricao, valor, frequencia, dataLD);

			despesaDAO.atualizar(despesa);

			request.setAttribute("mensagem", "Despesa atualizada com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao atualizar despesa!");

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
			String data = ConversorData.stringFrontBack(request.getParameter("data"));
			double valor = ConversorNumerico.stringDouble(request.getParameter("valor"));
			int codigoFrequencia = ConversorNumerico.stringInt(request.getParameter("frequencia"));

			ValidadorDado.validaValor(valor);
			
			LocalDate dataLD = ConversorData.stringLocalDate(data);

			Frequencia frequencia = new Frequencia();
			frequencia.setCodigo(codigoFrequencia);

			Despesa despesa = new Despesa(codigoUsuario, descricao, valor, frequencia, dataLD);

			despesaDAO.inserir(despesa);

			request.setAttribute("mensagem", "Despesa cadastrada com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao cadastrar despesa!");

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
