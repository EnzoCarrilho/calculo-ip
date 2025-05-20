package br.com.sp.senai.ipinfo.model;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

public class Ip {
	
	private String endereco;
	private String mascaraDecimal;
	private String mascaraBinario;
	private String classe;
	
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getMascaraDecimal() {
		return mascaraDecimal;
	}
	
	public void setMascaraDecimal(String mascaraDecimal) {
		this.mascaraDecimal = mascaraDecimal;
	}
	
	public String getMascaraBinario() {
		return mascaraBinario;
	}
	
	public void setMascaraBinario(String mascaraBinario) {
		this.mascaraBinario = mascaraBinario;
	}
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	
	
	private void definirClasse(){
		
		String inicioEndereco = endereco.substring(0, 3);
		int inicioEnderecoint = Integer.parseInt(inicioEndereco);
		
		if( inicioEnderecoint  <= 127) {
			
			setClasse("A");
			
		}else if(inicioEnderecoint  > 127 && inicioEnderecoint <= 191){
			
			setClasse("B");
			
		}else if(inicioEnderecoint > 191 && inicioEnderecoint  <= 223){
		
			setClasse("C");
			
		}else if(inicioEnderecoint  > 223 && inicioEnderecoint <= 239) {
			
			setClasse("D");
		}else {
			
			setClasse("E");
		}
		
	}
	
	StringBuilder novaMascaraBinario = null;
	
	private void definirMascara() {
	
		// separando o cidr do endereço ip
		String[] separador = endereco.split("/");
		
		String stringcidr = separador[1];
		int intcidr = Integer.parseInt(stringcidr);
		
		// Criando um StringBuilder com 32 zeros
		novaMascaraBinario = new StringBuilder("00000000000000000000000000000000");

		// Preenchendo os bits com '1' de acordo com o valor do CIDR			
		for (int i = 0; i < intcidr; i++) {
			novaMascaraBinario.setCharAt(i, '1');
		}
		
		String primeiroOcteto = novaMascaraBinario.substring(0, 8);
		String segundoOcteto = novaMascaraBinario.substring(8, 16);
		String terceiroOcteto = novaMascaraBinario.substring(16, 24);
		String quartoOcteto = novaMascaraBinario.substring(24, 32);
		
		setMascaraBinario(primeiroOcteto + '.' + segundoOcteto + '.' + terceiroOcteto + '.' + quartoOcteto);
		
		int primeiroDecimal = Integer.parseInt(primeiroOcteto, 2);
		int segundoDecimal = Integer.parseInt(segundoOcteto, 2);
		int terceiroDecimal = Integer.parseInt(terceiroOcteto, 2);
		int quartoDecimal = Integer.parseInt(quartoOcteto, 2);
		
		setMascaraDecimal(primeiroDecimal + "." + segundoDecimal + "." + terceiroDecimal + "." + quartoDecimal);
		
	}
	
	private int definirIpsDisponiveis() {
		
		int bitsZero = 0;
		
		for(int i = 0; i < novaMascaraBinario.length(); i++) {
			
			if(novaMascaraBinario.charAt(i) == '0' ) {
				bitsZero ++;
			}
		}
		
		int ipsDisponiveis = (int) ((Math.pow(2, bitsZero)) - 2);
		
		return ipsDisponiveis;
	}
	
	private int definirSubRedes(){
		
       int bitsUm = 0;
		
		for(int i = 0; i < novaMascaraBinario.length(); i++) {
			
			if(novaMascaraBinario.charAt(i) == '1' ) {
				bitsUm ++;
			}
		}
		
		int SubRedes = (int) Math.pow(2, bitsUm);
		return SubRedes;
	}

	
	
	public void mostrarDados() {
		
		definirClasse();
		definirMascara();
		definirIpsDisponiveis();
		
		System.out.println("Classe: " + classe);
		System.out.println("Mascara em Binário: " + mascaraBinario);
		System.out.println("Mascara em Decimal: " + mascaraDecimal);
		System.out.println("Ips host disponíveis: " + definirIpsDisponiveis());
		System.out.println();
	
	}
	
			
		
}
	
	

