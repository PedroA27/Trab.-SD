package sys.admin.server.Server.ServerConfiguration;

import java.util.EnumMap;

import sys.admin.server.Server.Utils.GenericConversor;


public class ServerConfiguration {

    private static ServerParamTypes[] ServerParams = ServerParamTypes.values();
    private static int ParamsQuantity = ServerParams.length;

    public final EnumMap<ServerParamTypes, Object> Params;
    
     public ServerConfiguration(String[] params) throws Exception, ServerMissingParamsException{

        EnumMap<ServerParamTypes, Object> paramsMap = new EnumMap<ServerParamTypes, Object>(ServerParamTypes.class);

        if(params.length < ParamsQuantity)
            throw new ServerMissingParamsException();
      
        for(int i = 0; i < ParamsQuantity; i++)
            paramsMap.put(ServerParams[i], GenericConversor.convert(params[i], ServerParams[i].type()));

        Params = paramsMap;

     }
     public String GetFullUrl(){
        return "rmi://"+GetUrl()+":"+GetPort()+"/"+GetName();
     }
     public String GetName(){
        return (String) Params.get(ServerParamTypes.NAME);
     }
     public int GetPort(){
        return (int) Params.get(ServerParamTypes.PORT);
     }
     public String GetUrl(){
        return (String) Params.get(ServerParamTypes.URL);
     }
}
