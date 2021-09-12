package lq2007.gradle.mod_src_gen;

import lq2007.gradle.mod_src_gen.generator.ModItems;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModSourceGenerator extends DefaultTask {

    public Path srcPath;
    public Path resPath;
    public ASTParser parser;
    public PluginLogger logger;
    public Map<String, String> optionals;

    @TaskAction
    public void main() {
        GeneratorExtension ext = (GeneratorExtension) getProject().getExtensions().getByName("modSourceGenerator");
        logger = ext.debug ? new PluginLoggerDebug() : new PluginLogger();
        try {
            // parser
            Map<String, String> pOptionals = new HashMap<>();
            pOptionals.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
            pOptionals.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
            pOptionals.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
            optionals = Collections.unmodifiableMap(pOptionals);
            parser = ASTParser.newParser(AST.JLS8);
            Path rootDir = getProject().getRootDir().toPath();
            srcPath = rootDir.resolve("src/main/java");
            resPath = rootDir.resolve("src/main/resources/assets/sinocraft");

            new ModItems().create(this);
        } catch (Exception e) {
            getProject().getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }
}
