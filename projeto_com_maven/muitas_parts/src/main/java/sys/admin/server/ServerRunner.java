package sys.admin.server;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sys.admin.server.Server.Implementations.Server;
import sys.admin.server.Server.ServerConfiguration.ServerConfiguration;
import sys.admin.server.Server.ServerConfiguration.ServerMissingParamsException;

//Classe que contém o método principal, responsável por rodar o cliente
public class ServerRunner{
    //Roda o servidor
    public static void Start(Server server) throws RemoteException, MalformedURLException{
        ServerConfiguration config = server.serverConfiguration;
		Registry registry = LocateRegistry.createRegistry(config.GetPort());
		System.out.println(config.GetFullUrl());
        Naming.rebind(config.GetFullUrl(), server);
        System.out.println("Servidor levantou");
	}
    //instancia e roda o servidor
    public static void main(String[] args) throws Exception, RemoteException, MalformedURLException, ServerMissingParamsException{
        Server server = new Server(args);
        Start(server);
    }
}
