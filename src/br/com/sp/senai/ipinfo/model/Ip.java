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
	
	
	private void definirMascara() {
	
		// separando o cidr do endereço ip
		String[] separador = endereco.split("/");
		
		String stringcidr = separador[1];
		int intcidr = Integer.parseInt(stringcidr);
		
		// Criando um StringBuilder com 32 zeros
		StringBuilder novaMascaraBinario = new StringBuilder("00000000000000000000000000000000");

		// Preenchendo os bits com '1' de acordo com o valor do CIDR			
		// dentro da sintaxe (inicializando a variável i, codição da estrutura, e incremento), o loop continuará enquanto a condição for verdadeira
		for (int i = 0; i < intcidr; i++) {
			novaMascaraBinario.setCharAt(i, '1');
		}
		
		String[] octetosMascaraBinario = new String[4];
		String primeiroOcteto = octetosMascaraBinario[0] = novaMascaraBinario.substring(0, 8);
		String segundoOcteto = octetosMascaraBinario[1] = novaMascaraBinario.substring(8, 16);
		String terceiroOcteto = octetosMascaraBinario[2] = novaMascaraBinario.substring(16, 24);
		String quartoOcteto = octetosMascaraBinario[3] = novaMascaraBinario.substring(24, 32);
		
		setMascaraBinario(primeiroOcteto + '.' + segundoOcteto + '.' + terceiroOcteto + '.' + quartoOcteto);
		
		int primeiroDecimal = Integer.parseInt(primeiroOcteto, 2);
		int segundoDecimal = Integer.parseInt(segundoOcteto, 2);
		int terceiroDecimal = Integer.parseInt(terceiroOcteto, 2);
		int quartoDecimal = Integer.parseInt(quartoOcteto, 2);
		
		setMascaraDecimal(primeiroDecimal + "." + segundoDecimal + "." + terceiroDecimal + "." + quartoDecimal);
		
	}
	

	
	public void mostrarDados() {
		
		definirClasse();
		definirMascara();
		
		System.out.println("Classe: " + classe);
		System.out.println("Mascara em Binário: " + mascaraBinario);
		System.out.println("Mascara em Decimal: " + mascaraDecimal);
		System.out.println();
	
	}
	
			
		
}
	
	

