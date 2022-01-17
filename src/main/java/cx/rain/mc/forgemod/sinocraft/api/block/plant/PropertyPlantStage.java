package cx.rain.mc.forgemod.sinocraft.api.block.plant;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PropertyPlantStage extends IntegerProperty {
    public static final PropertyPlantStage STAGE_0_7 = PropertyPlantStage.create("stage", 0, 7);
    public static final PropertyPlantStage STAGE_0_3 = PropertyPlantStage.create("stage", 0, 3);

    private int maxStage;

    protected PropertyPlantStage(String name, int min, int max) {
        super(name, min, max);
        maxStage = max;
    }

    public static PropertyPlantStage create(String name, int min, int max) {
        return new PropertyPlantStage(name, min, max);
    }

    public int getMaxStage() {
        return maxStage;
    }
}
