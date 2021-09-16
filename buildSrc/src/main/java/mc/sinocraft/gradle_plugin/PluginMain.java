package mc.sinocraft.gradle_plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class PluginMain implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("modSourceGenerator", GeneratorExtension.class);
        project.getTasks().register("genModSourceTask", ModSourceGenerator.class);
        project.getTasks().getByName("compileJava").dependsOn("genModSourceTask");
    }
}
