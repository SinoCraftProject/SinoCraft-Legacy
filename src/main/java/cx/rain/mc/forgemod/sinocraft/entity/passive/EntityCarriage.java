package cx.rain.mc.forgemod.sinocraft.entity.passive;

import cx.rain.mc.forgemod.sinocraft.entity.Entities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

// Todo: Waiting for textures and models.
public class EntityCarriage extends AnimalEntity {

    private EatGrassGoal eatGrassGoal;
    private int exampleTimer;


    public EntityCarriage(World worldIn) {
        super(Entities.ENTITY_BUFFALO.get(), worldIn);
    }

    public EntityCarriage(EntityType<? extends EntityBuffalo> type, World worldIn) {
        super(type, worldIn);
    }
    @Override
    protected void updateAITasks() {
        this.exampleTimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }


    @Override
    public void livingTick() {
        if (this.world.isRemote) {
            this.exampleTimer = Math.max(0, this.exampleTimer - 1);
        }
        super.livingTick();
    }

    // Fixme: Static method, see LivingEntity#registerAttributes.
//    @Override
//    protected void registerAttributes() {
//        super.registerAttributes();
//        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(26.0D);
//        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.28D);
//    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}
