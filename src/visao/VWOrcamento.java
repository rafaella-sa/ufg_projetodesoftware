package visao;

import java.util.ArrayList;
import java.util.Scanner;

import controle.CCategoria;
import controle.COrcamento;

public class VWOrcamento {
	public void entradaDeDados(){
        Scanner ler = new Scanner(System.in);
        String nomeDaPrestadora;
    	int previsaoDeConsertoEmDias;
    	String nomeDoContato;
    	String telefone;
    	double preco;
    	int ordemservico;
    	int id;
        
    	String aux;
        System.out.println("Digite o nome da prestadora: ");
        nomeDaPrestadora = ler.nextLine();
        
        System.out.println("Digite a previsao de conserto em dias: ");
        ler.nextLine();
        aux = ler.nextLine(); //consumir o enter
        previsaoDeConsertoEmDias = Integer.parseInt(aux);
        //System.out.println(nome);   
        
        System.out.println("Digite o nome do contato: ");
        ler.nextLine();
        nomeDoContato = ler.nextLine();
        
        System.out.println("Digite o telefone do contato: ");
        ler.nextLine();
        telefone = ler.nextLine();
        
        System.out.println("Digite o preco: ");
        ler.nextLine();
        aux = ler.nextLine();
        preco = Double.parseDouble(aux);
        
        System.out.println("Digite o codigo da OS: ");
        ler.nextLine();
        aux = ler.nextLine(); //consumir o enter
        ordemservico = Integer.parseInt(aux);
        
        System.out.println("Digite o codigo de identificacao do orcamento: ");
        ler.nextLine();
        aux = ler.nextLine(); //consumir o enter
        id = Integer.parseInt(aux);
        
        if (!(nomeDaPrestadora.trim().equals("")) && previsaoDeConsertoEmDias > 0 && !(nomeDoContato.trim().equals(""))
        		&& !(telefone.trim().equals("")) && preco > 0 && ordemservico > 0 && id > 0){
            try{
                COrcamento controle = new COrcamento();
                controle.gravar(nomeDaPrestadora, previsaoDeConsertoEmDias, nomeDoContato,
                        telefone, preco, ordemservico, id);
            }catch(IllegalArgumentException e){
                if (e.getMessage().equals("errORDEM")){
                    System.err.println("Numero de categoria ja cadastrado!!!");
                }
                if (e.getMessage().equals("errORCAMENTO")){
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
    	System.out.println("\nID\tOS\tNome da prestadora\tPrevisao (dias)\tNome do contato\tTelefone\tPreco\n");
    	for(String linha : lstItens){
    		String vet[] = linha.split(";");
    		System.out.print(vet[6] + "\t" + vet[5] + "\t");
            System.out.print("\t" + vet[0] + espacos.substring(0, 40-vet[0].length()));
            System.out.print("\t" + vet[1]);
            System.out.print("\t" + vet[2] + espacos.substring(0, 40-vet[2].length()));
            System.out.print("\t" + vet[3] + "\t" + vet[4] + "\n");
    	}
    	System.out.println("\n");
    }
    
    public void excluir(){
        Scanner ler = new Scanner(System.in);
        int id;
        
        System.out.println("Digite o codigo do orcamento: ");
        String aux = ler.nextLine();
        id = Integer.parseInt(aux);
        
        if (id > 0){
            try{
            	if ((new COrcamento()).excluir(id))
                       System.out.println( "\n\tOrcamento "+id+" excluido com sucesso!!!\n");             
            }catch(IllegalArgumentException e){
            	System.err.println("Orcamento inexistente!!!");
                }
            catch(Exception e){
                System.err.println("ERRO nas gravacoes: " + e.getMessage());
            }
        }
        ler.close();
    }
}
