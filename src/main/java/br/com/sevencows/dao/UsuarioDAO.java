package br.com.sevencows.dao;

import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Usuario;

public interface UsuarioDAO {

	void inserir(Usuario usuario) throws DatabaseException;

	void atualizar(Usuario usuario) throws DatabaseException;

	void excluir(int codigo) throws DatabaseException;

	Usuario buscar(int codigo);

	Usuario buscar(String email);

}
