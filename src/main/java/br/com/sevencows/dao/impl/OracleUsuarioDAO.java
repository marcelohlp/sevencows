package br.com.sevencows.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sevencows.dao.UsuarioDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Usuario;
import br.com.sevencows.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {

	private Connection connection;

	@Override
	public void inserir(Usuario usuario) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_SSC_USUARIO (cd_usuario, ds_email, nm_usuario, ds_senha) "
					+ "VALUES (SQ_CD_USUARIO.NEXTVAL, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao cadastrar usuario!");

		} finally {

			try {

				stmt.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			}
		}

	}

	@Override
	public void atualizar(Usuario usuario) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_SSC_USUARIO SET nm_usuario = ?, ds_senha = ? WHERE cd_usuario = ?";

			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setInt(3, usuario.getCodigo());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao atualizar usuario!");

		} finally {

			try {

				stmt.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			}

		}

	}

	@Override
	public void excluir(int codigo) throws DatabaseException {

		PreparedStatement stmt = null;
		
		try {
			
			connection = ConnectionManager.getInstance().getConnection();
			
			String sql = "DELETE FROM T_SSC_USUARIO WHERE cd_usuario = ?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir usuario!");

		} finally {

			try {

				stmt.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			}
			
		}
		
	}

	@Override
	public Usuario buscar(int codigo) {
		
		Usuario usuario = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionManager.getInstance().getConnection();
			
			String sql = "SELECT * FROM T_SSC_USUARIO WHERE cd_usuario = ?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int cdUsuario = rs.getInt("cd_usuario");
				String email = rs.getString("ds_email");
				String nome = rs.getString("nm_usuario");
				String senha = rs.getString("ds_senha");
				
				usuario = new Usuario(cdUsuario, email, nome, senha);
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} finally {

			try {

				stmt.close();
				rs.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			}
			
		}
		
		return usuario;
		
	}

	@Override
	public Usuario buscar(String email) {

		Usuario usuario = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionManager.getInstance().getConnection();
			
			String sql = "SELECT * FROM T_SSC_USUARIO WHERE ds_email = ?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, email);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				int cdUsuario = rs.getInt("cd_usuario");
				String dsEmail = rs.getString("ds_email");
				String nome = rs.getString("nm_usuario");
				String senha = rs.getString("ds_senha");
				
				usuario = new Usuario(cdUsuario, dsEmail, nome, senha);
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} finally {

			try {

				stmt.close();
				rs.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			}
			
		}
		
		return usuario;
		
	}

}
