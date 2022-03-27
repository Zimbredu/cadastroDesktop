package br.com.myproject.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;//Antenção aos imports

import br.com.myproject.cadastro.jdbc.modelo.CadastroPessoal;

public class CadastroPessoalDAO {

	private Connection connection;

	public CadastroPessoalDAO(Connection connection) {

		this.connection = connection;
	}

	/* Inicio do método salvar. */
	public void salvar(CadastroPessoal cadastro) {

		try {

			String sql = "INSERT INTO CADASTRO_PESSOAL (NOME , CPF) VALUES(?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, cadastro.getNome());
				pstm.setString(2, cadastro.getCpf());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						cadastro.setId(rst.getInt(1));
					}
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		

	}

	/* Fim do método salvar. */
	
	
	/* Inicio do método listar. */
	public List<CadastroPessoal> listar(){
		List<CadastroPessoal> cadastros = new ArrayList<CadastroPessoal>();
		try {
			String sql = "SELECT ID, NOME, CPF FROM CADASTRO_PESSOAL";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.execute();
				transformarResultSetEmCadastro(cadastros, pstm);
			}
			return cadastros;
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	}/* Fim do método listar. */
	
	
	/*Início do método buscar.*/
	public List<CadastroPessoal>buscar(CadastroPessoal cadastro){
		List<CadastroPessoal> cadastros = new ArrayList<CadastroPessoal>();
		try {
			String sql = "SELECT ID, NOME, CPF FROM CADASTRO_PESSOAL WHERE ID = ? ";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setInt(1, cadastro.getId());
				pstm.execute();
				
				transformarResultSetEmCadastro(cadastros, pstm);
			}
			return cadastros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	/*Fim do método buscar.*/
	
	/*Início do método deletar*/
	public void deletar(Integer id) {
		try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM CADASTRO_PESSOAL WHERE ID = ? ")){
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}/*Fim do método deletar*/
	
	/*Início do método alterar*/
	public void alterar(String nome, String cpf, Integer id ) {
		try (PreparedStatement pstm = connection
				.prepareStatement("UPDATE CADASTRO_PESSOAL C SET C.NOME = ?, C.CPF = ? WHERE ID = ?")){
			pstm.setString(1, nome);
			pstm.setString(2, cpf);
			pstm.setInt(3, id);
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}/*Fim do método alterar*/
	
	
	
	
	/*Início do método alterar*/
	
	/*Início do método transformarResultSetEmCadastro.*/
	private void transformarResultSetEmCadastro(List<CadastroPessoal> cadastros, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				CadastroPessoal cadastro = new CadastroPessoal( rst.getInt(1),rst.getString(2), rst.getString(3));
				
				cadastros.add(cadastro);
			}
		}
		
	}/*Fim do método transformarResultSetEmCadastro.*/
	
	
	
}















