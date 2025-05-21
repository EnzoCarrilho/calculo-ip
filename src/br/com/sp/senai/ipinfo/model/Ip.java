package br.com.sp.senai.ipinfo.model;

import java.util.HashMap;


public class Ip {
	
	private String endereco;
	private String mascaraDecimal;
	private String mascaraBinario;
	private String classe;
	private int cidr;
	
	
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
	
	
	private String definirClasse(){
		
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
		
		return classe;
	}
	
	StringBuilder novaMascaraBinario = null;
	
	private void definirMascara() {
	
		// separando o cidr do endere�o ip
		String[] separador = endereco.split("/");
						
		String stringcidr = separador[1];
		cidr = Integer.parseInt(stringcidr);
				
		// Criando um StringBuilder com 32 zeros
		novaMascaraBinario = new StringBuilder("00000000000000000000000000000000");

		// Preenchendo os bits com '1' de acordo com o valor do CIDR			
		for (int i = 0; i < cidr; i++) {
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
	
	
	
	private int definirSubRedes(){
		
		int bitsEmprestados = 0;
		
       if(cidr > 8 && cidr < 16 ) {
    	   
    	   bitsEmprestados = cidr - 8;
    	 
       }else if(cidr > 16 && cidr < 24) {
    	
    	   bitsEmprestados = cidr - 16;
    	   
       }else{
    	   
    	   bitsEmprestados = cidr - 24;
    	   
       }
       
       if(bitsEmprestados == 0) {
    	   
    	   System.out.println("Não há subredes");
       }
       
       int subRedes = (int) Math.pow(2, bitsEmprestados);
       return subRedes;
	}
	
	private int definirIpsDisponiveis() {
		
		int bitsZero = 0;
		int ipsDisponiveis = 0;
		
		// Percorrendo a Mascara e, Binário
		for(int i = 0; i < novaMascaraBinario.length(); i++) {
			
			// Contando os Bits zero
			if(novaMascaraBinario.charAt(i) == '0' ) {
				bitsZero ++;
			}
		}
	
	    	ipsDisponiveis = (int) ((Math.pow(2, bitsZero)) - 2);
	    
	    	return ipsDisponiveis;
	}
	

	
	
	public void mostrarDados() {
		
		definirClasse();
		definirMascara();
		definirIpsDisponiveis();
		
		System.out.println("Classe: " + definirClasse());
		System.out.println("Mascara em Bin�rio: " + mascaraBinario);
		System.out.println("Mascara em Decimal: " + mascaraDecimal);
		System.out.println("Número de Redes: " + definirSubRedes());
		System.out.println("Ips host dispon�veis: " + definirIpsDisponiveis());
		
		System.out.println();
	
	}
	
			
		
}
	
	

