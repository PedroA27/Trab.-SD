package sys.admin.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import sys.admin.Interfaces.Part;
import sys.admin.Interfaces.SubPartElement;



public class ClientMain {
	//limpa o console
	public static void clearConsole() {
		System.out.println("\u001b[H\u001b[2J");
	}
	//exibe os comandos disponíveis
	public static void printCommands(String serverName, String serverPort) {
		System.out.println("LISTA DE COMANDOS"
				+ "\n-------------------------------------------------------------------------------"
				+ "\nbind : Realiza a troca de servidor"
				+ "\nlistp : Lista as pecas do repositorio corrente"
				+ "\ngetp : Busca uma peca por codigo "
				+ "\nshowp :  Mostra atributos da peca corrente"
				+ "\nclearlist : Esvazia a lista de sub-pecas corrente"
				+ "\naddsubpart : Adiciona a lista de sub-pecas corrente n unidades da peca corrente"
				+ "\naddp : Adiciona uma peca ao repositorio corrente"
				+ "\nshowsubparts: Mostra a lista de subparts corrente"
				+ "\ngetrepprop: Exibe as informações do repositório corrente"
				+ "\nquit: Encerra a execucao do cliente."
				+ "\n-------------------------------------------------------------------------------"
				+ "\n Conectado ao servidor "+ serverName+" pela porta "+ serverPort
				+ "\n-------------------------------------------------------------------------------");			
	}
	//exibe o comando selecionado
	public static void printCommand(String command) {
		System.out.println("\n-------------------------------------------------------------------------------"
				+ "\nMétodo selecionado: "+command
				+ "\n-------------------------------------------------------------------------------");			
	}
	//exibe uma barra, utilizada para melhor visualização e separação dos dados para o usuário
	public static void printBar() {
		System.out.println("-------------------------------------------------------------------------------");			
	}
	//realiza uma parada para visalização dos dados, e limpa o console logo em seguida
	public static void printExit(Scanner sc) {
		printBar();
		System.out.println("Pression ENTER para retornar a lista de comandos");
		sc.nextLine();
		clearConsole();		
	}
	//onde a magia acontece: as entradas de comandos são capturadas, a conexão é feita com os servidores,
	//e a utilização da classe Client(que, por sua vez, utiliza stub's fornecidos pelo servidor)
	//temos um laço while, que server para ler continuamente as entradas do usuário, e 
	//um switch, que verificará qual é o comando, e com base nisso, o que executar
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Insira o nome do servidor: ");
			String serverName = sc.nextLine();
			clearConsole();
			
			System.out.println("Insira a porta do servidor: ");
			String serverPort = sc.nextLine();
			clearConsole();
			
			String server = "//localhost:";
					
			Client client = new Client(server+serverPort+"/"+serverName);
			
