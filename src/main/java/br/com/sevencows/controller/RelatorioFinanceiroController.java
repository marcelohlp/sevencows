package br.com.sevencows.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sevencows.dao.DespesaDAO;
import br.com.sevencows.dao.ReceitaDAO;
import br.com.sevencows.dao.ReservaDAO;
import br.com.sevencows.factory.DAOFactory;
import br.com.sevencows.model.Despesa;
import br.com.sevencows.model.Receita;
import br.com.sevencows.model.RelatorioFinanceiro;
import br.com.sevencows.model.Reserva;
import br.com.sevencows.util.ConversorData;
import br.com.sevencows.util.ValidadorDado;

@WebServlet("/RelatorioFinanceiroController")
public class RelatorioFinanceiroController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DespesaDAO despesaDAO;
	private ReceitaDAO receitaDAO;
	private ReservaDAO reservaDAO;

	public RelatorioFinanceiroController() {
		super();
	}

	public void init() {
		despesaDAO = DAOFactory.getDespesaDAO();
		receitaDAO = DAOFactory.getReceitaDAO();
		reservaDAO = DAOFactory.getReservaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession hs = request.getSession();

		int codigoUsuario = (int) hs.getAttribute("codigoUsuario");
		
		String dataInicial = request.getParameter("data-inicial");
		String dataFinal = request.getParameter("data-final");

		dataInicial = ValidadorDado.validarDataInicial(dataInicial);
		dataFinal = ValidadorDado.validarDataFinal(dataFinal);
		
		List<Receita> listaReceitas = receitaDAO.listar(codigoUsuario, dataInicial, dataFinal);
		List<Despesa> listaDespesas = despesaDAO.listar(codigoUsuario, dataInicial, dataFinal);
		List<Reserva> listaReservas = reservaDAO.listar(codigoUsuario);
		
		RelatorioFinanceiro relatorio = new RelatorioFinanceiro(listaReceitas, listaDespesas, listaReservas);
		
		String dtInicialPadraoFront = ConversorData.stringBackFront(dataInicial);
		String dtFinalPadraoFront = ConversorData.stringBackFront(dataFinal);
		
		request.setAttribute("dataInicial", dataInicial);
		request.setAttribute("dataFinal", dataFinal);
		
		request.setAttribute("dtInicial", dtInicialPadraoFront);
		request.setAttribute("dtFinal", dtFinalPadraoFront);


		request.setAttribute("relatorio", relatorio);
		
		request.getRequestDispatcher("relatorio.jsp").forward(request, response);
		
	}

}
