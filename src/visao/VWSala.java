package visao;
import controle.CSala;

import java.util.ArrayList;
import java.util.Scanner;

public class VWSala {
	public void entradaDeDados(){
        Scanner ler = new Scanner(System.in);
        int numero;
        String nome;
        float largura, comprimento;
        
        String aux;
        System.out.print("\nDigite o numero da sala: ");
        aux = ler.nextLine();
        numero = Integer.parseInt(aux);
        
        System.out.print("Digite o nome da sala: ");
        ler.nextLine(); //consumir o enter
        nome = ler.nextLine();
        System.out.println(nome);
        
        System.out.print("Digite a largura: ");
        ler.nextLine();
        aux = ler.nextLine();
        largura = Float.parseFloat(aux);
        
        System.out.print("Digite o comprimento: ");
        ler.nextLine();
        aux = ler.nextLine();
        comprimento = Float.parseFloat(aux);
        
        if (numero > 0 && !(nome.trim().equals("")) && largura > 0 && comprimento > 0){
            try{
               
                CSala controle = new CSala();
                controle.gravar(numero, nome, largura, comprimento);
                
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errNUMERO")){
                    System.err.println("Numero de sala ja cadastrado!!!");
                }
                if (e.getMessage().equals("errNOME")){
                    System.err.println("Nome de sala ja cadastrado!!!");
                }
            }catch(Exception e){
                System.err.println("ERRO na gravacoes: " + e.getMessage());
            }
                    
        } else {
            System.out.println("\n\tTodos os campos sao obrigatorios.");
        }
        ler.close();
    }
    
    
    public void listarDados(){
    	CSala lista = new CSala();
        String espacos = "                                        "; //40 espaços em branco
    	ArrayList<String> lstItens = lista.getItens();
    	System.out.println("\nNumero\tNome                                    Largura\tComprimento");
    	for(String linha : lstItens){
    		String vet[] = linha.split(";");
    		System.out.print(vet[0] );
                System.out.print("\t" + vet[1] + espacos.substring(0, 40-vet[1].length()) );
                System.out.print(vet[2]);
                System.out.println("\t  " + vet[3]);
    	}
    	System.out.println("\n");

    }
    
    public void excluir(){
        Scanner ler = new Scanner(System.in);
        int numero;
        
        System.out.print("\nDigite o numero da sala: ");
        String aux = ler.nextLine();
        numero = Integer.parseInt(aux);
        
        if (numero > 0 ){
            try{
                CSala controle = new CSala();
                if (controle.excluir(numero))
                        System.out.println( "\n\tSala "+numero+" excluida com sucesso!!!\n");
                
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errBENS_NA_SALA")){
                    System.err.println("Sala nao pode ser exclu�da, pois existem bens dentro dela!!!");
                }
                if (e.getMessage().equals("errSALA_INEXISTENTE")){
                	System.err.println("Sala inexistente!!!");
                }
            }catch(Exception e){
                System.err.println("ERRO na gravacoes: " + e.getMessage());
            }
        }
        ler.close();
    }
    
    public void listarBensPorSala(){
    	Scanner ler = new Scanner(System.in);
    	int num;
    	
    	System.out.println("Digite o numero da sala: ");
    	String aux = ler.nextLine();
    	num = Integer.parseInt(aux);
    	if(num > 0){
    		try{
    			CSala controle = new CSala();
    			int quant = controle.listarBensPorSala(num);
    			if(quant > 0)
    				System.out.println("Quantidade de bens na sala " + num + ": " + quant + "\n");
    			else
    				System.out.println("Sala vazia!!!\n");
    		}catch(IllegalArgumentException e){
    			System.err.println("Sala inexistente!!!");
    		}
    	}else{
    		System.out.println("Todos os campos sao obrigatorios.");
    	}
    	ler.close();
    }
}
