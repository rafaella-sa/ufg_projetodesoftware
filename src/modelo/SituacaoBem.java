package modelo;

public enum SituacaoBem {
	BAIXADO(1),
	INCORPORADO(5),
	EM_MANUTENCAO(9);
	
	private int situacao;
	
	private SituacaoBem(int situacao){
		this.situacao = situacao;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

}
