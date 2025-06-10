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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.sp.senai.ipinfo.model.Ip;

public class TelaCalculo {
	
	private JTextField textEndereco;
	private JLabel labelEndereco;
	private JButton buttonCalcular;
	private JButton buttonLimpar;
	private JList<String> listResultado;
	private JScrollPane scrollPane;
	
	
	public void criarTelaConversor() {
		
		JFrame tela = new JFrame();
		tela.setTitle("Cálculo de IP");
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
		scrollPane.setBounds(20, 130, 450, 400);
		
		final JTextArea labelMensagemErro = new JTextArea(); 
		labelMensagemErro.setEditable(false); 
		labelMensagemErro.setLineWrap(true);
		labelMensagemErro.setBounds(60, 190, 370, 240);
		labelMensagemErro.setText("Entrada Inválida.\nInsira um endereço válido.");
		labelMensagemErro.setVisible(false);
		labelMensagemErro.setForeground(Color.red);
		Font fonteErro = labelMensagemErro.getFont();
		Font fonteNegrito = new Font(fonteErro.getFontName(), Font.BOLD, 25);
		labelMensagemErro.setFont(fonteNegrito);
		

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
					listResultado.setVisible(true);
					
					String endereco = textEndereco.getText();
					
					Ip ip = new Ip();
					ip.setEndereco(endereco);
					ip.separarEndereco();
					
					List<String> ipResultados = ip.mostrarDados();
					DefaultListModel<String> modelo = new DefaultListModel<>();
					
					
					for (String lista : ipResultados) {
						String[] linhas = lista.split("\n");
						
						for (String linha : linhas) {
							modelo.addElement(linha);
						}
					}
					
					 
					 listResultado.setModel(modelo);
					
					
						
				}catch(ArrayIndexOutOfBoundsException exception){
				
				String[] nullString = new String[0];
				listResultado.setListData(nullString);
				
				textEndereco.setText(null);
				
				listResultado.setVisible(false);
				scrollPane.setVisible(false);
				labelMensagemErro.setVisible(true);
				
				}
				
			}
		});
		
		
		
		buttonLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				labelMensagemErro.setVisible(false);
				listResultado.setVisible(true);
				scrollPane.setVisible(true);
				
				String[] nullString = new String[0];
				listResultado.setListData(nullString);
				
				textEndereco.setText(null);
				
				textEndereco.requestFocus();
				
				
			}
		});
			
		
		tela.setVisible(true);
		
	}
		
		
}


