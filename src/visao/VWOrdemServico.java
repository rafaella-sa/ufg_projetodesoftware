package visao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controle.COrdemServico;

public class VWOrdemServico {
	public void entradaDeDados(){
        Scanner ler = new Scanner(System.in);
		String nomeDoSolicitante;
		String defeito;
		String dataDeDefeito;
		int grauDeUrgencia;
		String dataDeFechamento_da_os;
		String nomeDoReceptor;
		int numeroBem;
		int id;  
        
		String aux;
        System.out.println("Digite o nome do solicitante: ");
        nomeDoSolicitante = ler.nextLine();
        
        System.out.println("Digite o defeito do bem: ");
        ler.nextLine();
        defeito = ler.nextLine();
        
        System.out.println("Digite a data da ocorrência do defeito (no formato dd/mm/aaaa: ");
        ler.nextLine();
        dataDeDefeito = ler.nextLine();
        
        System.out.println("Qual é o grau de urgência? \nBAIXO(1)\nMEDIO(2)\nALTO(3)\nCRITICO(4)\nDIRETORIA(5)");
        ler.nextLine();
        aux = ler.nextLine();
        grauDeUrgencia = Integer.parseInt(aux);
        
        System.out.println("Digite a data de fechamento da ordem de servico (no formato dd/mm/aaaa): ");
        ler.nextLine();
        dataDeFechamento_da_os = ler.nextLine();
        
        System.out.println("Digite o nome do receptor: ");
        ler.nextLine();
        nomeDoReceptor = ler.nextLine();
        
        System.out.println("Digite o número do bem patrimonial: ");
        ler.nextLine();
        aux = ler.nextLine();
        numeroBem = Integer.parseInt(aux);
        		
        System.out.println("Digite o código de identificacao da ordem de servico: ");
        ler.nextLine();
        aux = ler.nextLine();
        id = Integer.parseInt(aux);
        
        if (grauDeUrgencia > 0 && numeroBem > 0 && id > 0 && !(nomeDoSolicitante.trim().equals("")) && !(defeito.trim().equals(""))
        		&& !(dataDeDefeito.trim().equals("")) && !(dataDeFechamento_da_os.trim().equals("")) && !(nomeDoReceptor.trim().equals(""))){
            try{
                COrdemServico controle = new COrdemServico();
                controle.gravar(nomeDoSolicitante, defeito, dataDeDefeito, 
                		grauDeUrgencia, dataDeFechamento_da_os, nomeDoReceptor, numeroBem, id);
                
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errORDEM")){
                    System.err.println("Numero de ordem de servico ja cadastrado!!!");
                }
                if (e.getMessage().equals("errBEM")){
                    System.err.println("O bem patrimonial nao existe!!!");
                    }
                if (e.getMessage().equals("errGRAU")){
                    System.err.println("O grau de urgência é inválido!!!");
                }
                if (e.getMessage().equals("errDATAD_INVALIDA")){
                    System.err.println("A data do defeito inserida é posterior à atual!!!");
                }
            }catch(ParseException e){
            	if (e.getMessage().equals("errDATAD_INVALIDA")){
                    System.err.println("Insira uma data de defeito valida no formato dd/mm/aaaa!!!");
                }
                if (e.getMessage().equals("errDATAF_INVALIDA")){
                    System.err.println("Insira uma data de fechamento da OS valida no formato dd/mm/aaaa!!!");
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
    	COrdemServico lista = new COrdemServico();
        String espacos = "                                        "; //40 espaços em branco
    	ArrayList<String> lstItens = lista.getItens();
    	System.out.println("\nID\tNum. bem\tNome do solicitante                     \tDefeito                                 \tData de defeito\t"
    			+ "Grau de urgencia\tData fech. OS\tNome do receptor");
    	for(String linha : lstItens){
    		String vet[] = linha.split(";");
    		System.out.print(vet[7]);
    		System.out.print("\t" + vet[6]);
            System.out.print("\t" + vet[0] + espacos.substring(0, 40-vet[0].length()));
            System.out.print("\t" + vet[1] + espacos.substring(0, 40-vet[1].length()));
            System.out.print("\t" + vet[2]);
            System.out.print("\t" + vet[3]);
            System.out.print("\t" + vet[4]);
            System.out.print("\t" + vet[5] + "\n");
    	}
    	System.out.println("\n");
    }
    
    public void excluir(){
        Scanner ler = new Scanner(System.in);
        int id;
        
        String aux;
        System.out.println("Digite o codigo de identificacao da OS: ");
        aux = ler.nextLine();
        id = Integer.parseInt(aux);
        
        if (id > 0){
            try{
                COrdemServico controle = new COrdemServico();
                if (controle.excluir(id))
                        System.out.println( "\n\tCategoria "+id+" excluida com sucesso!!!\n");
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errORCAMENTO")){
                    System.err.println("Ordem de servico nao pode ser excluida, pois existem orcamentos atribuidos a ela!!!");
                }
                if (e.getMessage().equals("errORDEM_INEXISTENTE")){
                	System.err.println("Ordem inexistente!!!");
                }
            }catch(Exception e){
                System.err.println("ERRO nas gravacoes: " + e.getMessage());
            }
        }
        else{
        	System.out.println("Todos os campos sao de preenchimento obrigatorio.");
        }
        ler.close();
    }
    
    public void tempoDeConserto(){
    	Scanner ler = new Scanner(System.in);
    	int os;
    	
    	String aux;
    	System.out.println("Digite a ordem de servico: ");
    	aux = ler.nextLine();
    	os = Integer.parseInt(aux);
    	
    	if(os > 0){
    		try{
    			COrdemServico controle = new COrdemServico();
    			System.out.println("Tempo de conserto em dias: " + controle.tempoDeConserto(os));
    		}catch(IllegalArgumentException e){
    			System.err.println("Ordem de servico inexistente!\n");
    		}
    	}else
    		System.out.println("Todos os campos sao obrigatorios.\n");
    	ler.close();
    }
}

