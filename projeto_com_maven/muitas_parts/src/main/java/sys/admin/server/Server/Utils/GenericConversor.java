package sys.admin.server.Server.Utils;

//Classe para converter de um tipo para outro tipo genérico (não funciona com todos os tipos existentes)
public class GenericConversor {
    public static <I, O> O convert(I input, Class<O> outputClass) throws Exception {
        return input == null ? null : outputClass.getConstructor(String.class).newInstance(input.toString());
    }
}
