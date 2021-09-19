package mc.sinocraft.gradle_plugin;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.ClassName;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Utils {

    public static void forClasses(String packageName, Path srcPath, boolean children, ITriConsumer.ClassInfoConsumer consumer) throws IOException {
        Path root = srcPath.resolve(packageName.replace(".", "/"));
        Files.walkFileTree(root, new ClassFileVisitor(packageName, root, children, consumer));
    }

    public static void forAllRegistryObjects(ClassName className, ModSourceGenerator task, ITriConsumer.RegistryObjectInfoConsumer consumer) throws IOException {
        for (FieldDeclaration field : getFields(className, task)) {
            int modifiers = field.getModifiers();
            Type type = field.getType();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && "RegistryObject".equals(getTypeName(type))) {
                VariableDeclarationFragment variable = (VariableDeclarationFragment) field.fragments().get(0);
                String name = variable.getName().getIdentifier();
                MethodInvocation initializer = (MethodInvocation) variable.getInitializer();
                StringLiteral idParameter = (StringLiteral) initializer.arguments().get(0);
                String id = idParameter.getLiteralValue();
                if (type instanceof ParameterizedType) {
                    List<?> arguments = ((ParameterizedType) type).typeArguments();
                    consumer.consume(arguments.isEmpty() ? null : (Type) arguments.get(0), name, id);
                } else {
                    consumer.consume(null, name, id);
                }
            }
        }
    }

    public static List<EnumConstantDeclaration> getEnumValues(ClassName className, ModSourceGenerator task, String... skip) throws IOException {
        Object o = getAST(className, task).types().get(0);
        if (!(o instanceof EnumDeclaration)) {
            throw new IOException(className.canonicalName() + " is not an enum.");
        }
        List<EnumConstantDeclaration> values = new ArrayList<>((List<EnumConstantDeclaration>) ((EnumDeclaration) o).enumConstants());
        if (skip.length > 0) {
            Set<String> skips = new HashSet<>(Arrays.asList(skip));
            values.removeIf(v -> skips.remove(v.getName().getIdentifier()));
        }
        return values;
    }

    public static FieldDeclaration[] getFields(ClassName className, ModSourceGenerator task) throws IOException {
        Object o = getAST(className, task).types().get(0);
        if (!(o instanceof TypeDeclaration)) {
            throw new IOException(className.canonicalName() + " is not a class.");
        }
        return ((TypeDeclaration) o).getFields();
    }

    public static CompilationUnit getAST(ClassName className, ModSourceGenerator task) throws IOException {
        Path file = task.srcPath.resolve(className.canonicalName().replace(".", "/") + ".java");
        return getAST(file, task.parser, task.optionals);
    }

    public static CompilationUnit getAST(Path file, ASTParser parser, Map<String, String> optionals) throws IOException {
        char[] source = new String(Files.readAllBytes(file)).toCharArray();
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setCompilerOptions(optionals);
        parser.setSource(source);
        CompilationUnit ast = (CompilationUnit) parser.createAST(null);
        if (ast.getProblems().length > 0) {
            StringBuilder sb = new StringBuilder("Error in java file ").append(file).append("\n");
            for (IProblem problem : ast.getProblems()) {
                sb.append("  ").append(problem).append("\n");
            }
            throw new IOException(sb.toString());
        }
        return ast;
    }

    public static Toml getToml(String file, ModSourceGenerator task) {
        return new Toml().read(task.resPath.resolve("gen/" + file).toFile());
    }

    public static String toUpperName(String name, int skip) {
        if (name.isEmpty() || name.length() <= skip) return "";
        String itemName = name.substring(skip);
        StringBuilder sb = new StringBuilder(String.valueOf(Character.toUpperCase(itemName.charAt(0))));
        for (int i = 1; i < itemName.length(); i++) {
            char c = itemName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_').append(c);
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }

    public static String toLowerName(String name, int skip) {
        if (name.isEmpty() || name.length() <= skip) return "";
        String itemName = name.substring(skip);
        StringBuilder sb = new StringBuilder(String.valueOf(Character.toLowerCase(itemName.charAt(0))));
        for (int i = 1; i < itemName.length(); i++) {
            char c = itemName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toCamelCase(String name, boolean lowerFirst) {
        StringBuilder sb = new StringBuilder();
        boolean first = false;
        for (String s : name.split("_")) {
            if (s.isEmpty()) continue;
            String s1 = s.toLowerCase(Locale.ROOT);
            if (lowerFirst && !first) {
                first = true;
                sb.append(s1);
            } else {
                sb.append(Character.toUpperCase(s1.charAt(0)));
                sb.append(s1.substring(1));
            }
        }
        return sb.toString();
    }

    public static String getTypeName(Type type) {
        if (type instanceof ParameterizedType) {
            return getTypeName(((ParameterizedType) type).getType());
        } else if (type instanceof ArrayType) {
            return getTypeName(((ArrayType) type).getElementType());
        } else {
            return type.toString();
        }
    }

    public static String getParameterizedName(Type type, int layer, boolean parameterized) {
        if (layer == 0) {
            return parameterized ? type.toString() : getTypeName(type);
        }
        if (!(type instanceof ParameterizedType)) {
            return "";
        }
        List<?> arguments = ((ParameterizedType) type).typeArguments();
        if (arguments.isEmpty()) {
            return "";
        }
        return getParameterizedName((Type) arguments.get(0), layer - 1, parameterized);
    }

    private static int[] resetIndexArray(int size, int defValue, int[] origin) {
        if (origin.length == size) return origin;
        int[] newArray = new int[size];
        Arrays.fill(newArray, defValue);
        int oldSize = Math.min(size, origin.length);
        System.arraycopy(origin, 0, newArray, 0, oldSize);
        return newArray;
    }
}
