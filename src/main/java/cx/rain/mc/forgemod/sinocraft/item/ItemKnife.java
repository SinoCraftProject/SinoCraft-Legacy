package cx.rain.mc.forgemod.sinocraft.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class ItemKnife extends AxeItem {
    static {
        var strippables = new HashMap<>();
        strippables.putAll(STRIPPABLES);

    }

    

    public ItemKnife(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
