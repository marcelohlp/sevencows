package br.com.sevencows.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sevencows.dao.ReservaDAO;
import br.com.sevencows.exception.DatabaseException;
import br.com.sevencows.model.Reserva;
import br.com.sevencows.singleton.ConnectionManager;

public class OracleReservaDAO implements ReservaDAO {

	private Connection connection;

	@Override
	public void inserir(Reserva reserva) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "INSERT INTO T_SSC_RESERVA (cd_reserva, cd_usuario, ds_reserva, vl_reserva, vl_meta, ds_objetivo) "
					+ "VALUES (SQ_CD_RESERVA.NEXTVAL, ?, ?, ?, ?, ?)";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, reserva.getCodigoUsuario());
			stmt.setString(2, reserva.getDescricao());
			stmt.setDouble(3, reserva.getValor());
			stmt.setDouble(4, reserva.getValorObjetivo());
			stmt.setString(5, reserva.getObjetivo());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao cadastrar nova reserva!");

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
	public void atualizar(Reserva reserva) throws DatabaseException {

		PreparedStatement stmt = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "UPDATE T_SSC_RESERVA SET ds_reserva = ?, vl_reserva = ?, vl_meta = ?, ds_objetivo = ? WHERE cd_reserva = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setString(1, reserva.getDescricao());
			stmt.setDouble(2, reserva.getValor());
			stmt.setDouble(3, reserva.getValorObjetivo());
			stmt.setString(4, reserva.getObjetivo());
			stmt.setInt(5, reserva.getCodigo());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao atualizar reserva!");

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

			String sql = "DELETE FROM T_SSC_RESERVA WHERE cd_reserva = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir reserva!");

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

			String sql = "DELETE FROM T_SSC_RESERVA WHERE cd_usuario = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigoUsuario);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			throw new DatabaseException("Erro ao excluir reservas!");

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
	public Reserva buscar(int codigo) {

		Reserva reserva = null;

		PreparedStatement stmt = null;

		ResultSet rs = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_SSC_RESERVA WHERE cd_reserva = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigo);

			rs = stmt.executeQuery();

			if (rs.next()) {

				int cdReserva = rs.getInt("cd_reserva");
				int cdUsuario = rs.getInt("cd_usuario");
				String dsReserva = rs.getString("ds_reserva");
				double vlReserva = rs.getDouble("vl_reserva");
				double vlObjetivo = rs.getDouble("vl_meta");
				String dsObjetivo = rs.getString("ds_objetivo");

				reserva = new Reserva(cdReserva, cdUsuario, dsReserva, vlReserva, dsObjetivo, vlObjetivo);

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

		return reserva;
	}

	@Override
	public List<Reserva> listar(int codigoUsuario) {

		List<Reserva> lista = new ArrayList<Reserva>();

		PreparedStatement stmt = null;

		ResultSet rs = null;

		try {

			connection = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM T_SSC_RESERVA WHERE cd_usuario = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, codigoUsuario);

			rs = stmt.executeQuery();

			while (rs.next()) {

				int cdReserva = rs.getInt("cd_reserva");
				int cdUsuario = rs.getInt("cd_usuario");
				String dsReserva = rs.getString("ds_reserva");
				double vlReserva = rs.getDouble("vl_reserva");
				double vlObjetivo = rs.getDouble("vl_meta");
				String dsObjetivo = rs.getString("ds_objetivo");

				Reserva reserva = new Reserva(cdReserva, cdUsuario, dsReserva, vlReserva, dsObjetivo, vlObjetivo);

				lista.add(reserva);
				
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
