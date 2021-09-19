package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import cx.rain.mc.forgemod.sinocraft.client.renderer.model.armor.ClothModel;
import cx.rain.mc.forgemod.sinocraft.item.cloth.ItemCloth;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

public class ItemClothHelmet extends ItemCloth {
    public ItemClothHelmet() {
        super(EquipmentSlotType.HEAD, new Lazy<>(new ClothModel<PlayerEntity>(1.0f)), new Properties().group(ModGroups.MISC));
    }
}
