package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;

import static cx.rain.mc.forgemod.culturecraft.item.Items.RADISH_SUMMER;
import static cx.rain.mc.forgemod.culturecraft.item.Items.REGISTRY;
import static net.minecraft.item.Items.DIAMOND;

@ModItem(name = "chirping_wildoose")
public class ItemChirpingwildgoose extends SwordItem {

    public ItemChirpingwildgoose() {
        super(ItemTier.DIAMOND, 9, -2.4F, new Item.Properties()
                .group(Groups.COMBAT)
                .setNoRepair()
                .maxStackSize(1));

}}