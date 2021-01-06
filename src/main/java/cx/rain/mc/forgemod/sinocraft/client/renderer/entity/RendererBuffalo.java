package cx.rain.mc.forgemod.sinocraft.client.renderer.entity;

import cx.rain.mc.forgemod.sinocraft.client.renderer.entity.model.ModelBuffalo;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityBuffalo;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererBuffalo extends MobRenderer<EntityBuffalo, ModelBuffalo> {

    private static final ResourceLocation BUFFALO_TEXTURES = new ResourceLocation("sinocraft", "textures/entity/ox.png");

    public RendererBuffalo(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelBuffalo(), 0.9F);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityBuffalo entity) {
        return BUFFALO_TEXTURES;
    }
}