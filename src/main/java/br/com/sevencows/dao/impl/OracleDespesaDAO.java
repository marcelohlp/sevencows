package br.com.sevencows.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.sevencows.dao.DespesaDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Despesa;
import br.com.sevencows.model.Frequencia;
import br.com.sevencows.singleton.ConnectionManager;
import br.com.sevencows.util.ConversorData;

public class OracleDespesaDAO implements DespesaDAO {

	private Connection connection;

	@Override
	public void inserir(Despesa despesa) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_SSC_DESPESA (cd_despesa, cd_usuario, cd_frequencia, ds_despesa, vl_despesa, dt_despesa) "
					+ "VALUES (SQ_CD_DESPESA.NEXTVAL, ?, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, despesa.getCodigoUsuario());
			stmt.setInt(2, despesa.getFrequencia().getCodigo());
			stmt.setString(3, despesa.getDescricao());
			stmt.setDouble(4, despesa.getValor());
			stmt.setDate(5, ConversorData.localDateDate(despesa.getData()));

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao cadastrar nova despesa!");

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
	public void atualizar(Despesa despesa) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_SSC_DESPESA SET cd_frequencia = ?, ds_despesa = ?, vl_despesa = ?, dt_despesa = ? WHERE cd_despesa = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, despesa.getFrequencia().getCodigo());
			stmt.setString(2, despesa.getDescricao());
			stmt.setDouble(3, despesa.getValor());
			stmt.setDate(4, ConversorData.localDateDate(despesa.getData()));
			stmt.setInt(5, despesa.getCodigo());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao atualizar despesa!");

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

			String sql = "DELETE FROM T_SSC_DESPESA WHERE cd_despesa = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir despesa!");

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

			String sql = "DELETE FROM T_SSC_DESPESA WHERE cd_usuario = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigoUsuario);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir despesas!");

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
	public Despesa buscar(int codigo) {

		PreparedStatement stmt = null;

		Despesa despesa = null;

		ResultSet rs = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_SSC_DESPESA INNER JOIN T_SSC_FREQUENCIA ON T_SSC_DESPESA.cd_frequencia = T_SSC_FREQUENCIA.cd_frequencia WHERE T_SSC_DESPESA.cd_despesa = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			rs = stmt.executeQuery();

			if (rs.next()) {

				int cdDespesa = rs.getInt("cd_despesa");
				int cdUsuario = rs.getInt("cd_usuario");
				String dsDespesa = rs.getString("ds_despesa");
				double vlDespesa = rs.getDouble("vl_despesa");
				LocalDate dtDespesa = ConversorData.dateLocalDate(rs.getDate("dt_despesa"));
				int cdFrequencia = rs.getInt("cd_frequencia");
				String dsFrequencia = rs.getString("ds_frequencia");

				Frequencia frequencia = new Frequencia(cdFrequencia, dsFrequencia);

				despesa = new Despesa(cdDespesa, cdUsuario, dsDespesa, vlDespesa, frequencia, dtDespesa);

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

		return despesa;

	}

	@Override
	public List<Despesa> listar(int codigoUsuario, String dataInicial, String dataFinal) {

		List<Despesa> lista = new ArrayList<Despesa>();

		PreparedStatement stmt = null;

		ResultSet rs = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_SSC_DESPESA INNER JOIN T_SSC_FREQUENCIA ON T_SSC_DESPESA.cd_frequencia = T_SSC_FREQUENCIA.cd_frequencia WHERE T_SSC_DESPESA.cd_usuario = ? AND T_SSC_DESPESA.dt_despesa BETWEEN ? AND ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigoUsuario);
			stmt.setDate(2, ConversorData.stringDate(dataInicial));
			stmt.setDate(3, ConversorData.stringDate(dataFinal));

			rs = stmt.executeQuery();

			while (rs.next()) {

				int cdDespesa = rs.getInt("cd_despesa");
				int cdUsuario = rs.getInt("cd_usuario");
				String dsDespesa = rs.getString("ds_despesa");
				double vlDespesa = rs.getDouble("vl_despesa");
				LocalDate dtDespesa = ConversorData.dateLocalDate(rs.getDate("dt_despesa"));
				int cdFrequencia = rs.getInt("cd_frequencia");
				String dsFrequencia = rs.getString("ds_frequencia");

				Frequencia frequencia = new Frequencia(cdFrequencia, dsFrequencia);

				Despesa despesa = new Despesa(cdDespesa, cdUsuario, dsDespesa, vlDespesa, frequencia, dtDespesa);

				lista.add(despesa);

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

		return lista;

	}

}
