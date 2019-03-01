package visao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controle.CBemPatrimonial;

public class VWBemPatrimonial {
	public void entradaDeDados(){
        Scanner ler = new Scanner(System.in);
        int numero;
        String dataDaCompra;
        String fabricante;
        String descricao;
        int situacao;
        int categoria;
        int sala;
        
        String aux;
        System.out.println("Digite o numero do bem: ");
        aux = ler.nextLine();
        numero = Integer.parseInt(aux);
        
        System.out.println("Digite a data no formato 'dd/mm/aaaa': ");
        ler.nextLine();
        dataDaCompra = ler.nextLine();
        
        System.out.println("Digite o nome do fabricante: ");
        ler.nextLine(); //consumir o enter
        fabricante = ler.nextLine();
        
        System.out.println("Digite a descricao do bem: ");
        ler.nextLine(); //consumir o enter
        descricao = ler.nextLine();
        //System.out.println(nome);
        
        System.out.println("Digite a situacao do bem: \n1. Baixado"
        		+ "\n5. Incorporado\n9. Em manutencao\n");
        ler.nextLine();
        aux = ler.nextLine();
        situacao = Integer.parseInt(aux);
        
        System.out.println("Digite a categoria do bem: ");
        ler.nextLine();
        aux = ler.nextLine();
        categoria = Integer.parseInt(aux);
        
        System.out.println("Digite a sala: ");
        ler.nextLine();
        aux = ler.nextLine();
        sala = Integer.parseInt(aux);
        
        if (numero > 0 && !(dataDaCompra.trim().equals("")) && !(fabricante.trim().equals("")) 
        		&& !(descricao.trim().equals("")) && situacao > 0 && categoria > 0 && sala > 0){
            try{
                CBemPatrimonial controle = new CBemPatrimonial();
                controle.gravar(numero, dataDaCompra, fabricante, descricao, situacao, categoria, sala);
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errBEM")){
                    System.err.println("Numero de bem patrimonial ja cadastrado!!!");
                }
                if (e.getMessage().equals("errSALA")){
                    System.err.println("Numero de sala nao cadastrado!!!");
                }
                if (e.getMessage().equals("errCATEGORIA")){
                    System.err.println("Codigo de categoria de bem nao cadastrado!!!");
                }
                if (e.getMessage().equals("errSITUACAO")){
                    System.err.println("Codigo de situacao do bem inexistente!!!");
                }
                if (e.getMessage().equals("errDATA_INVALIDA")){
                    System.err.println("Data posterior à atual!!!");
                }
            }catch(ParseException e){
            	System.out.println("DATA INVALIDA! Insira uma data valida no formato 'dd/mm/aaaa'.");
            }
            catch(Exception e){
                System.err.println("ERRO nas gravacoes: " + e.getMessage());
            }
                    
        } else {
            System.out.println("\n\tTodos os campos sao obrigatorios.");
        }
        ler.close();
    }
    
    
    public void listarDados(){
    	CBemPatrimonial lista = new CBemPatrimonial();
        String espacos = "                                        "; //40 espaços em branco
    	ArrayList<String> lstItens = lista.getItens();
    	System.out.println("\nNumero\tData da compra\tFabricante                              "
    			+ "Descricao                              \tSituacao\tCategoria\tSala");
    	for(String linha : lstItens){
    		String vet[] = linha.split(";");
    		System.out.print(vet[0]);
            System.out.print("\t" + vet[1] + "\t" + vet[2] + espacos.substring(0, 40-vet[2].length()) 
            + vet[3] + espacos.substring(0, 40-vet[3].length())+ "\t" + vet[4] + "\t" + vet[5] + "\t"
            + vet[6] + "\n");
    	}
    	System.out.println("\n");
    }
    
    public void excluir(){
        Scanner ler = new Scanner(System.in);
        int numero;
        
        System.out.println("Digite o numero do bem: ");
        String aux = ler.nextLine();
        numero = Integer.parseInt(aux);
        
        if (numero > 0){
            try{
                CBemPatrimonial controle = new CBemPatrimonial();
                if (controle.excluir(numero))
                        System.out.println( "\n\tBem patrimoial "+numero+" excluido com sucesso!!!\n");             
            }catch(IllegalArgumentException e){
            	 if (e.getMessage().equals("errOS_ASSOCIADA")){
                     System.err.println("Numero de bem patrimonial associado a uma OS!!!");
                 }
            	 if (e.getMessage().equals("errBEM_INEXISTENTE")){
                     System.err.println("Numero de bem patrimonial nao existe!!!");
                 }
            }catch(Exception e){
                System.err.println("ERRO nas gravacoes: " + e.getMessage());
            }
        }else
        	System.out.println("Todos os campos sao obrigatorios.\n");
        ler.close();
    }
    
    public void qtdDeManutencoes(){
    	Scanner ler = new Scanner (System.in);
    	int num;
    	
    	 System.out.println("Digite o numero do bem: ");
         String aux = ler.nextLine();
         num = Integer.parseInt(aux);
         
         if (num > 0){
        	 try{
        		 CBemPatrimonial controle = new CBemPatrimonial();
        		 System.out.println("Quantidade de manutencoes do bem " + num + ": " + controle.qtdDeManutencoes(num));
        	 }catch(IllegalArgumentException e){
        		 System.err.println("Bem inexistente!!!\n");
        	 }
         }else
        	 System.out.println("Todos os campos sao obrigatorios.\n");
         ler.close();
    }
    
    public void valorAcumulado(){
    	Scanner ler = new Scanner (System.in);
    	int num;
    	
    	 System.out.println("Digite o numero do bem: ");
         String aux = ler.nextLine();
         num = Integer.parseInt(aux);
         
         if (num > 0){
        	 try{
        		 CBemPatrimonial controle = new CBemPatrimonial();
        		 System.out.println("Valor acumulado de manutencoes do bem " + num + ": " + controle.valorAcumulado(num));
        	 }catch(IllegalArgumentException e){
        		 System.err.println("Bem inexistente!!!\n");
        	 }
         }else
        	 System.out.println("Todos os campos sao obrigatorios.\n");
         ler.close();
    }
}
