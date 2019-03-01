package visao;

import java.util.ArrayList;
import java.util.Scanner;

import controle.CCategoria;

public class VWCategoria {
	public void entradaDeDados(){
        Scanner ler = new Scanner(System.in);
        int codigo;
        String nome;   
        
        String aux;
        System.out.print("\nDigite o codigo da categoria: ");
        aux = ler.nextLine();
        codigo = Integer.parseInt(aux);
        
        System.out.print("Digite o nome da categoria: ");
        ler.nextLine();
        ler.nextLine(); //consumir o enter
        nome = ler.nextLine();
        //System.out.println(nome);      
        
        if (codigo > 0 && !(nome.trim().equals(""))){
            try{
                CCategoria controle = new CCategoria();
                controle.gravar(codigo, nome);
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errCODIGO")){
                    System.err.println("Numero de categoria ja cadastrado!!!");
                }
                if (e.getMessage().equals("errNOME")){
                    System.err.println("Nome de categoria ja cadastrado!!!");
                }
            }catch(Exception e){
                System.err.println("ERRO nas gravacoes: " + e.getMessage());
            }
                    
        } else {
            System.out.println("\n\tTodos os campos sao obrigatorios.");
        }
        ler.close();
    }
    
    
    public void listarDados(){
    	CCategoria lista = new CCategoria();
        String espacos = "                                        "; //40 espaços em branco
    	ArrayList<String> lstItens = lista.getItens();
    	System.out.println("\nNumero\tNome                                    ");
    	for(String linha : lstItens){
    		String vet[] = linha.split(";");
    		System.out.print(vet[0] );
            System.out.print("\t" + vet[1] + espacos.substring(0, 40-vet[1].length()) + "\n");
    	}
    	System.out.println("\n");
    }
    
    public void excluir(){
        Scanner ler = new Scanner(System.in);
        int numero;
        
        String aux;
        System.out.println("Digite o codigo da categoria: ");
        aux = ler.nextLine();
        numero = Integer.parseInt(aux);
        
        if (numero > 0){
            try{
                CCategoria controle = new CCategoria();
                if (controle.excluir(numero))
                    System.out.println( "\n\tCategoria "+numero+" excluida com sucesso!!!\n");             
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errBEM_NA_CATEGORIA")){
                    System.err.println("Categoria nao pode ser excluida, pois existem bens que se enquadram nela!!!");
                }
                if (e.getMessage().equals("errCATEGORIA_INEXISTENTE")){
                	System.err.println("Categoria inexistente!!!");
                }
            }catch(Exception e){
                System.err.println("ERRO nas gravacoes: " + e.getMessage());
            }
        }
        ler.close();
    }
}
