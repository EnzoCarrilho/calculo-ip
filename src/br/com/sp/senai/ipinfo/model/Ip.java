package br.com.sp.senai.ipinfo.model;

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
		
		
		String primeiroOcteto = endereco.substring(0, 3);
		int primeiroOctetoint = Integer.parseInt(primeiroOcteto);
		
		if( primeiroOctetoint  <= 127) {
			
			setClasse("A");
			
		}else if(primeiroOctetoint  > 127 && primeiroOctetoint <= 191){
			
			setClasse("B");
			
		}else if(primeiroOctetoint > 191 && primeiroOctetoint  <= 223){
		
			setClasse("C");
			
		}else if(primeiroOctetoint  > 223 && primeiroOctetoint <= 239) {
			
			setClasse("D");
		}else {
			
			setClasse("E");
		}
		
	}
	
	
	private void definirMascara() {
	
		
		String[] separador = endereco.split("/");
		int cidr = Integer.parseInt(separador[1]);
		
		mascaraBinario = "00000000000000000000000000000000";
		
		int contadorindex = 0;
		
		while(cidr > 0) {
			
			mascaraBinario.replace(mascaraBinario.charAt(contadorindex), (char) 1);
			
			--cidr;
			++contadorindex;
			
			setMascaraBinario(mascaraBinario);

		}
			
		
	}
		
		

	public void mostrarDados() {
		
		definirClasse();
		definirMascara();
		
		System.out.println("Classe: " + classe);
		System.out.println("Mascara em Binário: " + mascaraBinario);
		System.out.println();
	
	}
	
			
		
}
	
	

