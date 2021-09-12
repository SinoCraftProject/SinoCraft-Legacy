package lq2007.gradle.mod_src_gen;

import lq2007.gradle.mod_src_gen.generator.ModItems;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ModSourceGenerator extends DefaultTask {

    @TaskAction
    public void main() {
        try {
            // parser
            Map<String, String> optionals = new HashMap<>();
            optionals.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
            optionals.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
            optionals.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
            ASTParser parser = ASTParser.newParser(AST.JLS_Latest);
            parser.setKind(ASTParser.K_COMPILATION_UNIT);
            parser.setResolveBindings(true);
            parser.setCompilerOptions(optionals);
            Path rootDir = getProject().getRootDir().toPath();
            Path srcPath = rootDir.resolve("src/main/java");
            Path resPath = rootDir.resolve("src/main/resources/assets/sinocraft");

            new ModItems().create(srcPath, resPath, parser);
        } catch (Exception e) {
            getProject().getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }
}
