package modelo;

public class Categoria {
	private int codigo;
	private String nome;
	
	public Categoria(){
		super();
	}
	
	public Categoria(String linha){
		desserializar(linha); 
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String serializar(){
        return (this.codigo + ";" + this.nome);
    }
    
    private void desserializar(String linha){
        String[] vet = linha.split(";");
        
        this.codigo = Integer.parseInt(vet[0]);
        this.nome = vet[1];
    }
}
