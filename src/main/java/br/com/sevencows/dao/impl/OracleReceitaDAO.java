package br.com.sevencows.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.sevencows.dao.ReceitaDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Frequencia;
import br.com.sevencows.model.Receita;
import br.com.sevencows.singleton.ConnectionManager;
import br.com.sevencows.util.ConversorData;

public class OracleReceitaDAO implements ReceitaDAO {

	private Connection connection;

	@Override
	public void inserir(Receita receita) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_SSC_RECEITA (cd_receita, cd_usuario, cd_frequencia, ds_receita, vl_receita, dt_receita) "
					+ "VALUES (SQ_CD_RECEITA.NEXTVAL, ?, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, receita.getCodigoUsuario());
			stmt.setInt(2, receita.getFrequencia().getCodigo());
			stmt.setString(3, receita.getDescricao());
			stmt.setDouble(4, receita.getValor());
			stmt.setDate(5, ConversorData.localDateDate(receita.getData()));

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao cadastrar nova receita!");

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
	public void atualizar(Receita receita) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_SSC_RECEITA SET cd_frequencia = ?, ds_receita = ?, vl_receita = ?, dt_receita = ? WHERE cd_receita = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, receita.getFrequencia().getCodigo());
			stmt.setString(2, receita.getDescricao());
			stmt.setDouble(3, receita.getValor());
			stmt.setDate(4, ConversorData.localDateDate(receita.getData()));
			stmt.setInt(5, receita.getCodigo());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao atualizar receita!");

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

			String sql = "DELETE FROM T_SSC_RECEITA WHERE cd_receita = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir receita!");

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
	public void excluirTodos(int codigoUsuario) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE FROM T_SSC_RECEITA WHERE cd_usuario = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigoUsuario);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir receitas!");

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
	public Receita buscar(int codigo) {

		PreparedStatement stmt = null;

		Receita receita = null;

		ResultSet rs = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_SSC_RECEITA INNER JOIN T_SSC_FREQUENCIA ON T_SSC_RECEITA.cd_frequencia = T_SSC_FREQUENCIA.cd_frequencia WHERE T_SSC_RECEITA.cd_receita = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			rs = stmt.executeQuery();

			if (rs.next()) {

				int cdReceita = rs.getInt("cd_receita");
				int cdUsuario = rs.getInt("cd_usuario");
				String dsReceita = rs.getString("ds_receita");
				double vlReceita = rs.getDouble("vl_receita");
				LocalDate dtReceita = ConversorData.dateLocalDate(rs.getDate("dt_receita"));
				int cdFrequencia = rs.getInt("cd_frequencia");
				String dsFrequencia = rs.getString("ds_frequencia");

				Frequencia frequencia = new Frequencia(cdFrequencia, dsFrequencia);

				receita = new Receita(cdReceita, cdUsuario, dsReceita, vlReceita, frequencia, dtReceita);

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

		return receita;
	}

	@Override
	public List<Receita> listar(int codigoUsuario, String dataInicial, String dataFinal) {

		List<Receita> lista = new ArrayList<Receita>();

		PreparedStatement stmt = null;

		ResultSet rs = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();
			
			String sql = "SELECT * FROM T_SSC_RECEITA INNER JOIN T_SSC_FREQUENCIA ON T_SSC_RECEITA.cd_frequencia = T_SSC_FREQUENCIA.cd_frequencia WHERE T_SSC_RECEITA.cd_usuario = ? AND T_SSC_RECEITA.dt_receita BETWEEN ? AND ?";

			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, codigoUsuario);
			stmt.setDate(2, ConversorData.stringDate(dataInicial));
			stmt.setDate(3, ConversorData.stringDate(dataFinal));
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {

				int cdReceita = rs.getInt("cd_receita");
				int cdUsuario = rs.getInt("cd_usuario");
				String dsReceita = rs.getString("ds_receita");
				double vlReceita = rs.getDouble("vl_receita");
				LocalDate dtReceita = ConversorData.dateLocalDate(rs.getDate("dt_receita"));
				int cdFrequencia = rs.getInt("cd_frequencia");
				String dsFrequencia = rs.getString("ds_frequencia");

				Frequencia frequencia = new Frequencia(cdFrequencia, dsFrequencia);

				Receita receita = new Receita(cdReceita, cdUsuario, dsReceita, vlReceita, frequencia, dtReceita);
				
				lista.add(receita);

			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} finally {

			try {

				if (rs != null) {
					rs.close();
				}
				
				stmt.close();
				connection.close();

			} catch (SQLException e) {

				e.printStackTrace();
				System.err.println(e.getMessage());

			}
			
		}

		return lista;

	}

}
