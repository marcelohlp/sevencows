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

import br.com.sevencows.dao.FrequenciaDAO;
import br.com.sevencows.dao.ReceitaDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.exception.ValorException;
import br.com.sevencows.factory.DAOFactory;
import br.com.sevencows.model.Frequencia;
import br.com.sevencows.model.Receita;
import br.com.sevencows.model.RelatorioFinanceiro;
import br.com.sevencows.util.ConversorData;
import br.com.sevencows.util.ConversorNumerico;
import br.com.sevencows.util.OrdenadorLista;
import br.com.sevencows.util.ValidadorDado;

@WebServlet("/ReceitaController")
public class ReceitaController extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	private ReceitaDAO receitaDAO;
	private FrequenciaDAO frequenciaDAO;

	public ReceitaController() {
		super();
	}

	public void init() {
		receitaDAO = DAOFactory.getReceitaDAO();
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

		Receita receita = receitaDAO.buscar(codigo);
		
		String data = ConversorData.localDateFront(receita.getData());

		request.setAttribute("receita", receita);
		request.setAttribute("data", data);

		List<Frequencia> lista = frequenciaDAO.listar();

		request.setAttribute("frequencias", lista);

		request.getRequestDispatcher("receita-editar.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response, int codigoUsuario)
			throws ServletException, IOException {
		
		String dataInicial = request.getParameter("data-inicial");
		String dataFinal = request.getParameter("data-final");

		dataInicial = ValidadorDado.validarDataInicial(dataInicial);
		dataFinal = ValidadorDado.validarDataFinal(dataFinal);
		
		List<Receita> lista = receitaDAO.listar(codigoUsuario, dataInicial, dataFinal);

		RelatorioFinanceiro relatorio = new RelatorioFinanceiro(lista, null, null);
		
		OrdenadorLista.ordenarData(lista);
		
		String dtInicialPadraoFront = ConversorData.stringBackFront(dataInicial);
		String dtFinalPadraoFront = ConversorData.stringBackFront(dataFinal);
		
		request.setAttribute("dataInicial", dataInicial);
		request.setAttribute("dataFinal", dataFinal);
		
		request.setAttribute("dtInicial", dtInicialPadraoFront);
		request.setAttribute("dtFinal", dtFinalPadraoFront);

		request.setAttribute("receitas", lista);
		
		request.setAttribute("relatorio", relatorio);

		request.getRequestDispatcher("receita-listar.jsp").forward(request, response);
	}

	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Frequencia> lista = frequenciaDAO.listar();
		
		String data = ConversorData.localDateFront(LocalDate.now());

		request.setAttribute("frequencias", lista);
		
		request.setAttribute("data", data);

		request.getRequestDispatcher("receita-adicionar.jsp").forward(request, response);

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

			receitaDAO.excluir(codigo);

			request.setAttribute("mensagem", "Receita removida com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao excluir receita!");

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

			Receita receita = new Receita(codigo, codigoUsuario, descricao, valor, frequencia, dataLD);

			receitaDAO.atualizar(receita);

			request.setAttribute("mensagem", "Receita atualizada com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao atualizar receita!");

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

			Receita receita = new Receita(codigoUsuario, descricao, valor, frequencia, dataLD);

			receitaDAO.inserir(receita);

			request.setAttribute("mensagem", "Receita cadastrada com sucesso!");

		} catch (DatabaseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

			request.setAttribute("erro", "Erro ao cadastrar receita!");

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
