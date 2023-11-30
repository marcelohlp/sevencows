package br.com.sevencows.dao;

import java.util.List;

import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Despesa;

public interface DespesaDAO {

	void inserir(Despesa despesa) throws DatabaseException;
	
	void atualizar(Despesa despesa) throws DatabaseException;
	
	void excluir(int codigo) throws DatabaseException;
	
	void excluirTodos(int codigoUsuario) throws DatabaseException;
	
	Despesa buscar(int codigo);
	
	List<Despesa> listar(int codigoUsuario, String dataInicial, String dataFinal);
	
}
