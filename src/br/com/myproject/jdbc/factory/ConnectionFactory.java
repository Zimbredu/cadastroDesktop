package br.com.myproject.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() {
		// alterar as configuração, deixar igual da instalação BC.
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/cadastro?useTemezone=true&severTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("root");
		this.dataSource = comboPooledDataSource;

	}

	public Connection recuperarConexao() {
		try {
			return this.dataSource.getConnection();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
