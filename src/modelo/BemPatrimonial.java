package modelo;

import java.time.LocalDate;

public class BemPatrimonial {
	 private int numero;
	 private LocalDate dataDaCompra;
	 private String fabricante;
	 private String descricao;
	 private int situacao;
	 private int categoria;
	 private int sala;

	 public BemPatrimonial(){
		 super();   
	 }
	    
	 public BemPatrimonial(String linha){
		 desserializar(linha);
	 }
	 
	 public int getNumero() {
		 return numero;
	 }

	 public void setNumero(int numero){
		 this.numero = numero;
	 }

	 public LocalDate getDataDaCompra() {
		 return dataDaCompra;
	 }

	 public void setDataDaCompra(LocalDate dataDaCompra) {
		 this.dataDaCompra = dataDaCompra;
	 }

	 public String getFabricante(){
		 return fabricante;
	 }

	 public void setFabricante(String fabricante){
		 this.fabricante = fabricante;
	 }

	 public String getDescricao(){
		 return descricao;
	 }

	 public void setDescricao(String descricao) {
		 this.descricao = descricao;
	 }

	 public int getSituacao() {
		 return situacao;
	 }

	 public void setSituacao(int situacao) {
		 this.situacao = situacao;
	 }

	 public int getCategoria() {
		 return categoria;
	 }

	 public void setCategoria(int categoria) {
		 this.categoria = categoria;
	 }
	 
	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public String serializar(){
		return (this.numero + ";" + this.dataDaCompra.toString() + ";" + 
				 this.fabricante + ";" + this.descricao + ";" + 
				 this.situacao + ";" + this.categoria + ";" + this.sala);
	 }
	    
	 private void desserializar(String linha){
		 String[] vet = linha.split(";");
		 this.numero = Integer.parseInt(vet[0]);
		 String[] data = vet[1].split("-");
		 this.dataDaCompra = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
		 this.fabricante = vet[2];
		 this.descricao = vet[3];
		 this.situacao = Integer.parseInt(vet[4]);
		 this.categoria = Integer.parseInt(vet[5]);
		 this.sala = Integer.parseInt(vet[6]);
	 }
}
