package cx.rain.mc.forgemod.culturecraft.init;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
/*
public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS,
            CultureCraft.MODID);

    public static final RegistryObject<SoundEvent> AMBIENT = SOUNDS.register("entity.example_entity.ambient",
            () -> new SoundEvent(new ResourceLocation(CultureCraft.MODID, "entity.example_entity.ambient")));

    public static final Lazy<SoundEvent> LAZY_PROMISES_MUSIC = Lazy
            .of(() -> new SoundEvent(new ResourceLocation(CultureCraft.MODID, "disc.promises")));

    public static final RegistryObject<SoundEvent> PROMISES_MUSIC = SOUNDS.register("promises_music",
            LAZY_PROMISES_MUSIC);
}
*/