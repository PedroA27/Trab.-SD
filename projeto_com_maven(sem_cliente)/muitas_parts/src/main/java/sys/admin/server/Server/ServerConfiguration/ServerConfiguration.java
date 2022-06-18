package sys.admin.server.Server.ServerConfiguration;

import java.util.EnumMap;

import sys.admin.server.Server.Utils.GenericConversor;

//Classe ServerConfiguration, que, a partir do enum ServerParamTypes, inicializa dinamicamente 
//um EnumMap que servirá como um objeto com as propriedades do servidor, dinamicamente,
//convertendo o parâmetro para o tipo desejado
public class ServerConfiguration {
   
    private static ServerParamTypes[] ServerParams = ServerParamTypes.values();
    private static int ParamsQuantity = ServerParams.length;

    public final EnumMap<ServerParamTypes, Object> Params;
    
     public ServerConfiguration(String[] params) throws Exception, ServerMissingParamsException{

        EnumMap<ServerParamTypes, Object> paramsMap = new EnumMap<ServerParamTypes, Object>(ServerParamTypes.class);

        //Caso parâmetros estejam faltando, lance uma exceção do tipo ServerMissingParamsException
        if(params.length < ParamsQuantity)
            throw new ServerMissingParamsException();
      
         //inicializa dinamicamente o EnumMap, com os parâmetros
        for(int i = 0; i < ParamsQuantity; i++)
            paramsMap.put(ServerParams[i], GenericConversor.convert(params[i], ServerParams[i].type()));

        Params = paramsMap;

     }
     //método para retornar a url completa do servidor
     public String GetFullUrl(){
        return "rmi://"+GetUrl()+":"+GetPort()+"/"+GetName();
     }
     //obtém o nome do servidor
     public String GetName(){
        return (String) Params.get(ServerParamTypes.NAME);
     }
      //obtém a porta do servidor
     public int GetPort(){
        return (int) Params.get(ServerParamTypes.PORT);
     }
      //obtém a url do servidor
     public String GetUrl(){
        return (String) Params.get(ServerParamTypes.URL);
     }
}
