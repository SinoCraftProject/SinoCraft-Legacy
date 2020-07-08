package cx.rain.mc.forgemod.culturecraft.entity.passive;

import cx.rain.mc.forgemod.culturecraft.entity.Entities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

/** 未完成
 *  等材质与模型 ， 使用方法右键上车，打开gui选择驿站
 *  会自动探路
 */

public class EntityCarriage extends AnimalEntity {

    private EatGrassGoal eatGrassGoal;
    private int exampleTimer;


    public EntityCarriage(World worldIn) {
        super(Entities.ENTITY_BUFFALO, worldIn);
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
    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
    }
    @Override
    public EntityCarriage createChild(AgeableEntity ageable) {
        return null;
    }

}
