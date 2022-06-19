import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import Server.Part;
import Server.PartRepository;
import Server.SubPartElement;

public class ClientMain {
	public static void clearConsole() {
		System.out.println("\u001b[H\u001b[2J");
	}
	
	public static void printCommands() {
		System.out.println("Lista de comandos:\nbind : Realiza a troca de servidor\nlistp \ngetp \nshowp \nclearlist \naddsubpart \naddp ");	
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
			
			printCommands();
			String command = sc.nextLine();
			while(!command.equals("quit")) {
				switch(command) {
					case "bind":
						System.out.println("Insira o nome do novo servidor: ");
						String newServerName = sc.nextLine();
						clearConsole();
						
						System.out.println("Insira a porta do novo servidor: ");
						String newServerPort = sc.nextLine();
						clearConsole();
						client.bind(server+newServerPort+"/"+newServerName);
						break;
					case "listp":
						System.out.println("Lista de Parts: ");
						Collection<Part> list = client.listp();
						Iterator<Part> iter= list.iterator();
						System.out.println("Lista de Parts\n\n");
						while (iter.hasNext()) {
				            System.out.println(iter.next().getName());
				        }
						break;
//						
						
					case "getp":
						System.out.println("Insira o id desejado:\n\n");
						UUID idPart;
						try {
							idPart = UUID.fromString(sc.nextLine());
						} catch (Exception e) {
							System.out.println("Id inválido");
							break;
						}
						Part search = client.getp(idPart);
						if(search != null) {
							client.setPart(search);
						}
						else{
							System.out.println("Part não existente");
						}
						break;
					case "showp":
						Part show = client.getPart();
						if(show != null) {
							System.out.println("Id: " + show.getPartId());
							System.out.println("Nome: " + show.getName());
							System.out.println("Descrição: " + show.getDescription());
							System.out.println("Subparts: " + show.getSubParts());
						}
						else {
							System.out.println("Part não encontrada");
						}
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
					default:
						System.out.println("Comando invalido");
				}
				
			}
			clearConsole();
			printCommands();
			command = sc.nextLine();
			
		} catch (Exception e) {
            
            e.printStackTrace();
		}
	}
