package cx.rain.mc.forgemod.culturecraft.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public abstract class RegistryBiome extends Biome {

    protected RegistryBiome(Builder biomeBuilder) {
        super(biomeBuilder);
    }

    // override to register a custom biome weight with Forge's BiomeManager (default is 10)
    public void registerWeights() {}

    @Override
    public int getGrassColor(double x, double z) {
        return getBase().getGrassColor(x, z);
    }

    @Override
    public int getFoliageColor() {
        return getBase().getFoliageColor();
    }

    @Override
    public int getSkyColor() {
        return getBase().getSkyColor();
    }

    public abstract Biome getBase();

    protected static float getTemperatureNoise(BlockPos pos) {
        float value = (float) (TEMPERATURE_NOISE.noiseAt(pos.getX() / 8F, pos.getZ() / 8F, false) * 4F);
        return (value + pos.getY() - 64F) * 0.05F / 30F;
    }
}

