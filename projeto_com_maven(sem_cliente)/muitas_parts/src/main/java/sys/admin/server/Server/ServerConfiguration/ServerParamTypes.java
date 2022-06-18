package sys.admin.server.Server.ServerConfiguration;

public enum ServerParamTypes {  

    PORT(Integer.class),
    URL(String.class),
    NAME(String.class);

    private Class<? extends Object> paramType;

    public Class<? extends Object> type(){return paramType;}

    ServerParamTypes(Class<? extends Object> paramType){
        this.paramType = paramType;
    }
}

