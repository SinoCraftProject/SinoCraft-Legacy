package cx.rain.mc.forgemod.sinocraft.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderHook {
    public RenderHook(){
        new ChangeRenderType();
    }
}
