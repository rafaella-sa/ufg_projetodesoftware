package modelo;

import java.time.LocalDate;

public class OrdemServico {
	private String nomeDoSolicitante;
	private String defeito;
	private LocalDate dataDeDefeito;
	private int grauDeUrgencia;
	private LocalDate dataDeFechamento_da_os;
	private String nomeDoReceptor;
	private int numeroBem;
	private int id;
	
	public OrdemServico(){
		super();
	}
	
	public OrdemServico(String linha){
		desserializar(linha);
	}

	public String getNomeDoSolicitante() {
		return nomeDoSolicitante;
	}

	public void setNomeDoSolicitante(String nomeDoSolicitante) {
		this.nomeDoSolicitante = nomeDoSolicitante;
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}

	public LocalDate getDataDeDefeito() {
		return dataDeDefeito;
	}

	public void setDataDeDefeito(LocalDate dataDeDefeito) {
		this.dataDeDefeito = dataDeDefeito;
	}

	public int getGrauDeUrgencia() {
		return grauDeUrgencia;
	}

	public void setGrauDeUrgencia(int grauDeUrgencia) {
		this.grauDeUrgencia = grauDeUrgencia;
	}

	public LocalDate getDataDeFechamento_da_os() {
		return dataDeFechamento_da_os;
	}

	public void setDataDeFechamento_da_os(LocalDate dataDeFechamento_da_os) {
		this.dataDeFechamento_da_os = dataDeFechamento_da_os;
	}

	public String getNomeDoReceptor() {
		return nomeDoReceptor;
	}

	public void setNomeDoReceptor(String nomeDoReceptor) {
		this.nomeDoReceptor = nomeDoReceptor;
	}
	
	public int getNumeroBem() {
		return numeroBem;
	}

	public void setNumeroBem(int numeroBem) {
		this.numeroBem = numeroBem;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int tempoDeConserto(){
		return 0;
	}

	public String serializar(){
        return (this.nomeDoSolicitante + ";" + this.defeito + ";" + this.dataDeDefeito.toString()
        		+ ";" + this.grauDeUrgencia + ";" + this.dataDeFechamento_da_os.toString()
        		+ ";" + this.nomeDoReceptor + ";" + this.numeroBem + ";" + this.id);
    }
    
    private void desserializar(String linha){
        String[] vet = linha.split(";");
        
        this.nomeDoSolicitante = vet[0];
        this.defeito = vet[1];
        String[] dataD = vet[2].split("-");
		this.dataDeDefeito = LocalDate.of(Integer.parseInt(dataD[2]), Integer.parseInt(dataD[1]), Integer.parseInt(dataD[0]));
        this.grauDeUrgencia = Integer.parseInt(vet[3]);
        String[] dataF = vet[4].split("-");
		this.dataDeFechamento_da_os = LocalDate.of(Integer.parseInt(dataF[2]), Integer.parseInt(dataF[1]), Integer.parseInt(dataF[0]));
		this.nomeDoReceptor = vet[5];
		this.numeroBem = Integer.parseInt(vet[6]);
		this.id = Integer.parseInt(vet[7]);
    }
}
