package br.com.sevencows.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sevencows.dao.FrequenciaDAO;
import br.com.sevencows.model.Frequencia;
import br.com.sevencows.singleton.ConnectionManager;

public class OracleFrequenciaDAO implements FrequenciaDAO {

	private Connection connection;
	
	@Override
	public List<Frequencia> listar() {
		
		List<Frequencia> lista = new ArrayList<Frequencia>();
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			
			connection = ConnectionManager.getInstance().getConnection();
			
			String sql = "SELECT * FROM T_SSC_FREQUENCIA";
			
			stmt = connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int codigo = rs.getInt("cd_frequencia");
				String descricao = rs.getString("ds_frequencia");
				
				Frequencia frequencia = new Frequencia(codigo, descricao);
				
				lista.add(frequencia);
				
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
