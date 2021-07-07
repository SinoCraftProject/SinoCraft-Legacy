package cx.rain.mc.forgemod.sinocraft.data.provider.base;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public abstract class ProviderLanguage extends LanguageProvider {
    public ProviderLanguage(DataGenerator gen, String locale) {
        super(gen, SinoCraft.MODID, locale);
    }

    public void addAdvancement(String id, String title, String description) {
        add("advancement.sinocraft." + id + ".title", title);
        add("advancement.sinocraft." + id + ".description", description);
    }

    public void addItemGroup(ItemGroup group, String name) {
        add(((TranslationTextComponent) group.getGroupName()).getKey(), name);
    }

    public void addTooltip(RegistryObject<? extends Item> item, String... tooltips) {
        String name = item.getId().getPath();
        for (int i = 1; i <= tooltips.length; i++) {
            add("tooltip." + name + "." + i, tooltips[i - 1]);
        }
    }
}
