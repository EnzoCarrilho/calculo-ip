package br.com.sp.senai.ipinfo.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.com.sp.senai.ipinfo.model.Ip;

public class TelaCalculo {
	
	private JTextField textEndereco;
	private JLabel labelEndereco;
	private JButton buttonCalcular;
	private JButton buttonLimpar;

	private JLabel labelMensagemErro;
	private JList<String> listResultado;
	private JScrollPane scrollPane;
	
	
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
		
		listResultado = new JList<String>();
		
		scrollPane = new JScrollPane(listResultado);
		scrollPane.setBounds(20, 130, 450, 350);
		
		labelMensagemErro = new JLabel();
		labelMensagemErro.setBounds(170, 190, 200, 40);
		labelMensagemErro.setText("Tipo de entrada inválida");
		labelMensagemErro.setVisible(false);
		labelMensagemErro.setForeground(Color.red);

		container.add(textEndereco);
		container.add(labelEndereco);
		container.add(buttonCalcular);
		container.add(buttonLimpar);
		container.add(scrollPane);
		container.add(labelMensagemErro);
		
		
		buttonCalcular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					
					labelMensagemErro.setVisible(false);
					scrollPane.setVisible(true);
					

					String endereco = labelEndereco.getText();
					
					Ip ip = new Ip();
					ip.setEndereco(endereco);
					ip.definirMascara();
					
					DefaultListModel<String> modelo = new DefaultListModel<>();
					List<String> ipResultados = ip.mostrarDados();
					
					 for (String elemento : ipResultados) {
					       modelo.addElement(elemento);
					   }
					 
					 listResultado.setModel(modelo);
					
					
					
					
					
				}catch(ArrayIndexOutOfBoundsException exception){}
				
				
				
				
				
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


