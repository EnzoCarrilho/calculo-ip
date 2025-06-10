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
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	String[] octetos;
	public void separarEndereco() {
		
		int cidr;

		String[] splitEndereco = endereco.split("/");
		cidr = Integer.parseInt(splitEndereco[1]);	
		this.cidr = cidr;
		
		
		octetos = splitEndereco[0].split("\\.");
		if (octetos.length != 4) {
			octetos[0] = "Formato de IP invalido.";
		}

	}
		
	public String getMascaraBinario() {
	
		StringBuilder novaMascaraBinario = new StringBuilder("00000000000000000000000000000000");
		// Preenchendo os bits com '1' de acordo com o valor do CIDR			
		for (int i = 0; i < cidr; i++) {
			novaMascaraBinario.setCharAt(i, '1');
		}
		
		String primeiroOcteto = novaMascaraBinario.substring(0, 8);
		String segundoOcteto = novaMascaraBinario.substring(8, 16);
		String terceiroOcteto = novaMascaraBinario.substring(16, 24);
		String quartoOcteto = novaMascaraBinario.substring(24, 32);
		
		mascaraBinario = primeiroOcteto + '.' + segundoOcteto + '.' + terceiroOcteto + '.' + quartoOcteto;
		
		return mascaraBinario;
	}
	
	public String getMascaraDecimal() {
		
		String[] separarMascara = getMascaraBinario().split("\\.");
		
		String primeiroOcteto = separarMascara[0];
		String segundoOcteto = separarMascara[1];
		String terceiroOcteto = separarMascara[2];
		String quartoOcteto = separarMascara[3];

		
		int primeiroDecimal = Integer.parseInt(primeiroOcteto, 2);
		int segundoDecimal = Integer.parseInt(segundoOcteto, 2);
		int terceiroDecimal = Integer.parseInt(terceiroOcteto, 2);
		int quartoDecimal = Integer.parseInt(quartoOcteto, 2);
		
		mascaraDecimal = primeiroDecimal + "." + segundoDecimal + "." + terceiroDecimal + "." + quartoDecimal;
		return mascaraDecimal;
	}
	
	
	private String definirClasse(){
		
		int primeiroOcteto = Integer.parseInt(octetos[0]);
		
		if( primeiroOcteto  <= 127) {
			
			classe = "A";
			
		}else if(primeiroOcteto  > 127 && primeiroOcteto <= 191){
			
			classe = "B";
			
		}else if(primeiroOcteto > 191 && primeiroOcteto  <= 223){
		
			classe = "C";
			
		}else if(primeiroOcteto  > 223 && primeiroOcteto <= 239) {
			
			classe = "D";
		}else {
			
			classe = "E";
		}
		
		return classe;
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
		
		// Percorrendo a Mascara em BinÃ¡rio
		for(int i = 0; i < getMascaraBinario().length(); i++) {
			
			// Contando os Bits zero
			if(getMascaraBinario().charAt(i) == '0' ) {
				bitsZero ++;
			}
		}
	    	ipsDisponiveis = (int) ((Math.pow(2, bitsZero)) - 2);
	    
	    	return ipsDisponiveis;
	}
	
		int saltoDeIps = 0;
		public Integer[] calcularEnderecoDeInicioDasRedes(){
		
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
	
	
	private Integer[] calcularPrimeirosIpsValidos() {
		
		Integer[] intervaloDePrimeirosIpsValidos = new Integer[numeroDeRedes];
				
		for(int i = 0; i < calcularEnderecoDeInicioDasRedes().length; i++) {
			intervaloDePrimeirosIpsValidos[i] = calcularEnderecoDeInicioDasRedes()[i]+1;
		}
		return intervaloDePrimeirosIpsValidos;
	}
	
	
	private Integer[] calcularUltimosIpsValidos() {
		
		Integer[] intervaloDeUltimosIpsValidos = new Integer[numeroDeRedes];
				
		for(int i = 0; i < calcularEnderecoDeInicioDasRedes().length; i++) {
			intervaloDeUltimosIpsValidos[i] = calcularEnderecoDeInicioDasRedes()[i] + definirIpsDisponiveis();
		}
		return intervaloDeUltimosIpsValidos;
	}
	
	
	private Integer[] calcularIpdeBroadcast() {
		
		Integer[] intervaloDeBroadcast = new Integer[numeroDeRedes];
				
		for(int i = 0; i < calcularUltimosIpsValidos().length; i++) {
			intervaloDeBroadcast[i] = calcularUltimosIpsValidos()[i]+1;
		}
		return intervaloDeBroadcast;
	}
	
	
	
	
	private String[] iniciosDeRede(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < calcularEnderecoDeInicioDasRedes().length; i++) {
			
			String item = calcularEnderecoDeInicioDasRedes()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	private String[] primeirosEnderecosDisponiveis(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < calcularPrimeirosIpsValidos().length; i++) {
			
			String item = calcularPrimeirosIpsValidos()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	private String[] ultimosEnderecosDisponiveis(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < calcularUltimosIpsValidos().length; i++) {
			
			String item = calcularUltimosIpsValidos()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	private String[] enderecosDeBroadcast(){
		
		String[] iniciosDeRede = new String[numeroDeRedes];
		
		for(int i = 0; i < calcularIpdeBroadcast().length; i++) {
			
			String item = calcularIpdeBroadcast()[i].toString();
			iniciosDeRede[i] = item;
			
		}
		return iniciosDeRede;
	}
	
	
	public java.util.List<String> mostrarDados() {
		
		String enderecoSeparado = endereco.substring(0, 9);
		
		java.util.List<String> resultados = new ArrayList<String>();
		
		resultados.add("EndereÃ§o: " + endereco);
		resultados.add("Classe: " + definirClasse());
		
		if(classe == "D" || classe == "E") {
			resultados.add("Máscara Decimal: ");
			resultados.add("Máscara BinÃ¡rio: ");
			resultados.add("Número de Redes: ");
			resultados.add("Ips host disponíveis por rede: ");
			
		}else {
			resultados.add("Máscara Binário: " + getMascaraBinario());
			resultados.add("Máscara Decimal: " + getMascaraDecimal());
			resultados.add("Número de Redes: " + calcularSubRedes());
			resultados.add("Ips host disponíveis por rede: " + definirIpsDisponiveis());
		}
		
		if(cidr > 24 && cidr < 31) {
		
			for (int i = 0; i < numeroDeRedes; i++) {
				
				resultados.add("===================================================");
				resultados.add("Rede" + i + ":");
				resultados.add("Início Da Rede: " + enderecoSeparado + "." + iniciosDeRede()[i]);
				resultados.add("Intervalo de host disponíveis: " + enderecoSeparado + "." + primeirosEnderecosDisponiveis()[i] + "--" + enderecoSeparado + "." + ultimosEnderecosDisponiveis()[i]);
				resultados.add("Endereço de Broadcast: " + enderecoSeparado + "." + enderecosDeBroadcast()[i]);
				resultados.add("===================================================");
			}
		}
		
		return resultados;
		
	}
	
	
}