			System.out.println("Conexao Realizada");
			printCommands(serverName,serverPort);
			String command = sc.nextLine();
			while(!command.equals("quit")) {
				switch(command) {
					case "bind":
						printCommand(command);
						System.out.println("Insira o nome do novo servidor: ");
						String newServerName = sc.nextLine();		
						printBar();
						System.out.println("Insira a porta do novo servidor: ");
						String newServerPort = sc.nextLine();
						client.bind(server+newServerPort+"/"+newServerName);
						serverName = newServerName;
						serverPort = newServerPort;
						printExit(sc);
						break;
				
					case "listp":
						printCommand(command);
						List<Part> list = client.listp();
						Iterator<Part> iter = list.iterator();
						System.out.println("LISTA DE PARTS");
						while (iter.hasNext()) {
							printBar();
							Part partIter = iter.next();
							System.out.println("Nome da peca: " + partIter.getName());
							System.out.println("Id da peca: " + partIter.getPartId());
						}
						printExit(sc);
						break;				
						
					case "getp":
						printCommand(command);
						System.out.print("\nInsira o id desejado: ");
						UUID idPart;
						try {
							idPart = UUID.fromString(sc.nextLine());
						} catch (Exception e) {
							System.out.println("Id inválido");
							printExit(sc);
							break;
						}
						Part search = client.getp(idPart);
						if (search != null) {
							client.setPart(search);
							System.out.println("Metodo Concluido");
						} else {
							System.out.println("Part não existente");
							

						}
						printExit(sc);
						break;
						
					case "showp":
						printCommand(command);
						Part show = client.getPart();
						if(show != null) {
							System.out.println("Id: " + show.getPartId());
							System.out.println("Nome: " + show.getName());
							System.out.println("Descrição: " + show.getDescription());
							System.out.println("Nome do repositório da peça: "+client.getPartRepo());
							List<SubPartElement> subParts = show.getSubParts();
							boolean isAggregated = subParts.size() > 0;
							System.out.println("Tipo da peça: " + (isAggregated ? "AGREGADA" : "PRIMITIVA"));
							if(isAggregated){
								printBar();
								System.out.println("SUBPEÇAS\n");
//								System.out.println("---------------------");

								int qtdAggregated = 0;
								int qtdPrimitive = 0;
								for(SubPartElement subPart : subParts){
									Part subPartPart = subPart.getSubPart();
									boolean isPrimitive = subPartPart.isPrimitive();
									printBar();
									System.out.println("	-Nome: "+subPartPart.getName());
									System.out.println("	-Quantidade: "+subPart.getSubPartQuantity()+"\n");
									System.out.println("	-Tipo da peça: "+(isPrimitive ? "PRIMITIVA" : "AGREGADA")+"\n");
									if(isPrimitive)
										qtdPrimitive++;
									else
										qtdAggregated++;
								}
								printBar();
								System.out.println("Quantidade de subpeças primitivas: "+qtdPrimitive);
								System.out.println("Quantidade de subpeças agregadas: "+qtdAggregated);
//								System.out.println("---------------------");

							}														
						}
						else {
							printBar();
							System.out.println("Peça não encontrada");
						}
						printExit(sc);
						break;
					case "clearlist":
						printCommand(command);
						client.clearlist();
						System.out.println("Lista limpa");
						printExit(sc);
						break;
					case "addsubpart":
						printCommand(command);
						System.out.print("\nInsira a quantidade n de unidades da peca atual a serem adicionadas: ");
						int qtd = Integer.parseInt(sc.nextLine());
						client.addsubpart(qtd);
						printBar();
						System.out.println("Metodo concluido");

						printExit(sc);
						break;
					case "addp":
						printCommand(command);
						System.out.print("\nInsira o nome da part: ");
				        String name = sc.nextLine();
				        printBar();
				        while(name.isEmpty()) {
				        	System.out.print("\nErro: Nome invalido"
				        			+ "\nInsira o nome da part: ");
					        name = sc.nextLine();
					        printBar();
				        }

				        System.out.println("Insira a descricao da part:");
				        String description = sc.nextLine();
				        printBar();
				        
				        while(description.isEmpty()) {
				        	System.out.print("\nErro: Descricao invalida"
				        			+ "\nInsira a descricao da part: ");
					        description = sc.nextLine();
				        }
				        client.addp(name, description); 
				        System.out.println("Metodo concluido");

						printExit(sc);
				        break;

					case "getrepprop":
						printCommand(command);
						client.getrepprop().forEach(prop -> System.out.println(prop));
						printExit(sc);
					break;
					case "showsubparts":
						printCommand(command);
						System.out.println("SUB-PECAS");
						client.getSubParts().forEach((subPart, quantity) -> {
							try {
								printBar();
								System.out.println("Nome da subpeça: "+subPart.getName());
								System.out.println("Quantidade de subpeças: "+quantity);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
						printExit(sc);
					break;
					default:
						printBar();
						System.out.println("Comando Invalido");

						printExit(sc);
						
				}
				// clearConsole();
				printCommands(serverName, serverPort);
				System.out.print("\nInsira o comando desejado: ");
				command = sc.nextLine();
			}
			
			
		} catch (Exception e) {
            
            e.printStackTrace();
		}
	}
}