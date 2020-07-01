package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

import static cx.rain.mc.forgemod.culturecraft.item.Items.RADISH_SUMMER;

@ModItem(name = "chirping_wildoose")
public class ItemChirpingwildgoose extends SwordItem {

    public ItemChirpingwildgoose() {
        super(ItemTier.DIAMOND, 9, -2.4F, new Item.Properties()
                .group(Groups.COMBAT)
                .maxStackSize(1));
    }

}
