package br.com.sp.senai.ipinfo.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
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
	
		// separando o cidr do endereï¿½o ip
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
	
	
	int numeroDeRedes = 0;
	int bitsEmprestados = 0;
	private int calcularSubRedes(){
		
       if(cidr > 8 && cidr < 16 ) {
    	   
    	   bitsEmprestados = cidr - 8;
    	 
       }else if(cidr > 16 && cidr < 24) {
    	
    	   bitsEmprestados = cidr - 16;
    	   
       }else{
    	   
    	   bitsEmprestados = cidr - 24;
    	   
       }
       
       if(bitsEmprestados == 0) {
    	   
    	   System.out.println("NÃ£o hÃ¡ subredes");
    	   numeroDeRedes = 1;
       }else {
       
       numeroDeRedes = (int) Math.pow(2, bitsEmprestados);
       }
       
       return numeroDeRedes;
	}
	
	
	private int definirIpsDisponiveis() {
		
		int bitsZero = 0;
		int ipsDisponiveis = 0;
		
		// Percorrendo a Mascara e, BinÃ¡rio
		for(int i = 0; i < novaMascaraBinario.length(); i++) {
			
			// Contando os Bits zero
			if(novaMascaraBinario.charAt(i) == '0' ) {
				bitsZero ++;
			}
		}
	    	ipsDisponiveis = (int) ((Math.pow(2, bitsZero)) - 2);
	    
	    	return ipsDisponiveis;
	}
	
		int saltoDeIps = 0;
		public Integer[] definirIpDeInicioDasRedes(){
		
		int novoOctetoMascara = 255 + bitsEmprestados;
		int saltoDeIps = novoOctetoMascara / numeroDeRedes;
		int enderecoDeInicioDasRedes = 0;
		
		Integer[] intervalosDeInicioDasRedes = new Integer[numeroDeRedes];
		
		for (int i = 0; i < numeroDeRedes; i++) {
	        intervalosDeInicioDasRedes[i] = enderecoDeInicioDasRedes;
	        enderecoDeInicioDasRedes += saltoDeIps; // Incrementa para a prÃ³xima interaÃ§Ã£o
	    }
		
		return intervalosDeInicioDasRedes;
	}
	
	
	private Integer[] definirPrimeiroIpValido() {
		
		Integer[] intervaloDePrimeirosIpsValidos = new Integer[numeroDeRedes];
				
		for(int i = 0; i < definirIpDeInicioDasRedes().length; i++) {
			intervaloDePrimeirosIpsValidos[i] = definirIpDeInicioDasRedes()[i]+1;
		}
		return intervaloDePrimeirosIpsValidos;
	}
	
	
	private Integer[] definirUltimoIpValido() {
		
		Integer[] intervaloDeUltimosIpsValidos = new Integer[numeroDeRedes];
				
		for(int i = 0; i < definirIpDeInicioDasRedes().length; i++) {
			intervaloDeUltimosIpsValidos[i] = definirIpDeInicioDasRedes()[i] + definirIpsDisponiveis();
		}
		return intervaloDeUltimosIpsValidos;
	}
	
	
	private Integer[] definirIpdeBroadcast() {
		
		Integer[] intervaloDeBroadcast = new Integer[numeroDeRedes];
				
		for(int i = 0; i < definirUltimoIpValido().length; i++) {
			intervaloDeBroadcast[i] = definirUltimoIpValido()[i]+1;
		}
		return intervaloDeBroadcast;
	}
	
	private String[] iniciosDeRede(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < definirIpDeInicioDasRedes().length; i++) {
			
			String item = definirIpDeInicioDasRedes()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	private String[] primeirosEnderecosDisponiveis(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < definirPrimeiroIpValido().length; i++) {
			
			String item = definirPrimeiroIpValido()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	private String[] ultimosEnderecosDisponiveis(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < definirUltimoIpValido().length; i++) {
			
			String item = definirUltimoIpValido()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	private String[] enderecosDeBroadcast(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < definirIpdeBroadcast().length; i++) {
			
			String item = definirIpdeBroadcast()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	

	
	
	public void mostrarDados() {
		
		String enderecoSeparado = endereco.substring(0, 9);
		
		definirMascara();
		System.out.println("Classe: " + definirClasse());
		System.out.println("Mascara em Binário: " + mascaraBinario);
		System.out.println("Mascara em Decimal: " + mascaraDecimal);
		System.out.println("nï¿½mero De Redes: " + calcularSubRedes());
		System.out.println("Ips host disponíveis por rede: " + definirIpsDisponiveis());
		
		for (int i = 0; i < numeroDeRedes; i++) {
			
			System.out.println("================================================");
			System.out.println("Rede" + i + ":");
			System.out.println("Início Da Rede: " + enderecoSeparado + "." + iniciosDeRede()[i]);
			System.out.println("Intervalo de host disponíveis: " + enderecoSeparado + "." + primeirosEnderecosDisponiveis()[i] + "----" + enderecoSeparado + "." + ultimosEnderecosDisponiveis()[i]);
			System.out.println("Endereço de Broadcast: " + enderecoSeparado + "." + enderecosDeBroadcast()[i]);
			System.out.println("===================================================");
		}
	
	}
	
	
}

