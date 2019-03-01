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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import modelo.GrauUrgencia;
import modelo.OrdemServico;

public class COrdemServico {
ArrayList<OrdemServico> listaOrdens = new ArrayList<OrdemServico>();
    
    public COrdemServico(){
        lerArquivo();
    }
    
    public boolean verificarBemAssociado(int num){
    	for(OrdemServico os : listaOrdens){
    		if(os.getNumeroBem() == num)
    			return true;
    	}	
    	return false;
    }
    
    public boolean verificarOrdemExiste(int id){
    	for(OrdemServico os : listaOrdens){
    		if(os.getId() == id)
    			return true;
    	}	
    	return false;
    }
    
    public boolean gravar(String nom_sol, String def, String dataD, 
    		int grau, String dataF, String nom_rec, int nm_bem, int id)
    				throws ParseException{
    	//verificar se a ordem de servico já existe
    	for(OrdemServico os : listaOrdens){
    		if(os.getId() == id)
    			throw new IllegalArgumentException("errORDEM");
    	}
        //verificar se o bem existe
    	if(!(new CBemPatrimonial()).verificarBemExiste(nm_bem))
    		throw new IllegalArgumentException("errBEM");
        //verificar grau de urgencia
        if(grau != GrauUrgencia.BAIXO.getGrau() && grau != GrauUrgencia.MEDIO.getGrau()
	    		&& grau != GrauUrgencia.ALTO.getGrau() && grau != GrauUrgencia.CRITICO.getGrau()
	    		&& grau != GrauUrgencia.DIRETORIA.getGrau())
	    	throw new IllegalArgumentException("errGRAU");
        //verificar data de defeito
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    df.setLenient(false);
	    try{
	    	df.parse(dataD);
	    }catch(ParseException e){
	    	throw new ParseException("errDATAD_INVALIDA", 0);
	    }
	    String date[] = dataD.split("/");
	    LocalDate dataDef = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
	    if(dataDef.isAfter(LocalDate.now()))
	    	throw new IllegalArgumentException("errDATAD_INVALIDA");
        //verificar data de fechamento
	    try{
	    	df.parse(dataF);
	    }catch(ParseException e){
	    	throw new ParseException("errDATAF_INVALIDA", 0);
	    }
	    date = dataF.split("/");
	    LocalDate dataFec = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        //Incluir no Array
        OrdemServico novaOrdem = new OrdemServico();
        novaOrdem.setNomeDoSolicitante(nom_sol);
        novaOrdem.setDefeito(def);
        novaOrdem.setDataDeDefeito(dataDef);
        novaOrdem.setGrauDeUrgencia(grau);
        novaOrdem.setDataDeFechamento_da_os(dataFec);
        novaOrdem.setNomeDoReceptor(nom_rec);
        novaOrdem.setNumeroBem(nm_bem);
        novaOrdem.setId(id);
        this.gravarArquivo();
        return true;
    }
    
    private boolean gravarArquivo(){
        try{
            BufferedWriter arqOrdem = new BufferedWriter(new FileWriter("ordens.txt"));
            for(OrdemServico ord : listaOrdens)  {
                arqOrdem.append(ord.serializar() + "\n");
            }
            arqOrdem.close();
        }catch (Exception e){
            System.err.println("Erro ao gravar arquivo de salas: "+ e.getMessage());
        }
        
        return false;
    }
    
    private boolean lerArquivo(){
        try{
            BufferedReader arqOrdem = new BufferedReader(new FileReader("ordens.txt"));
            String linha = "";
            listaOrdens.clear();
            while( (linha = arqOrdem.readLine()) != null){
                OrdemServico ord = new OrdemServico(linha);
                listaOrdens.add(ord);
            }
            arqOrdem.close();

        }catch (FileNotFoundException e){
            ;;
        }catch (IOException e){
            System.err.println("Erro ao ler arquivo de ordens: " + e.getMessage());
        }
        
        return false;
    }
    
    public ArrayList<String> getItens(){
    	ArrayList<String> vet = new ArrayList<String>();
    	
    	for(OrdemServico ord : listaOrdens){
    		vet.add(ord.serializar());
    	}
    	
    	return vet;
    }
    
    public boolean excluir(int id){
    	boolean bExcluiu = false;
    	//Verificar se pode excluir (impedir se houver Orcamento associado)
    	if(new COrcamento().contarOrcamentos(id) > 0)
    		throw new IllegalArgumentException("errORCAMENTO");
    	//Procurar o elemento na lista e excluir
    	for(OrdemServico ord : listaOrdens){
    		if (ord.getId() == id){
    			listaOrdens.remove(ord);
    	        this.gravarArquivo();
    	        bExcluiu = true;
    	        break;
    		}
    	}
    	if (!bExcluiu){
    		throw new IllegalArgumentException("errORDEM_INEXISTENTE");
    	}
    	return true;
    }
    
    public int qtdDeManutencoes(int bem){
    	int quant = 0;
    	for(OrdemServico os : listaOrdens){
    		if(os.getNumeroBem() == bem)
    			quant++;
    	}
    	return quant;
    }
    
    public double valorAcumulado(int bem){
    	double quant = 0;
    	COrcamento controle = new COrcamento();
    	for(OrdemServico os : listaOrdens){
    		if(os.getNumeroBem() == bem)
    			quant = quant + controle.valorAcumulado(os.getId());
    	}
    	return quant;
    }
    
    public int tempoDeConserto(int id){
    	int tempo = 0;
    	for(OrdemServico os : listaOrdens){
    		if(os.getId() == id){
    			tempo = (int)(ChronoUnit.DAYS.between(os.getDataDeDefeito(), os.getDataDeFechamento_da_os()));
    			return tempo;
    		}
    	}
    	return tempo;
    }
}
