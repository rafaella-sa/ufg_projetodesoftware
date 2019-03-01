package visao;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		int opcao1, opcao2;
		Scanner leitor = new Scanner (System.in);
  
		String aux;
        do{
        	System.out.println("*** MENU PRINCIPAL ***\n");
        	System.out.println("1. Salas");
        	System.out.println("2. Bens Patrimoniais");
        	System.out.println("3. Categorias de bens");
        	System.out.println("4. Ordens de servico");
        	System.out.println("5. Orcamentos\n");
        	System.out.println("0. SAIR\n");
        	System.out.println("Selecione a opcao: ");
        	aux = leitor.nextLine();
        	opcao1 = Integer.parseInt(aux);
            switch (opcao1){
                case 1: { //submenu de salas
	                	do{
	                		System.out.println("*** SUBMENU DE SALAS ***");
	                		System.out.println("1. Incluir novas salas");
	                    	System.out.println("2. Excluir salas");
	                    	System.out.println("3. Listar salas existentes");
	                    	System.out.println("4. Listar quantidade de bens por sala\n");
	                    	System.out.println("0. VOLTAR\n");
	                    	System.out.println("Selecione a opcao: ");
	                    	aux = leitor.nextLine();
	                    	opcao2 = Integer.parseInt(aux);
	                        switch (opcao2){
	                            case 1: { //cadastrar salas
	                                	(new VWSala()).entradaDeDados();
	                                	break;
	                            }
	                            case 2: { //excluir salas
	                            		(new VWSala()).excluir();
	                           	 		break;
	                            }
	                            case 3: { //listar salas
	                            		(new VWSala()).listarDados();
	                            		break;
	                            }
	                            case 4: { //listar quantidade de bens por sala
	                           	 		(new VWSala()).listarBensPorSala();
	                           	 		break;
	                            }
	                            case 0: {
	                        			System.out.println("Voltando ao menu principal...");
	                        			System.out.println("");
	                        			break;
	                            }
	                            default:{
	                            		System.out.println("Opcao invalida!");
	                            		System.out.println("");
	                            		break;
	                            }
	                        }
	                    }while(opcao2 != 0);
	                	break;
                }
                case 2: { //submenu de bens patrimoniais
                		do{
		                	System.out.println("*** SUBMENU DE BENS ***");
		            		System.out.println("1. Incluir novos bens");
		                	System.out.println("2. Excluir bens");
		                	System.out.println("3. Listar bens existentes");
		                	System.out.println("4. Quantidade de manutencoes de um bem");
		                	System.out.println("5. Valor acumulado de manutencoes em um bem\n");
		                	System.out.println("0. VOLTAR\n");
		                	System.out.println("Selecione a opcao: ");
		                	aux = leitor.nextLine();
		                	opcao2 = Integer.parseInt(aux);
		                	switch (opcao2){
		                        case 1: { //cadastrar bens
		                            	(new VWBemPatrimonial()).entradaDeDados();
		                            	break;
		                        }
		                        case 2: { //excluir bens
		                        	    (new VWBemPatrimonial()).excluir();
		                       	 		break;
		                        }
		                        case 3: { //listar bens
		                        		(new VWBemPatrimonial()).listarDados();
		                        		break;
		                        }
		                        case 4: { //quantidade de manutencoes de um bem
		                        		(new VWBemPatrimonial()).qtdDeManutencoes();
		                       	 		break;
		                        }
		                        case 5: { //valor acumulado de manutencoes em um bem
		                        		(new VWBemPatrimonial()).valorAcumulado();
		                        		break;
		                        }
		                        case 0: {
		                    			System.out.println("Voltando ao menu principal...");
		                    			System.out.println("");
		                    			break;
		                        }
		                        default:{
		                        		System.out.println("Opcao invalida!");
		                        		System.out.println("");
		                        		break;
		                        }
		                	}
                		}while(opcao2 != 0);
                		break;
                }
                case 3: { //submenu de categorias
                	do{
                		System.out.println("*** SUBMENU DE CATEGORIAS ***");
                		System.out.println("1. Incluir novas categorias");
                    	System.out.println("2. Excluir categorias");
                    	System.out.println("3. Listar categorias existentes\n");
                    	System.out.println("0. VOLTAR\n");
                    	System.out.println("Selecione a opcao: ");
                    	aux = leitor.nextLine();
	                	opcao2 = Integer.parseInt(aux);
                        switch (opcao2){
                            case 1: { //cadastrar categorias
                                	(new VWCategoria()).entradaDeDados();
                                	break;
                            }
                            case 2: { //excluir categorias
                            		(new VWCategoria()).excluir();
                            		break;
                            }
                            case 3: { //listar categorias
                           	 		(new VWCategoria()).listarDados();
                           	 		break;
                            }
                            case 0: {
                        			System.out.println("Voltando ao menu principal...");
                        			System.out.println("");
                        			break;
                            }
                            default:{
                            		System.out.println("Opcao invalida!");
                            		System.out.println("");
                            		break;
                            }
                        }
                    }while(opcao2 != 0);
	                break;
                }
                case 4: { //submenu de ordens de servico
                		do{
		                	System.out.println("*** SUBMENU DE ORDENS DE SERVICO ***");
		            		System.out.println("1. Incluir novas ordens");
		                	System.out.println("2. Excluir ordens");
		                	System.out.println("3. Listar ordens existentes");
		                	System.out.println("4. Verificar tempo de conserto de um bem\n");
		                	System.out.println("0. VOLTAR\n");
		                	System.out.println("Selecione a opcao: ");
		                	aux = leitor.nextLine();
		                	opcao2 = Integer.parseInt(aux);
		                	switch (opcao2){
		                        case 1: { //incluir ordem de servico
		                            	(new VWOrdemServico()).entradaDeDados();
		                            	break;
		                        }
		                        case 2: { //excluir ordem de servico
		                        		(new VWOrdemServico()).excluir();
		                        		break;
		                        }
		                        case 3: { //listar ordens de servico
		                       	 		(new VWOrdemServico()).listarDados();
		                       	 		break;
		                        }
		                        case 4: { //verificar tempo de conserto de um bem
		                        		(new VWOrdemServico()).tempoDeConserto();
	                       	 			break;
		                        }
		                        case 0: {
		                    			System.out.println("Voltando ao menu principal...");
		                    			System.out.println("");
		                    			break;
		                        }
		                        default:{
		                        		System.out.println("Opcao invalida!");
		                        		System.out.println("");
		                        		break;
		                        }
		                    }
                		}while(opcao2 != 0);
	                    break;
                }
                case 5: { //submenu de orcamentos
                		do{
		                	System.out.println("*** SUBMENU DE ORCAMENTOS ***");
		            		System.out.println("1. Incluir novos orcamentos");
		                	System.out.println("2. Excluir orcamentos");
		                	System.out.println("3. Listar orcamentos existentes\n");
		                	System.out.println("0. VOLTAR\n");
		                	System.out.println("Selecione a opcao: ");
		                	aux = leitor.nextLine();
		                	opcao2 = Integer.parseInt(aux);
		                	switch (opcao2){
	                            case 1: { //incluir orcamentos
	                                	(new VWOrcamento()).entradaDeDados();
	                                	break;
	                            }
	                            case 2: { //excluir orcamentos
	                            		(new VWOrcamento()).excluir();
	                            		break;
	                            }
	                            case 3: { //listar orcamentos
	                           	 		(new VWOrcamento()).listarDados();
	                           	 		break;
	                            }
	                            case 0: {
	                        			System.out.println("Voltando ao menu principal...");
	                        			System.out.println("");
	                        			break;
	                            }
	                            default:{
	                            		System.out.println("Opcao invalida!");
	                            		System.out.println("");
	                            		break;
	                            }
	                        }
                		}while(opcao2 != 0);
                		break;
                }
                case 0: {
                		System.out.println("Encerrando...");
                		break;
                }
                default:{
                		System.out.println("Opcao invalida!");
                		System.out.println("");
                		break; 
                }
           }
        }while(opcao1 != 0); 
        leitor.close();
    }
}

