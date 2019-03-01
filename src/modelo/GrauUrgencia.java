package modelo;

public enum GrauUrgencia {
	BAIXO(1),
	MEDIO(2),
	ALTO(3),
	CRITICO(4),
	DIRETORIA(5);
	
	private int grau;
	
	private GrauUrgencia(int grau){
		this.grau = grau;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}
}
