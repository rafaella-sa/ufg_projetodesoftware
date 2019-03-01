package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.BemPatrimonial;
import modelo.SituacaoBem;

public class CBemPatrimonial {
	ArrayList<BemPatrimonial> listaBens = new ArrayList<BemPatrimonial>();
    
    public CBemPatrimonial(){
        lerArquivo();
    }
    
    public boolean verificarCategoriaAssociada(int cod){
    	for(BemPatrimonial bem: listaBens)
    		if(bem.getCategoria() == cod)
    			return true;
    	return false;
    }
    
    public boolean verificarSalaAssociada(int num){
    	for(BemPatrimonial bem: listaBens)
    		if(bem.getSala() == num)
    			return true;
    	return false;
    }
    
    public boolean verificarBemExiste(int num){
    	for(BemPatrimonial bem : listaBens){
    		if(bem.getNumero() == num)
    			return true;
    	}	
    	return false;
    }
    
    public boolean gravar(int numero, String dataDaCompra, String fabricante, 
    		String descricao, int situacao, int categoria, int sala) throws ParseException{
    	//verificar se o bem já existe
    	if(verificarBemExiste(numero))
    		throw new IllegalArgumentException("errBEM");
    	
    	//verificar se a sala já existe
    	if(!(new CSala()).verificarSalaExiste(sala)){
    		throw new IllegalArgumentException("errSALA");
    	}
    	
	    //verificar se a categoria já existe
    	if(!(new CCategoria()).verificarCategoriaExiste(categoria)){
	       	throw new IllegalArgumentException("errCATEGORIA");
	    }
	    
	    //verificar se o codigo da situacao é válido
	    if(situacao != SituacaoBem.BAIXADO.getSituacao() 
	    		&& situacao != SituacaoBem.INCORPORADO.getSituacao()
	    		&& situacao != SituacaoBem.EM_MANUTENCAO.getSituacao())
	    	throw new IllegalArgumentException("errSITUACAO");
	    
	    //verificar se a data é válida
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    df.setLenient(false);
	    try{
	    	df.parse(dataDaCompra);
	    }catch(ParseException e){
	    	throw new ParseException("errDATA_INVALIDA", 0);
	    }
	    String vet[] = dataDaCompra.split("/");
	    LocalDate data = LocalDate.of(Integer.parseInt(vet[2]), Integer.parseInt(vet[1]), Integer.parseInt(vet[0]));
	    if(data.isAfter(LocalDate.now()))
	    	throw new IllegalArgumentException("errDATA_INVALIDA");
        //incluir no array
	    BemPatrimonial novoBem = new BemPatrimonial();
        novoBem.setNumero(numero);
        novoBem.setDataDaCompra(data);
        novoBem.setFabricante(fabricante);
        novoBem.setDescricao(descricao);
        novoBem.setSituacao(situacao);
        novoBem.setCategoria(categoria);
        novoBem.setSala(sala);
        listaBens.add(novoBem);
        this.gravarArquivo();
        return true;
    }
    
    private boolean gravarArquivo(){
        try{
            BufferedWriter arqBem = new BufferedWriter(new FileWriter("bens.txt"));
            for(BemPatrimonial bem : listaBens)  {
                arqBem.append(bem.serializar() + "\n");
            }
            arqBem.close();
        }catch (Exception e){
            System.err.println("Erro ao gravar arquivo de bens: "+ e.getMessage());
        }
        return false;
    }
    
    private boolean lerArquivo(){
        try{
            BufferedReader arqBem = new BufferedReader(new FileReader("bens.txt"));
            String linha = "";
            listaBens.clear();
            while( (linha = arqBem.readLine()) != null){
                BemPatrimonial bem = new BemPatrimonial(linha);
                listaBens.add(bem);
            }
            arqBem.close();

        }catch (FileNotFoundException e){
            ;;
        }catch (IOException e){
            System.err.println("Erro ao ler arquivo de bens: " + e.getMessage());
        } 
        return false;
    }
    
    public ArrayList<String> getItens(){
    	ArrayList<String> vet = new ArrayList<String>();
    	for(BemPatrimonial bem : listaBens){
    		vet.add(bem.serializar());
    	}
    	return vet;
    }
    
    public boolean excluir(int num){
    	boolean bExcluiu = false;
    	//verificar se pode excluir (impedir se houver OrdemServico associado a este bem)
    	try{
    		if((new COrdemServico()).verificarBemAssociado(num))
    			throw new Exception();
    	}catch(Exception e){
    		throw new IllegalArgumentException("errOS_ASSOCIADA");
    	}
    	//Procurar o elemento na lista e excluir
    	for(BemPatrimonial bem : listaBens){
    		if (bem.getNumero() == num){
    			listaBens.remove(bem);
    	        this.gravarArquivo();
    	        bExcluiu = true;
    	        break;
    		}
    	}
    	if (!bExcluiu){
    		throw new IllegalArgumentException("errBEM_INEXISTENTE");
    	}
    	return true;
    }
    
    public int listarBensPorSala(int sala){
    	int quant = 0;
    	for(BemPatrimonial bem : listaBens){
    		if(bem.getSala() == sala)
    			quant++;
    	}
    	return quant;
    }
    
    public int qtdDeManutencoes(int num){
    	if(!verificarBemExiste(num))
    		throw new IllegalArgumentException("errBEM");
    	COrdemServico controle = new COrdemServico();
    	return controle.qtdDeManutencoes(num);
    }
    
    public double valorAcumulado(int num){
    	if(!verificarBemExiste(num))
    		throw new IllegalArgumentException("errBEM");
    	COrdemServico controle = new COrdemServico();
    	return controle.valorAcumulado(num);
    }
}
