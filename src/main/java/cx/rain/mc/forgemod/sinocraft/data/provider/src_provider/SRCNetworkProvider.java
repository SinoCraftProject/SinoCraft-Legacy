package cx.rain.mc.forgemod.sinocraft.data.provider.src_provider;

import cx.rain.mc.forgemod.sinocraft.network.BaseMessage;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.PacketBuffer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SRCNetworkProvider {

    private List<String> packets;
    private final String content0 =
            "package cx.rain.mc.forgemod.sinocraft.network;\n" +
            "\n" +
            "import cx.rain.mc.forgemod.sinocraft.SinoCraft;\n" +
            "import cx.rain.mc.forgemod.sinocraft.network.packet.*;\n" +
            "import net.minecraft.util.ResourceLocation;\n" +
            "import net.minecraftforge.fml.network.NetworkRegistry;\n" +
            "import net.minecraftforge.fml.network.simple.SimpleChannel;\n" +
            "\n" +
            "public class Networks {\n" +
            "    public static SimpleChannel INSTANCE;\n" +
            "    private static int ID = 0;\n" +
            "\n" +
            "    private static int nextID() {\n" +
            "        return ID++;\n" +
            "    }\n" +
            "\n" +
            "    public static void setup() {\n" +
            "        SinoCraft.getLogger().info(\"Registering networks.\");\n" +
            "        INSTANCE = NetworkRegistry.newSimpleChannel(\n" +
            "                new ResourceLocation(SinoCraft.MODID, \"network\"),\n" +
            "                () -> SinoCraft.VERSION,\n" +
            "                SinoCraft.VERSION::equals,\n" +
            "                SinoCraft.VERSION::equals\n" +
            "        );\n" +
            "\n";
    private final String content1 =
            "    }\n" +
            "}\n";
    private final String template = "        INSTANCE.registerMessage(nextID(), %1$s.class, %1$s::serialize, %1$s::new, %1$s::handler);\n";

    public void write(DataGenerator generator) throws IOException {
        Path root = generator.getOutputFolder().getParent().getParent().resolve("main/java/");
        loadNetworks(root);
        writeSource(root);
    }

    private void loadNetworks(Path root) throws IOException {
        packets = new ArrayList<>();
        Path packetFolder = root.resolve("cx/rain/mc/forgemod/sinocraft/network/packet");
        Files.walk(packetFolder)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .filter(name -> name.endsWith(".java"))
                .map(name -> name.substring(0, name.length() - 5))
                .forEach(name -> {
                    String classPath = "cx.rain.mc.forgemod.sinocraft.network.packet." + name;
                    try {
                        Class<?> aClass = SRCNetworkProvider.class.getClassLoader().loadClass(classPath);
                        int modifiers = aClass.getModifiers();
                        if (BaseMessage.class.isAssignableFrom(aClass) && !Modifier.isAbstract(modifiers)) {
                            try {
                                aClass.getConstructor(PacketBuffer.class);
                                packets.add(name);
                            } catch (NoSuchMethodException e) {
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("NetworkProvider: Can't find class " + classPath + ": " + e.getMessage());
                    }
                });
    }

    public void writeSource(Path root) throws IOException {
        Path output = root.resolve("cx/rain/mc/forgemod/sinocraft/network/Networks.java");
        if (!Files.exists(output)) {
            Files.createDirectories(output.getParent());
            Files.createFile(output);
        }
        try(BufferedWriter writer = Files.newBufferedWriter(output)) {
            writer.write(content0);
            for (String packet : packets) {
                writer.write(String.format(template, packet));
            }
            writer.write(content1);
        }
    }
}
