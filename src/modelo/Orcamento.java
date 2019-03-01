package modelo;

public class Orcamento {
	private String nomeDaPrestadora;
	private int previsaoDeConsertoEmDias;
	private String nomeDoContato;
	private String telefone;
	private double preco;
	private int ordemservico;
	private int id;
	
	public Orcamento(){
		super();
	}
	
	public Orcamento(String linha){
		desserializar(linha);
	}

	public String getNomeDaPrestadora() {
		return nomeDaPrestadora;
	}

	public void setNomeDaPrestadora(String nomeDaPrestadora) {
		this.nomeDaPrestadora = nomeDaPrestadora;
	}

	public int getPrevisaoDeConsertoEmDias() {
		return previsaoDeConsertoEmDias;
	}

	public void setPrevisaoDeConsertoEmDias(int previsaoDeConsertoEmDias) {
		this.previsaoDeConsertoEmDias = previsaoDeConsertoEmDias;
	}

	public String getNomeDoContato() {
		return nomeDoContato;
	}

	public void setNomeDoContato(String nomeDoContato) {
		this.nomeDoContato = nomeDoContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(int ordemservico) {
		this.ordemservico = ordemservico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String serializar(){
        return (this.nomeDaPrestadora + ";" + this.previsaoDeConsertoEmDias + ";" + 
        		this.nomeDoContato + ";" + this.telefone + ";" + this.preco + ";"
        		+ this.ordemservico + ";" + this.id);
    }
    
    private void desserializar(String linha){
        String[] vet = linha.split(";");
        
        this.nomeDaPrestadora = vet[0];
        this.previsaoDeConsertoEmDias = Integer.parseInt(vet[1]);
        this.nomeDoContato = vet[2];
        this.telefone = vet[3];
        this.preco = Double.parseDouble(vet[4]);
        this.ordemservico = Integer.parseInt(vet[5]);
        this.id = Integer.parseInt(vet[6]);
    }
}
