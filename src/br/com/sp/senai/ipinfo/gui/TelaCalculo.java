package br.com.sp.senai.ipinfo.gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaCalculo {
	
	private JTextField textEndereco;
	private JLabel labelEndereco;
	private JButton buttonCalcular;
	private JLabel labelResultado;
	private JLabel mensagemDeErro;
	
	public void CriarTelaCalculo() {
		
		JFrame tela = new JFrame();
		
		tela.setTitle("Cálculo de IP");
		tela.setSize(600, 400);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setResizable(false);
		tela.setLayout(null);
		
		Container container = tela.getContentPane();
		
		labelEndereco = new JLabel();
		labelEndereco.setBounds(20, 20, 200, 30);
		labelEndereco.setText("Endereço IP: ");
		
		textEndereco = new JTextField();
		
		
		
		container.add(labelEndereco);
		
		tela.setVisible(true);
		
	}

}
