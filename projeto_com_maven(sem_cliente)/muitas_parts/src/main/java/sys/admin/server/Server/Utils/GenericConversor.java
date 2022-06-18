package sys.admin.server.Server.Utils;

public class GenericConversor {
    public static <I, O> O convert(I input, Class<O> outputClass) throws Exception {
        return input == null ? null : outputClass.getConstructor(String.class).newInstance(input.toString());
    }
}
