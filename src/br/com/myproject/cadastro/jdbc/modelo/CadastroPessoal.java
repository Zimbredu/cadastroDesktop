package br.com.myproject.cadastro.jdbc.modelo;


public class CadastroPessoal{

	private Integer id;// verificar o comportamento.
	private String nome;
	private String cpf;
	VerificarNome verificarNome = new VerificarNome();

	public CadastroPessoal(String nome, String cpf) {
		
		this.nome = verificarNome.inserirDadosNome(nome).toUpperCase();
		this.cpf = verificarNome.inseriDadosCpf(cpf);

	}

	public CadastroPessoal(Integer id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}




	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		
	}

//	public void addColumn(String string) {//Verificar no codigo de exemplo
//		// TODO Auto-generated method stub
//		
//	}
//	
//	

}
