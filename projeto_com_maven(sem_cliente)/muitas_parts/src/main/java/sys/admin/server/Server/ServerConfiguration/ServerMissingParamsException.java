package sys.admin.server.Server.ServerConfiguration;

//Exceção criada para ser lançada quando há parâmetros faltando para inicialização do servidor
public class ServerMissingParamsException extends Exception{

    public ServerMissingParamsException() {
        super("Error: server is missing params; Three params must be provided, in this order: PORT URL NAME");
    }
    
}
