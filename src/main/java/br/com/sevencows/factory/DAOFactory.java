package br.com.sevencows.factory;

import br.com.sevencows.dao.DespesaDAO;
import br.com.sevencows.dao.FrequenciaDAO;
import br.com.sevencows.dao.ReceitaDAO;
import br.com.sevencows.dao.ReservaDAO;
import br.com.sevencows.dao.UsuarioDAO;
import br.com.sevencows.dao.impl.OracleDespesaDAO;
import br.com.sevencows.dao.impl.OracleFrequenciaDAO;
import br.com.sevencows.dao.impl.OracleReceitaDAO;
import br.com.sevencows.dao.impl.OracleReservaDAO;
import br.com.sevencows.dao.impl.OracleUsuarioDAO;

public abstract class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static DespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}
	
	public static ReceitaDAO getReceitaDAO() {
		return new OracleReceitaDAO();
	}
	
	public static ReservaDAO getReservaDAO() {
		return new OracleReservaDAO();
	}
	
	public static FrequenciaDAO getFrequenciaDAO() {
		return new OracleFrequenciaDAO();
	}
	
}
