package mc.sinocraft.gradle_plugin;

public class Templates {

    private static final String TEMPLATE_FIELD = "REGISTRY.register($S, () -> new $T($T.%s))";
    private static final String TEMPLATE_FIELD2 = "REGISTRY.register($S, () -> new $T($T.%s, $T.%s))";

    public static String registerWithNew(String value) {
        return String.format(TEMPLATE_FIELD, value);
    }

    public static String registerWithNew(String value1, String value2) {
        return String.format(TEMPLATE_FIELD2, value1, value2);
    }
}
