package cx.rain.mc.forgemod.sinocraft.utility;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ProtectedHelper {
    public static void addAxeStrippingMap(Block originWood, Block outputWood) {
        try {
            Field field = AxeItem.class.getDeclaredField("BLOCK_STRIPPING_MAP");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            Map<Block, Block> tmpStrippingMap = new HashMap<>();
            tmpStrippingMap.putAll((Map<Block, Block>) field.get(null));
            tmpStrippingMap.put(originWood, outputWood);
            field.set(null, tmpStrippingMap);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public static JsonElement AdvancementToJson(Advancement advancement){
        JsonElement json = null;
        try {
            Field field = AdvancementManager.class.getDeclaredField("Gson");
            field.setAccessible(true);
            json=((Gson)field.get(null)).toJsonTree(advancement);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return json;
    }
}
