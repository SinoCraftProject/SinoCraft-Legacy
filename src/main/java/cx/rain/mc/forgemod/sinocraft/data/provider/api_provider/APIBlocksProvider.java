package cx.rain.mc.forgemod.sinocraft.data.provider.api_provider;

import cx.rain.mc.forgemod.sinocraft.api.block.IModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
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

public class APIBlocksProvider {

    private List<String> apiImports, implImports;
    private List<Triple<Class<?>, String, String>> blocks;

    public void write(DataGenerator generator) throws IOException {
        try {
            loadBlocks();
        } catch (IllegalAccessException e) {
            throw new IOException("Can't get block info", e);
        }
        Path root = generator.getOutputFolder().getParent().getParent().resolve("main/java/");
        writeApi(root);
        writeImpl(root);
    }

    private void loadBlocks() throws IllegalAccessException {
        blocks = new ArrayList<>();
        Set<Class<?>> impSet = new HashSet<>();
        for (Field field : ModBlocks.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (obj instanceof RegistryObject) {
                    Object o = ((RegistryObject<?>) obj).get();
                    if (o instanceof Block) {
                        Class<?> blockType = o.getClass();
                        String typeName = blockType.getName();
                        while (!typeName.startsWith("net.minecraft") && !typeName.startsWith("cx.rain.mc.forgemod.sinocraft.api")) {
                            blockType = blockType.getSuperclass();
                            typeName = blockType.getName();
                        }
                        String fieldName = field.getName();
                        String[] names = fieldName.toLowerCase(Locale.ROOT).split("_");
                        StringBuilder builder = new StringBuilder("get");
                        for (String name : names) {
                            builder.append(Character.toUpperCase(name.charAt(0)));
                            builder.append(name.substring(1));
                        }
                        blocks.add(ImmutableTriple.of(blockType, builder.toString(), fieldName));
                        impSet.add(blockType);
                    }
                }
            }
        }
        impSet.add(RegistryObject.class);
        apiImports = impSet.stream().map(Class::getName).sorted().collect(Collectors.toList());
        impSet.add(IModBlocks.class);
        impSet.add(ModBlocks.class);
        implImports = impSet.stream().map(Class::getName).sorted().collect(Collectors.toList());
    }

    public void writeApi(Path root) throws IOException {
        Path output = root.resolve("cx/rain/mc/forgemod/sinocraft/api/block/IModBlocks.java");
        if (!Files.exists(output)) {
            Files.createDirectories(output.getParent());
            Files.createFile(output);
        }
        try(BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write("package cx.rain.mc.forgemod.sinocraft.api.block;\n\n");
            for (String name : apiImports) {
                writer.write("import " + name + ";\n");
            }
            writer.write('\n');
            writer.write("public interface IModBlocks {\n\n");
            for (Triple<Class<?>, String, String> block : blocks) {
                String typeName = block.getLeft().getSimpleName();
                String funcName = block.getMiddle();
                writer.write("    RegistryObject<? extends " + typeName + "> " + funcName + "();\n");
            }
            writer.write('\n');
            writer.write("}\n");
        }
    }

    public void writeImpl(Path root) throws IOException {
        Path output = root.resolve("cx/rain/mc/forgemod/sinocraft/api_impl/APIModBlocks.java");
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
            writer.write("public enum APIModBlocks implements IModBlocks {\n\n" +
                         "    INSTANCE;\n\n");
            for (Triple<Class<?>, String, String> block : blocks) {
                String typeName = block.getLeft().getSimpleName();
                String funcName = block.getMiddle();
                String blockName = block.getRight();
                writer.write("    @Override\n" +
                             "    public RegistryObject<? extends " + typeName + "> " + funcName + "() {\n" +
                             "        return ModBlocks." + blockName + ";\n" +
                             "    }\n");
            }
            writer.write('\n');
            writer.write("}\n");
        }
    }
}
