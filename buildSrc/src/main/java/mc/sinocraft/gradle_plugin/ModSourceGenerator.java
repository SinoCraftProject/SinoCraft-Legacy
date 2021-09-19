package mc.sinocraft.gradle_plugin;

import mc.sinocraft.gradle_plugin.generator.ModBlockItems;
import mc.sinocraft.gradle_plugin.generator.ModBlocks;
import mc.sinocraft.gradle_plugin.generator.ModItems;
import mc.sinocraft.gradle_plugin.generator.ModTileEntities;
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
            new ModBlocks().create(this);
            new ModBlockItems().create(this);
            new ModTileEntities().create(this);
        } catch (Exception e) {
            getProject().getLogger().error(e.getMessage());
            e.printStackTrace();
        }
    }
}
