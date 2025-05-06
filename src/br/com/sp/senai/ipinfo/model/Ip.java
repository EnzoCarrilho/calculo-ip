package br.com.sp.senai.ipinfo.model;

public class Ip {

	private String mascara;
	private String ip;
	private String classe;
	

	public String getMascara() {
		return mascara;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	
	String[] octetos = ip.split(".");
	
	String primeiroOcteto = octetos[0];
	String segundoOcteto = octetos[1];
	String terceiroOcteto = octetos[2];
	String quartoOcteto = octetos[3].substring(0, 3);
	
	String[] barraCidr = ip.split("/");
	int cidr = Integer.parseInt(barraCidr[1]);
	
	
	private void definirClasse(){
		
		String primeiroOcteto = ip.substring(0, 3);
		int octetoint = Integer.parseInt(primeiroOcteto);
		
		if( octetoint <= 197) {
			
			setClasse("A");
			
		}else if(octetoint > 128 && octetoint <= 192){
			
			setClasse("B");
			
		}else{
			
			setClasse("C");
			
		}
		
	}
	
	
	private void definirMascara() {
		
		String[] mascaraBinario = new String[4];
		mascaraBinario[0] = "";
		mascaraBinario[1] = "";
		
		int divisaoCidr = cidr/8;
		
		if(divisaoCidr == 1) {
			mascara = "255.0.0.0";
		}else if(divisaoCidr == 2) {
			mascara = "255.255.0.0";	
		}else if(divisaoCidr == 3) {
			mascara = "255.255.255.0";
		}else if(divisaoCidr == 4) {
			mascara = "255.255.255.255";
		}
		
		
		if(divisaoCidr % 8 != 0) {
			
			
			
		}
		
			
	}
		
		

	
	public void mostrarDados() {
		
		definirClasse();
		definirMascara();
		
		System.out.println("Classe: " + classe);
		System.out.println("Mascara: " + mascara);
	
	}
	
			
		
}
	
	

