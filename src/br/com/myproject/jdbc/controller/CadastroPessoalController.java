package br.com.myproject.jdbc.controller;

import java.sql.Connection;
import java.util.List;

import br.com.myproject.cadastro.jdbc.modelo.CadastroPessoal;
import br.com.myproject.jdbc.dao.CadastroPessoalDAO;
import br.com.myproject.jdbc.factory.ConnectionFactory;

public class CadastroPessoalController {
	
	private CadastroPessoalDAO cadastroPessoalDAO;

	public CadastroPessoalController() {
		
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.cadastroPessoalDAO = new CadastroPessoalDAO(connection);
	}
	
	public List<CadastroPessoal> listar(){
		return this.cadastroPessoalDAO.listar();
				
	}

	public void salvar(CadastroPessoal cadastroP) {
		this.cadastroPessoalDAO.salvar(cadastroP);
		
	}

	public void deletar(Integer id) {
		this.cadastroPessoalDAO.deletar(id);
		
	}

	public void alterar(Integer id, String nome, String cpf) {
		this.cadastroPessoalDAO.alterar(nome, cpf, id);
		
	}

}
