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
	
	
	private void definirClasse(){
		
		String primeiroocteto = ip.substring(0, 3);
		int octetoint = Integer.parseInt(primeiroocteto);
		
		if( octetoint <= 197) {
			
			setClasse("A");
			
		}else if(octetoint > 128 && octetoint <= 192){
			
			setClasse("B");
			
		}else{
			
			setClasse("C");
			
		}
		
	}
	
	private void definirMascara() {
		
	String[] cidr = ip.split("/");
		
		if(cidr.length == 2) {
			String valorcidr = cidr[1];
		
	
			if(valorcidr == "8") {
				
				mascara = "255.0.0.0";
				
			}else if(valorcidr == "16") {
				
				mascara = "255.255.0.0";
			
			}else if(valorcidr == "24"){
				
				mascara = "255.255.255.0";
			}
		}
	}
	
	public void mostrarDados() {
		
		definirClasse();
		definirMascara();
		
		System.out.println("Classe: " + classe);
		System.out.println("Mascara: " + mascara);
	
	}
	
	
			
			
		
}
	
	

