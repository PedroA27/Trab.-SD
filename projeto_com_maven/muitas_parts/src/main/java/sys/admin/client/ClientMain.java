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
	public static void clearConsole() {
		System.out.println("\u001b[H\u001b[2J");
	}
	
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
				+ "\nquit: Encerra a execucao do cliente."
				+ "\n-------------------------------------------------------------------------------"
				+ "\n Conectado ao servidor "+ serverName+" pela porta "+ serverPort
				+ "\n-------------------------------------------------------------------------------");
				
	}
	
	public static void printCommand(String command) {
		System.out.println("\n-------------------------------------------------------------------------------"
				+ "\nMétodo selecionado: "+command
				+ "\n-------------------------------------------------------------------------------");			
	}
	
	public static void printBar() {
		System.out.println("-------------------------------------------------------------------------------");			
	}
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
			
			printCommands(serverName,serverPort);
			String command = sc.nextLine();
			while(!command.equals("quit")) {
				switch(command) {
					case "bind":
						printCommand(command);
						System.out.println("Insira o nome do novo servidor: ");
						String newServerName = sc.nextLine();					
						System.out.println("Insira a porta do novo servidor: ");
						String newServerPort = sc.nextLine();
						client.bind(server+newServerPort+"/"+newServerName);
						serverName = newServerName;
						serverPort = newServerPort;
						clearConsole();
						break;
				
					case "listp":
						printCommand(command);
						List<Part> list = client.listp();
						Iterator<Part> iter = list.iterator();
						System.out.println("LISTA DE PARTS");
						while (iter.hasNext()) {
							Part partIter = iter.next();
							System.out.println("Nome da peca: " + partIter.getName());
							System.out.println("Id da peca: " + partIter.getPartId());
							printBar();
						}
						System.out.println("Pression ENTER para retornar a lista de comandos");
						sc.nextLine();
						clearConsole();
						break;				
						
					case "getp":
						printCommand(command);
						System.out.print("\nInsira o id desejado: ");
						UUID idPart;
						try {
							idPart = UUID.fromString(sc.nextLine());
						} catch (Exception e) {
							System.out.println("Id inválido");
							printBar();
							System.out.println("Pression ENTER para retornar a lista de comandos");
							sc.nextLine();
							clearConsole();
							break;
						}
						Part search = client.getp(idPart);
						if (search != null) {
							client.setPart(search);
						} else {
							System.out.println("Part não existente");
							printBar();
							System.out.println("Pression ENTER para retornar a lista de comandos");
							sc.nextLine();

						}
						clearConsole();
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
								System.out.println("SUBPEÇAS: ");
								System.out.println("---------------------");
								int qtdAggregated = 0;
								int qtdPrimitive = 0;
								for(SubPartElement subPart : subParts){
									Part subPartPart = subPart.getSubPart();
									boolean isPrimitive = subPartPart.isPrimitive();
									System.out.println("	-Nome: "+subPartPart.getName());
									System.out.println("	-Quantidade: "+subPart.getSubPartQuantity()+"\n");
									System.out.println("	-Tipo da peça: "+(isPrimitive ? "AGREGADA" : "PRIMITIVA")+"\n");
									if(isPrimitive)
										qtdPrimitive++;
									else
										qtdAggregated++;
								}
								System.out.println("Quantidade de subpeças primitivas: "+qtdPrimitive);
								System.out.println("Quantidade de subpeças agregadas: "+qtdAggregated);
								System.out.println("---------------------");
							}														
						}
						else {
							System.out.println("Peça não encontrada");
						}
						printBar();
						System.out.println("Pression ENTER para retornar a lista de comandos");
						sc.nextLine();
						clearConsole();
						break;
					case "clearlist":
						client.clearlist();
						System.out.println("Lista limpa");	
						break;
					case "addsubpart":
						System.out.println("Insira a quantidade n de unidades da peca atual a serem adicionadas: ");
						int qtd = Integer.parseInt(sc.nextLine());
						client.addsubpart(qtd);
						
						break;
					case "addp":
						System.out.println("Insira o nome da part:");
				        String name = sc.nextLine();
				        
				        while(name.isEmpty()) {
				        	System.out.println("(Nome invalido)Insira o nome da part:");
					        name = sc.nextLine();
				        }

				        System.out.println("Insira a descricao da part:");
				        String description = sc.nextLine();
				        
				        while(description.isEmpty()) {
				        	System.out.println("(Descricao invalida)Insira a descricao da part:");
					        description = sc.nextLine();
				        }
				        client.addp(name, description);
				        break;

					case "getrepprop":
						client.getrepprop().forEach(prop -> System.out.println(prop));
					break;
					case "showsubparts":
						client.getSubParts().forEach((subPart, quantity) -> {
							try {
								System.out.println("Nome da subpeça: "+subPart.getName());
								System.out.println("Quantidade de subpeças: "+quantity);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
					break;
					default:
						System.out.println("Comando invalido");
						
				}
				// clearConsole();
				printCommands(serverName, serverPort);
				command = sc.nextLine();
			}
			
			
		} catch (Exception e) {
            
            e.printStackTrace();
		}
	}
}
