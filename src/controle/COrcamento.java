package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Orcamento;

public class COrcamento {
	ArrayList<Orcamento> listaOrcamentos = new ArrayList<Orcamento>();
    
    public COrcamento(){
        lerArquivo();
    }
    
    public int contarOrcamentos(int id){
    	int cont = 0;
    	for(Orcamento orc : listaOrcamentos){
            if (orc.getOrdemservico() == id){
            	cont++;
            	if(cont == 5)
            		return cont;
            }
    	}
    	return cont;
    }
    
    public boolean verificarOrcamentos(int id){
    	return (contarOrcamentos(id) == 5);
    }
    
    public boolean gravar(String nomeDaPrestadora, int previsaoDeConsertoEmDias, String nomeDoContato,
    		String telefone, double preco, int ordemservico, int id){
        //verificar se o orcamento já existe
        for(Orcamento orc : listaOrcamentos){
            if (orc.getId() == id){
                throw new IllegalArgumentException("errORCAMENTO");
            }
        }
        //verificar se a ordem de servico já existe
        if(!(new COrdemServico()).verificarOrdemExiste(ordemservico))
    		throw new IllegalArgumentException("errORDEM");
        //Incluir no Array
        Orcamento novoOrcamento = new Orcamento();
        novoOrcamento.setNomeDaPrestadora(nomeDaPrestadora);
        novoOrcamento.setPrevisaoDeConsertoEmDias(previsaoDeConsertoEmDias);
        novoOrcamento.setNomeDoContato(nomeDoContato);
        novoOrcamento.setTelefone(telefone);
        novoOrcamento.setPreco(preco);
        novoOrcamento.setOrdemservico(ordemservico);
        novoOrcamento.setId(id);
        listaOrcamentos.add(novoOrcamento);
        this.gravarArquivo();
        return true;
    }
    
    
    private boolean gravarArquivo(){
        try{
            BufferedWriter arqOrcamento = new BufferedWriter(new FileWriter("orcamentos.txt"));
            for(Orcamento orc : listaOrcamentos)  {
                arqOrcamento.append(orc.serializar() + "\n");
            }
            arqOrcamento.close();
        }catch (Exception e){
            System.err.println("Erro ao gravar arquivo de orcamentos: "+ e.getMessage());
        }
        return false;
    }
    
    private boolean lerArquivo(){
        try{
            BufferedReader arqOrcamento = new BufferedReader(new FileReader("orcamentos.txt"));
            String linha = "";
            listaOrcamentos.clear();
            while( (linha = arqOrcamento.readLine()) != null){
                Orcamento orc = new Orcamento(linha)                ;
                listaOrcamentos.add(orc);
            }
            arqOrcamento.close();

        }catch (FileNotFoundException e){
            ;;
        }catch (IOException e){
            System.err.println("Erro ao ler arquivo de orcamentos: " + e.getMessage());
        }
        
        return false;
    }
    
    public ArrayList<String> getItens(){
    	ArrayList<String> vet = new ArrayList<String>();
    	
    	for(Orcamento orc : listaOrcamentos){
    		vet.add(orc.serializar());
    	}
    	
    	return vet;
    }
    
    public boolean excluir(int id){
    	boolean bExcluiu = false;
    	//Procurar o elemento na lista e excluir
    	for(Orcamento orc : listaOrcamentos){
    		if (orc.getId() == id){
    			listaOrcamentos.remove(orc);
    	        this.gravarArquivo();
    	        bExcluiu = true;
    	        break;
    		}
    	}
    	if (!bExcluiu){
    		throw new IllegalArgumentException("errORCAMENTO_INEXISTENTE");

    	}
    	return true;
    }
    
    public double valorAcumulado(int id){
    	double quant = 0;
    	for(Orcamento orc : listaOrcamentos){
    		if(orc.getOrdemservico() == id)
    			quant = quant + orc.getPreco();
    	}
    	return quant;
    }
}
