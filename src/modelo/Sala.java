package modelo;

public class Sala {
    private int numero;
    private String nome;
    private float largura;
    private float comprimento;

    public Sala(){
        super();   
    }
    
    public Sala(String linha){
        desserializar(linha);
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }
    
    public String serializar(){
        return (this.numero + ";" + this.nome + ";" + this.largura + ";" + this.comprimento);
    }
    
    private void desserializar(String linha){
        String[] vet = linha.split(";");
        
        this.numero = Integer.parseInt(vet[0]);
        this.nome = vet[1];
        this.largura = Float.parseFloat(vet[2]);
        this.comprimento = Float.parseFloat(vet[3]);
    }
}
