package sys.admin.server.Server.ServerConfiguration;

public class ServerMissingParamsException extends Exception{

    public ServerMissingParamsException() {
        super("Error: server is missing params; Three params must be provided, in this order: PORT URL NAME");
    }
    
}
