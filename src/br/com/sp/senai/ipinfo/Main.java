package br.com.sp.senai.ipinfo;

import java.util.Scanner;

import br.com.sp.senai.ipinfo.gui.TelaCalculo;
import br.com.sp.senai.ipinfo.model.Ip;

public class Main {

	public static void main(String[] args) {
		
//		TelaCalculo tela = new TelaCalculo();
//		tela.criarTelaConversor();
	
		Scanner reader = new Scanner(System.in);

		Ip ip = new Ip();
		System.out.println("Insira o endere�o ip");
		
		 ip.setEndereco(reader.next());
		 ip.mostrarDados();
	
		reader.close(); 
	}

}
