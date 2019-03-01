package controle;

import java.util.ArrayList;

import modelo.Sala;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSala {
	ArrayList<Sala> listaSalas = new ArrayList<Sala>();
    
    public CSala(){
        lerArquivo();
    }
    
    public boolean verificarSalaExiste(int num){
    	for(Sala sl : listaSalas){
    		if(sl.getNumero() == num)
    			return true;
    	}	
    	return false;
    }
    
    public boolean gravar(int num, String nm, float largura, float comprimento){
        //verificar se a sala já existe
        for(Sala sl : listaSalas){
            if (sl.getNumero() == num){
                throw new IllegalArgumentException("errNUMERO");
            }
            if (sl.getNome().equals(nm)){
                throw new IllegalArgumentException("errNOME");
            }
        }
        //Incluir no Array
        Sala novaSala = new Sala();
        novaSala.setNumero(num);
        novaSala.setNome(nm);
        novaSala.setLargura(largura);
        novaSala.setComprimento(comprimento);
        listaSalas.add(novaSala);
        this.gravarArquivo();
        return true;
    }
    
    
    private boolean gravarArquivo(){
        try{
            BufferedWriter arqSala = new BufferedWriter(new FileWriter("salas.txt"));
            for(Sala sl : listaSalas)  {
                arqSala.append(sl.serializar() + "\n");
            }
            arqSala.close();
        }catch (Exception e){
            System.err.println("Erro ao gravar arquivo de salas: "+ e.getMessage());
        }
        
        return false;
    }
    
    private boolean lerArquivo(){
        try{
            BufferedReader arqSala = new BufferedReader(new FileReader("salas.txt"));
            String linha = "";
            listaSalas.clear();
            while( (linha = arqSala.readLine()) != null){
                Sala sl = new Sala(linha)                ;
                listaSalas.add(sl);
            }
            arqSala.close();

        }catch (FileNotFoundException e){
            ;;
        }catch (IOException e){
            System.err.println("Erro ao ler arquivo de salas: " + e.getMessage());
        }
        
        return false;
    }
    
    public ArrayList<String> getItens(){
    	ArrayList<String> vet = new ArrayList<String>();
    	
    	for(Sala sl : listaSalas){
    		vet.add(sl.serializar());
    	}
    	
    	return vet;
    }
    
    public boolean excluir(int num){
    	boolean bExcluiu = false;
    	//Verificar se pode excluir (impedir se houver BemPatrimonal nesta sala)
    	try{
    		if((new CBemPatrimonial()).verificarSalaAssociada(num))
    			throw new Exception();
    	}catch(Exception e){
    		throw new IllegalArgumentException("errBENS_NA_SALA");
    	}
    	
    	//Porcurar o elemento na lista e excluir
    	for(Sala sl : listaSalas){
    		if (sl.getNumero() == num){
    			listaSalas.remove(sl);
    	        this.gravarArquivo();
    	        bExcluiu = true;
    	        break;
    		}
    	}
    	if (!bExcluiu){
    		throw new IllegalArgumentException("errSALA_INEXISTENTE");

    	}
    	return true;
    }
    
    public int listarBensPorSala(int num){
    	if(!verificarSalaExiste(num))
    		throw new IllegalArgumentException("errSALA");
    	CBemPatrimonial controle = new CBemPatrimonial();
    	return controle.listarBensPorSala(num);
    }
}
