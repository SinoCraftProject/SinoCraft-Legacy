package cx.rain.mc.forgemod.sinocraft.item.cloth;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import cx.rain.mc.forgemod.sinocraft.client.renderer.model.armor.ClothModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class ItemCloth extends ArmorItem {
    private final Lazy<BipedModel<?>> model;

    public ItemCloth(EquipmentSlotType slot, Lazy<BipedModel<?>> model, Item.Properties builderIn) {
        super(SinoCraftArmorMaterial.CLOTHES, slot, builderIn);
        this.model = model;
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return model.<A>cast().get();
    }

}
