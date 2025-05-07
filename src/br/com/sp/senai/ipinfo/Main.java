package br.com.sp.senai.ipinfo;

import java.util.Scanner;

import br.com.sp.senai.ipinfo.model.Ip;

public class Main {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		Ip ip = new Ip();
		
		System.out.println("Insira o ip");
		ip.setEndereco(reader.next());
		ip.mostrarDados();
		
		
		reader.close();
	}

}
