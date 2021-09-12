package lq2007.gradle.mod_src_gen;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.ClassName;
import org.eclipse.jdt.core.compiler.IProblem;
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

    public static List<ClassFileInfo> findClasses(String packageName, Path srcPath, boolean children) throws IOException {
        Path root = srcPath.resolve(packageName.replace(".", "/"));
        ClassFileVisitor visitor = new ClassFileVisitor(packageName, root, children);
        Files.walkFileTree(root, visitor);
        return visitor.result;
    }

    public static Stream<EnumConstantDeclaration> getEnumValues(ClassName className, ModSourceGenerator task, String... skip) throws IOException {
        Object o = getAST(className, task).types().get(0);
        if (!(o instanceof EnumDeclaration)) {
            throw new IOException(className.canonicalName() + " is not an enum.");
        }
        Stream<EnumConstantDeclaration> values = ((List<EnumConstantDeclaration>) ((EnumDeclaration) o).enumConstants()).stream();
        if (skip.length > 0) {
            Set<String> skips = new HashSet<>(Arrays.asList(skip));
            values = values.filter(s -> !skips.remove(s.getName().getIdentifier()));
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

    public static Path getClassPath(String className, Path srcPath) {
        return srcPath.resolve(className.replace(".", "/") + ".java");
    }

    public static CompilationUnit getAST(ClassName className, ModSourceGenerator task) throws IOException {
        Path classPath = getClassPath(className.canonicalName(), task.srcPath);
        return getAST(classPath, task);
    }

    public static CompilationUnit getAST(Path file, ModSourceGenerator task) throws IOException {
        char[] source = new String(Files.readAllBytes(file)).toCharArray();
        task.parser.setKind(ASTParser.K_COMPILATION_UNIT);
        task.parser.setResolveBindings(true);
        task.parser.setCompilerOptions(task.optionals);
        task.parser.setSource(source);
        CompilationUnit ast = (CompilationUnit) task.parser.createAST(null);
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
        return new Toml().read(task.resPath.resolve(file).toFile());
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
}
