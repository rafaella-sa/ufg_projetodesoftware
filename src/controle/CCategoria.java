package controle;

import java.util.ArrayList;
import modelo.Categoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CCategoria {
	ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
    
    public CCategoria(){
        lerArquivo();
    }
    
    public boolean verificarCategoriaExiste(int cod){
    	for(Categoria ctg : listaCategorias){
    		if(ctg.getCodigo() == cod)
    			return true;
    	}	
    	return false;
    }
    
    public boolean gravar(int cod, String nm){
        //verificar se a categoria já existe
        for(Categoria ctg : listaCategorias){
            if (ctg.getCodigo() == cod){
                throw new IllegalArgumentException("errCODIGO");
            }
            if (ctg.getNome().equals(nm)){
                throw new IllegalArgumentException("errNOME");
            }
        }
        //incluir no array
        Categoria novaCategoria = new Categoria();
        novaCategoria.setCodigo(cod);
        novaCategoria.setNome(nm);
        listaCategorias.add(novaCategoria);
        this.gravarArquivo();
        return true;
    }
    
    private boolean gravarArquivo(){
        try{
            BufferedWriter arqCategoria = new BufferedWriter(new FileWriter("categorias.txt"));
            for(Categoria ctg : listaCategorias)  {
                arqCategoria.append(ctg.serializar() + "\n");
            }
            arqCategoria.close();
        }catch (Exception e){
            System.err.println("Erro ao gravar arquivo de categorias: "+ e.getMessage());
        }
        return false;
    }
    
    private boolean lerArquivo(){
        try{
            BufferedReader arqCategoria = new BufferedReader(new FileReader("categorias.txt"));
            String linha = "";
            listaCategorias.clear();
            while( (linha = arqCategoria.readLine()) != null){
                Categoria ctg = new Categoria(linha);
                listaCategorias.add(ctg);
            }
            arqCategoria.close();

        }catch (FileNotFoundException e){
            ;;
        }catch (IOException e){
            System.err.println("Erro ao ler arquivo de categorias: " + e.getMessage());
        } 
        return false;
    }
    
    public ArrayList<String> getItens(){
    	ArrayList<String> vet = new ArrayList<String>();
    	for(Categoria ctg : listaCategorias){
    		vet.add(ctg.serializar());
    	}
    	return vet;
    }
    
    public boolean excluir(int cod){
    	boolean bExcluiu = false;
    	//verificar se pode excluir (impedir se houver BemPatrimonal associado a esta categoria)
    	try{
    		if((new CBemPatrimonial()).verificarCategoriaAssociada(cod))
    			throw new Exception();
    	}catch(Exception e){
    		throw new IllegalArgumentException("errBEM_NA_CATEGORIA");
    	}
    	
    	//Procurar o elemento na lista e excluir
    	for(Categoria ctg : listaCategorias){
    		if (ctg.getCodigo() == cod){
    			listaCategorias.remove(ctg);
    	        this.gravarArquivo();
    	        bExcluiu = true;
    	        break;
    		}
    	}
    	if (!bExcluiu){
    		throw new IllegalArgumentException("errCATEGORIA_INEXISTENTE");
    	}
    	return true;
    }
    
}
