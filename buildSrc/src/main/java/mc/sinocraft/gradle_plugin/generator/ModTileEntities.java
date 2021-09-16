package mc.sinocraft.gradle_plugin.generator;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.*;
import mc.sinocraft.gradle_plugin.Checker;
import mc.sinocraft.gradle_plugin.ClassTypes;
import mc.sinocraft.gradle_plugin.ModSourceGenerator;
import mc.sinocraft.gradle_plugin.Utils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class ModTileEntities {
    private TypeSpec.Builder blocks;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger();

    public void create(ModSourceGenerator task) throws IOException {
        blocks = TypeSpec.classBuilder("ModTileEntities2");
        blocks.addField(FieldSpec.builder(ClassTypes.DeferredRegister(ClassTypes.TileEntityType(ClassTypes.Any)), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.TILE_ENTITIES, $T.MODID)", ClassTypes.DeferredRegister, ClassTypes.ForgeRegistries, ClassTypes.SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin add tile entities...");
        counter.set(0);
        addTypedTileEntities();
        task.logger.printLog("    Added " + counter.get() + " entities.");
        blocks.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassTypes.IEventBus, "bus")
                .addCode("$T.getLogger().info($S);\n", ClassTypes.SinoCraft, "Registering tile entities.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.block.tileentity", blocks.build()).build().writeTo(task.srcPath);
        Checker.checkRegistryObjects(task, ClassTypes.ModTileEntities, ClassTypes.ModTileEntities2, Collections.emptyMap());
    }

    private void addTypedTileEntities() throws IOException {
        task.logger.printDetailedLog(" Add tile entities");
        Toml te = Utils.getToml("te.toml", task);
        String template = "REGISTRY.register($S, () -> $T.Builder.create($T::new, $T.%s.get()).build(null))";
        Utils.forClasses("cx.rain.mc.forgemod.sinocraft.block.tileentity", task.srcPath, false, (packageName, className, filePath) -> {
            if (className.startsWith("TileEntity")) {
                ClassName teType = ClassName.get(packageName, className);

                String registerName = Utils.toLowerName(className, 10);
                String fieldName = Utils.toUpperName(className, 10);
                String blockName = fieldName;
                if (te.containsTable(className)) {
                    Toml table = te.getTable(className);
                    registerName = table.getString("id", registerName);
                    fieldName = table.getString("name", fieldName);
                    blockName = table.getString("block", blockName);
                }
                String initializer = String.format(template, blockName);
                blocks.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.TileEntityType(teType)), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(initializer, registerName, ClassTypes.TileEntityType, teType, ClassTypes.ModBlocks)
                        .build());
                task.logger.printDetailedLog("  TileEntity: " + fieldName + "{id = " + registerName + ", block = " + blockName + ", type = " + teType.canonicalName() + "}");
                counter.addAndGet(1);
            }
        });
    }
}
