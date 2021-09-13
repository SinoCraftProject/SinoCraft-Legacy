package lq2007.gradle.mod_src_gen.generator;

import com.squareup.javapoet.*;
import lq2007.gradle.mod_src_gen.Checker;
import lq2007.gradle.mod_src_gen.ModSourceGenerator;
import lq2007.gradle.mod_src_gen.Utils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static lq2007.gradle.mod_src_gen.ClassTypes.ModBlocks;
import static lq2007.gradle.mod_src_gen.ClassTypes.ModItems;
import static lq2007.gradle.mod_src_gen.ClassTypes.*;

public class ModBlockItems {

    private TypeSpec.Builder items;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger(0);

    private final Object[][] radishes = new Object[][]{
            new Object[]{"white_radish", 3, 4f},
            new Object[]{"summer_radish", 2, 3f},
            new Object[]{"green_radish", 3, 5f},
    };
    private final String[] skips = new String[] {
            "EGGPLANT_PLANT",
            "TEAPOT",
            "WOOD_PULP_BLOCK",
            "SOYBEAN_PLANT",
            "SUMMER_RADISH_PLANT",
            "RICE_PLANT",
            "CHILI_PEPPER_PLANT",
            "GREEN_PEPPER_PLANT",
            "GREEN_RADISH_PLANT",
            "CABBAGE_PLANT",
            "TEA_TABLE",
            "SORGHUM_PLANT",
            "TEACUP",
            "WHITE_RADISH_PLANT",
            "MILLET_PLANT",
    };

    public void create(ModSourceGenerator task) throws IOException {
        items = TypeSpec.classBuilder("ModBlockItems2");
        items.addField(FieldSpec.builder(DeferredRegister(Item), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.ITEMS, $T.MODID)", DeferredRegister, ForgeRegistries, SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin create block items...");
        counter.set(0);
        addBlocks();
        addRadishes();
        task.logger.printLog("    Added " + counter.get() + " items.");
        items.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(IEventBus, "bus")
                .addCode("$T.getLogger().info($S);\n", SinoCraft, "Registering block items.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.block", items.build()).build().writeTo(task.srcPath);
        Checker.checkRegistryObjects(task, ModBlockItems, ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlockItems2"), Collections.emptyMap());
    }

    private void addBlocks() throws IOException {
        task.logger.printDetailedLog(" Add block items");
        String template = "REGISTRY.register($S, () -> new $T($T.%s.get(), new $T.Properties().group($T.BLOCKS)))";
        Utils.forAllRegistryObjects(ModBlocks, task, (type, name, id) -> {
            for (String skip : skips) {
                if (skip.equals(name)) return;
            }
            for (Object[] radish : radishes) {
                if (Objects.equals(radish[0], name.toUpperCase(Locale.ROOT))) return;
            }
            String initializer = String.format(template, name);
            items.addField(FieldSpec.builder(RegistryObject(BlockItem), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(initializer, id, BlockItem, ModBlocks, Item, ModGroups).build());
            task.logger.printDetailedLog("  Item: " + name + "{id = " + id + ", name = " + name + "}");
            counter.addAndGet(1);
        });
    }

    private void addRadishes() {
        String template = "REGISTRY.register($S, () -> new $T($T.%s.get(), new $T.Properties().group($T.AGRICULTURE).food(new $T.Builder().hunger(%s).saturation(%s).build()).setNoRepair()))";
        ClassName BlockNamedItem = ClassName.get("net.minecraft.item", "BlockNamedItem");
        task.logger.printDetailedLog(" Add radishes use " + BlockNamedItem.canonicalName());

        for (Object[] radish : radishes) {
            String id = (String) radish[0];
            String name = id.toUpperCase(Locale.ROOT);
            String plant = name + "_PLANT";
            String hunger = String.valueOf((int) radish[1]);
            String saturation = (float) radish[2] + "f";
            String initializer = String.format(template, plant, hunger, saturation);
            items.addField(FieldSpec.builder(RegistryObject(BlockItem), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(initializer, id, BlockNamedItem, ModBlocks, Item, ModGroups, Food).build());
            task.logger.printDetailedLog("  Radish: " + name + "{id = " + id + ", plant = " + plant + ", hunger = " + hunger + ", saturation = " + saturation + "}");
            counter.addAndGet(1);
        }
    }
}
