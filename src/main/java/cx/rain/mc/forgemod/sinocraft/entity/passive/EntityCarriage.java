package cx.rain.mc.forgemod.sinocraft.entity.passive;

import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
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


    @Override
    public void livingTick() {
        if (this.world.isRemote) {
            this.exampleTimer = Math.max(0, this.exampleTimer - 1);
        }
        super.livingTick();
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}
