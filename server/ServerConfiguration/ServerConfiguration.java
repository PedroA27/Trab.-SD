package server.ServerConfiguration;

import java.util.Collections;
import java.util.EnumMap;

public class ServerConfiguration {

    private static ServerParamTypes[] ServerParams = ServerParamTypes.values();
    private static int ParamsQuantity = ServerParams.length;

    public final EnumMap<ServerParamTypes, String> Params;
    
     public ServerConfiguration(String[] params) throws ServerMissingParamsException{

        EnumMap<ServerParamTypes, String> paramsMap = new EnumMap<ServerParamTypes, String>(ServerParamTypes.class);

        if(params.length < ParamsQuantity)
            throw new ServerMissingParamsException();
        
        for(int i = 0; i < ParamsQuantity; i++)
            paramsMap.put(ServerParams[i], params[i]);

        Params = (EnumMap<ServerParamTypes, String>) Collections.unmodifiableMap(paramsMap);

     }
}
