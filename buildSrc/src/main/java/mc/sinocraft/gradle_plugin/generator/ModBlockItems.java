package mc.sinocraft.gradle_plugin.generator;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.*;
import mc.sinocraft.gradle_plugin.ClassTypes;
import mc.sinocraft.gradle_plugin.ModSourceGenerator;
import mc.sinocraft.gradle_plugin.Utils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ModBlockItems {

    private TypeSpec.Builder items;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger(0);

    public void create(ModSourceGenerator task) throws IOException {
        items = TypeSpec.classBuilder("ModBlockItems").addModifiers(Modifier.PUBLIC);
        items.addField(FieldSpec.builder(ClassTypes.DeferredRegister(ClassTypes.Item), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.ITEMS, $T.MODID)", ClassTypes.DeferredRegister, ClassTypes.ForgeRegistries, ClassTypes.SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin add block items...");
        counter.set(0);
        addBlocks();
        task.logger.printLog("    Added " + counter.get() + " items.");
        items.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassTypes.IEventBus, "bus")
                .addCode("$T.getLogger().info($S);\n", ClassTypes.SinoCraft, "Registering block items.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.block", items.build()).build().writeTo(task.srcPath);
    }

    private void addBlocks() throws IOException {
        task.logger.printDetailedLog(" Add block items");
        Toml blockItems = Utils.getToml("block_items.toml", task);
        Utils.forAllRegistryObjects(ClassTypes.ModBlocks, task, (type, name, id) -> {
            StringBuilder itemObj = new StringBuilder("REGISTRY.register($S, () -> new $T($T.");
            boolean hasFood = false;
            ClassName itemType;
            int hunger = 0;
            float saturation = 0f;
            String itemName, registryName;
            if (blockItems.containsTable(name)) {
                Toml table = blockItems.getTable(name);
                if (table.isEmpty()) return;
                itemName = table.getString("name", name);
                registryName = table.getString("id", id);
                itemObj.append(name).append(".get(), new $T.Properties()");
                itemType = table.getBoolean("named", false) ? ClassTypes.BlockNamedItem : ClassTypes.BlockItem;
                itemObj.append(".group($T.").append(table.getString("group", "BLOCKS")).append(")");
                if (table.containsTable("food")) {
                    hasFood = true;
                    itemObj.append(".food(new $T.Builder()");
                    Toml food = table.getTable("food");
                    hunger = food.getLong("hunger", 0L).intValue();
                    saturation = food.getDouble("saturation", 0.0).floatValue();
                    itemObj.append(".hunger(").append(hunger).append(")")
                           .append(".saturation(").append(saturation).append("f).build())");
                }
                if (table.containsTableArray("properties")) {
                    for (String prop : table.<String>getList("properties")) {
                        itemObj.append(".").append(prop);
                    }
                }
                itemObj.append("))");
            } else {
                itemType = ClassTypes.BlockItem;
                itemName = name;
                registryName = id;
                itemObj.append(itemName).append(".get(), new $T.Properties().group($T.BLOCKS)))");
            }
            if (hasFood) {
                items.addField(FieldSpec.builder(ClassTypes.RegistryObject(itemType), itemName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(itemObj.toString(), registryName, itemType, ClassTypes.ModBlocks, ClassTypes.Item, ClassTypes.ModGroups, ClassTypes.Food).build());
            } else {
                items.addField(FieldSpec.builder(ClassTypes.RegistryObject(itemType), itemName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(itemObj.toString(), registryName, itemType, ClassTypes.ModBlocks, ClassTypes.Item, ClassTypes.ModGroups).build());
            }
            String tag = itemType == ClassTypes.BlockItem ? "Item" : "NamedItem";
            task.logger.printDetailedLog("  " + tag + ": " + itemName + "{id = " + registryName + ", block = " + name + "}");
            if (hasFood) {
                task.logger.printDetailedLog("   Food: {hunger = " + hunger + ", saturation = " + saturation + "}");
            }
            counter.addAndGet(1);
        });
    }
}
