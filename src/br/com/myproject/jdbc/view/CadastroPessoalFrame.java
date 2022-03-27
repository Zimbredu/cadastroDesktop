package br.com.myproject.jdbc.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.myproject.cadastro.jdbc.modelo.CadastroPessoal;
import br.com.myproject.jdbc.controller.CadastroPessoalController;

public class CadastroPessoalFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelCpf, labelColunaId, labelColunaNome,  labelColunaCpf ;
	private JTextField textoNome, textoCpf;
//	private JComboBox<?> ? Apos rodar verificar a necessidade dessa "dessa Classe".
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botaoApagar;
	private JTable tabela;
	/* private DefaultTableModel modelo; */
	private DefaultTableModel modelo;
	private CadastroPessoalController cadastroPessoalController;

	public CadastroPessoalFrame() {
		super("Cadastro_Pessoal");
		Container container = getContentPane();
		setLayout(null);

		this.cadastroPessoalController = new CadastroPessoalController();

		labelNome = new JLabel("Nome da pessoa cadastrada");
		labelCpf = new JLabel("Cpf da pessoa cadastradas");

		labelNome.setBounds(10, 10, 240, 15);
		labelCpf.setBounds(10, 50, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelCpf.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelCpf);

		textoNome = new JTextField();
		textoCpf = new JTextField();

		/* Obs.: Revisar a aula sobre a Classe javax.swing.JComboBox<> */

		textoNome.setBounds(10, 25, 265, 20);
		textoCpf.setBounds(10, 65, 265, 20);

		container.add(textoNome);
		container.add(textoCpf);

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identificador Id");
		modelo.addColumn("Identificador do Nome");
		modelo.addColumn("Identificador do Cpf");

		preencherTabela();
		
		labelColunaId = new JLabel("N�mero do Id");
		labelColunaId.setBounds(10, 200, 740, 20);
		labelColunaId.setForeground(Color.BLACK);

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botaoApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");

		botaoApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);

		container.add(botaoApagar);
		container.add(botaoEditar);

		setSize(800, 600);

		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		botaoApagar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
		});

	}

	/* Início do método limparTabela */
	private void limparTabela() {
		modelo.getDataVector().clear();
	}/* do método limparTabela */

	/* Inicio do méitodo alterar */
	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String cpf = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			this.cadastroPessoalController.alterar(id, nome, cpf);
			modelo.removeRow(tabela.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item alterado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor selecione o ID. ");
		}

	}/* Fim do méitodo alterar */

	/* Inicio do méitodo deletar */
	private void deletar() {
		Object objetoDalinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		if (objetoDalinha instanceof Integer) {
			Integer id = (Integer) objetoDalinha;
			this.cadastroPessoalController.deletar(id);
			modelo.removeRow(tabela.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item excluido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor selecione o ID. ");
		}

	}/* Fim do méitodo alterar */

	/* Inicio do méitodo preencherTabela */
	private void preencherTabela() {
		List<CadastroPessoal> cadastroPessoal = listarCadastro();
		try {
			for (CadastroPessoal cadastroP : cadastroPessoal) {
				modelo.addRow(new Object[] { cadastroP.getId(), cadastroP.getNome(), cadastroP.getCpf() });
			}
		} catch (Exception e) {
			throw e;
		}
	}/* Fim do méitodo preencherTabela */

	/* Inicio do méitodo salvar */
	private void salvar() {
		if (!textoNome.getText().equals("") && !textoCpf.getText().equals("")) {
			CadastroPessoal cadastroP = new CadastroPessoal(textoNome.getText(), textoCpf.getText());
			this.cadastroPessoalController.salvar(cadastroP);
			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Nome e descrição devem ser informados.");
		}

	}/* Fim do méitodo salvar */

	/* Inicio do méitodo listarCadastro */
	private List<CadastroPessoal> listarCadastro() {
		return this.cadastroPessoalController.listar();
	}/* Fim do méitodo listarCadastro */

	/* Inicio do méitodo limpar */
	private void limpar() {
		this.textoNome.setText("");
		this.textoCpf.setText("");
	}/* Fim do méitodo limpar */

}
