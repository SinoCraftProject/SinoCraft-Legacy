package lq2007.gradle.mod_src_gen;

import com.squareup.javapoet.ClassName;
import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Utils {

    public static List<ClassFileInfo> findClasses(String packageName, Path srcPath) throws IOException {
        Path root = srcPath.resolve(packageName.replace(".", "/"));
        ClassFileVisitor visitor = new ClassFileVisitor(packageName, root);
        Files.walkFileTree(root, new ClassFileVisitor(packageName, root));
        return visitor.result;
    }

    public static Stream<EnumConstantDeclaration> getEnumValues(ClassName className, Path srcPath, ASTParser parser, String... skip) throws IOException {
        CompilationUnit ast = getAST(className, srcPath, parser);
        List<?> types = ast.types();
        if (types.isEmpty()) {
            return Stream.empty();
        }
        Object o = types.get(0);
        if (!(o instanceof EnumDeclaration)) {
            return Stream.empty();
        }
        Stream<EnumConstantDeclaration> values = ((List<EnumConstantDeclaration>) ((EnumDeclaration) o).enumConstants()).stream();
        if (skip.length > 0) {
            Set<String> skips = new HashSet<>(Arrays.asList(skip));
            values = values.filter(s -> !skips.remove(s.getName().getIdentifier()));
        }
        return values;
    }

    public static FieldDeclaration[] getFields(ClassName className, Path srcPath, ASTParser parser) throws IOException {
        CompilationUnit ast = getAST(className, srcPath, parser);
        List<?> types = ast.types();
        if (types.isEmpty()) {
            return new FieldDeclaration[0];
        }
        Object o = types.get(0);
        if (!(o instanceof TypeDeclaration)) {
            return new FieldDeclaration[0];
        }
        return ((TypeDeclaration) o).getFields();
    }

    public static Path getClassPath(String className, Path srcPath) {
        return srcPath.resolve(className.replace(".", "/") + ".java");
    }

    public static CompilationUnit getAST(ClassName className, Path srcPath, ASTParser parser) throws IOException {
        Path classPath = getClassPath(className.canonicalName(), srcPath);
        return getAST(classPath, parser);
    }

    public static CompilationUnit getAST(Path file, ASTParser parser) throws IOException {
        char[] source = new String(Files.readAllBytes(file)).toCharArray();
        parser.setSource(source);
        return (CompilationUnit) parser.createAST(null);
    }

    public static String toUpperName(String name, int skip) {
        if (name.isEmpty() || name.length() <= skip) return "";
        String itemName = name.substring(skip + 1);
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
        String itemName = name.substring(skip + 1);
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
}
