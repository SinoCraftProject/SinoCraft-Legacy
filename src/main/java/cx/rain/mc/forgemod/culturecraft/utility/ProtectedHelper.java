package cx.rain.mc.forgemod.culturecraft.utility;

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
}
