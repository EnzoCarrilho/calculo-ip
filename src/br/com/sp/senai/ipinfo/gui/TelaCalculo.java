package br.com.sp.senai.ipinfo.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.sp.senai.ipinfo.model.Ip;

public class TelaCalculo {
	
	private JTextField textEndereco;
	private JLabel labelEndereco;
	private JButton buttonCalcular;
	private JButton buttonLimpar;
	private JLabel labelResultado;
	private JLabel labelMensagemErro;
	
	
	public void criarTelaConversor() {
		
		JFrame tela = new JFrame();
		tela.setTitle("Conversor de Temperatura");
		tela.setSize(500, 600);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setResizable(false);
		tela.setLayout(null);
		
		Container container = tela.getContentPane();
		
		labelEndereco = new JLabel();
		labelEndereco.setBounds(20, 20, 200, 30);
		labelEndereco.setText("Insira o endereço Ip: ");
		
		textEndereco = new JTextField();
		textEndereco.setBounds(20, 50, 450, 30);
		
		buttonCalcular = new JButton();
		buttonCalcular.setText("Calcular");
		buttonCalcular.setBounds(20, 90, 220, 30);
		
		buttonLimpar = new JButton();
		buttonLimpar.setText("Limpar");
		buttonLimpar.setBounds(250, 90, 220, 30);
		
		labelResultado = new JLabel();
		labelResultado.setBounds(150, 130, 230, 40);
		//criando a fonte
		Font fonteResultado = labelResultado.getFont();
		//definindo a estilização e tamanho da fonte
		Font fonteNegrito = new Font(fonteResultado.getFontName(), Font.BOLD, 25);
		//atribuindo a fonte ao label resultado
		labelResultado.setFont(fonteNegrito);
		
		labelMensagemErro = new JLabel();
		labelMensagemErro.setBounds(170, 190, 200, 40);
		labelMensagemErro.setText("Tipo de entrada inválida");
		labelMensagemErro.setVisible(false);
		labelMensagemErro.setForeground(Color.red);
		

		container.add(textEndereco);
		container.add(labelEndereco);
		container.add(buttonCalcular);
		container.add(buttonLimpar);
		container.add(labelResultado);
		container.add(labelMensagemErro);
		
		
		buttonCalcular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		buttonLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			
		
		tela.setVisible(true);
		
	}
		
		
}


