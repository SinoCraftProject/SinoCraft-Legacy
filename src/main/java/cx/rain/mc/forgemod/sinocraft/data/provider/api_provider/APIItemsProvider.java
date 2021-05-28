package cx.rain.mc.forgemod.sinocraft.data.provider.api_provider;

import cx.rain.mc.forgemod.sinocraft.api.item.IModItems;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class APIItemsProvider {

    private List<String> apiImports, implImports;
    private List<Triple<Class<?>, String, String>> items;

    public void write(DataGenerator generator) throws IOException {
        try {
            loadItems();
        } catch (IllegalAccessException e) {
            throw new IOException("Can't get item info", e);
        }
        Path root = generator.getOutputFolder().getParent().getParent().resolve("main/java/");
        writeApi(root);
        writeImpl(root);
    }

    private void loadItems() throws IllegalAccessException {
        items = new ArrayList<>();
        Set<Class<?>> impSet = new HashSet<>();
        for (Field field : ModItems.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (obj instanceof RegistryObject) {
                    Object o = ((RegistryObject<?>) obj).get();
                    if (o instanceof Item) {
                        Class<?> itemType = o.getClass();
                        String typeName = itemType.getName();
                        while (!typeName.startsWith("net.minecraft") && !typeName.startsWith("cx.rain.mc.forgemod.sinocraft.api")) {
                            itemType = itemType.getSuperclass();
                            typeName = itemType.getName();
                        }
                        String fieldName = field.getName();
                        String[] names = fieldName.toLowerCase(Locale.ROOT).split("_");
                        StringBuilder builder = new StringBuilder("get");
                        for (String name : names) {
                            builder.append(Character.toUpperCase(name.charAt(0)));
                            builder.append(name.substring(1));
                        }
                        items.add(ImmutableTriple.of(itemType, builder.toString(), fieldName));
                        impSet.add(itemType);
                    }
                }
            }
        }
        impSet.add(RegistryObject.class);
        apiImports = impSet.stream().map(Class::getName).sorted().collect(Collectors.toList());
        impSet.add(IModItems.class);
        impSet.add(ModItems.class);
        implImports = impSet.stream().map(Class::getName).sorted().collect(Collectors.toList());
    }

    public void writeApi(Path root) throws IOException {
        Path output = root.resolve("cx/rain/mc/forgemod/sinocraft/api/item/IModItems.java");
        if (!Files.exists(output)) {
            Files.createDirectories(output.getParent());
            Files.createFile(output);
        }
        try(BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("package cx.rain.mc.forgemod.sinocraft.api.item;\n\n");
            for (String name : apiImports) {
                writer.write("import " + name + ";\n");
            }
            writer.write('\n');
            writer.write("public interface IModItems {\n\n");
            for (Triple<Class<?>, String, String> item : items) {
                String typeName = item.getLeft().getSimpleName();
                String funcName = item.getMiddle();
                writer.write("    RegistryObject<? extends " + typeName + "> " + funcName + "();\n");
            }
            writer.write('\n');
            writer.write("}\n");
        }
    }

    public void writeImpl(Path root) throws IOException {
        Path output = root.resolve("cx/rain/mc/forgemod/sinocraft/api_impl/APIModItems.java");
        if (!Files.exists(output)) {
            Files.createDirectories(output.getParent());
            Files.createFile(output);
        }
        try(BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("package cx.rain.mc.forgemod.sinocraft.api_impl;\n\n");
            for (String name : implImports) {
                writer.write("import " + name + ";\n");
            }
            writer.write('\n');
            writer.write("public enum APIModItems implements IModItems {\n\n" +
                         "    INSTANCE;\n\n");
            for (Triple<Class<?>, String, String> item : items) {
                String typeName = item.getLeft().getSimpleName();
                String funcName = item.getMiddle();
                String itemName = item.getRight();
                writer.write("    @Override\n" +
                                 "    public RegistryObject<? extends " + typeName + "> " + funcName + "() {\n" +
                                 "        return ModItems." + itemName + ";\n" +
                                 "    }\n");
            }
            writer.write('\n');
            writer.write("}\n");
        }
    }
}
