package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.util.math.AxisAlignedBB;

public class MathHelper {

    public static boolean contains(AxisAlignedBB rect, AxisAlignedBB other) {
        return rect.contains(other.minX, other.minY, other.minZ) && rect.contains(other.maxX, other.maxY, other.maxZ);
    }
}
