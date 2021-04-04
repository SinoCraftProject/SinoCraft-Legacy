package cx.rain.mc.forgemod.sinocraft.entity.passive;

import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
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
        super(ModEntities.ENTITY_BUFFALO.get(), worldIn);
    }

    public EntityCarriage(EntityType<? extends EntityBuffalo> type, World worldIn) {
        super(type, worldIn);
    }
    @Override
    protected void updateAITasks() {
        this.exampleTimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }


    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public void livingTick() {
        if (this.world.isRemote) {
            this.exampleTimer = Math.max(0, this.exampleTimer - 1);
        }
        super.livingTick();
    }

    static {
        AttributeModifierMap.MutableAttribute attr = MobEntity.func_233666_p_();
        attr.createMutableAttribute(Attributes.MAX_HEALTH, 26.0D);
        attr.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.28D);
        //TODO: change null
        GlobalEntityTypeAttributes.put(null, attr.create());
    }
}
