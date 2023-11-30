package br.com.sevencows.dao;

import java.util.List;

import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Reserva;

public interface ReservaDAO {

	void inserir(Reserva reserva) throws DatabaseException;
	
	void atualizar(Reserva reserva) throws DatabaseException;
	
	void excluir(int codigo) throws DatabaseException;
	
	void excluirTodos(int codigoUsuario) throws DatabaseException;
	
	Reserva buscar(int codigo);
	
	List<Reserva> listar(int codigoUsuario);
	
}
