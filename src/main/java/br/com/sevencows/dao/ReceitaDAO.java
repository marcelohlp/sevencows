package br.com.sevencows.dao;

import java.util.List;

import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Receita;

public interface ReceitaDAO {

	void inserir(Receita receita) throws DatabaseException;

	void atualizar(Receita receita) throws DatabaseException;

	void excluir(int codigo) throws DatabaseException;
	
	void excluirTodos(int codigoUsuario) throws DatabaseException;

	Receita buscar(int codigo);

	List<Receita> listar(int codigoUsuario, String dataInicial, String dataFinal);

}
