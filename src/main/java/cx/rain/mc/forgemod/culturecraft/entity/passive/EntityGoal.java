package cx.rain.mc.forgemod.culturecraft.entity.passive;

import cx.rain.mc.forgemod.culturecraft.entity.Entities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


// by 333konpi333(HINKing)
public class EntityGoal extends AnimalEntity {

    private EatGrassGoal eatGrassGoal;
    private int exampleTimer;

    public EntityGoal(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public EntityGoal createChild(AgeableEntity ageable) {
        return Entities.ENTITY_GOAL.create(this.world);
    }


        @Override
        protected void registerGoals() {
            this.goalSelector.addGoal(0, new SwimGoal(this));
            this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
            this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
            this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.WHEAT), false));
            this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
            this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
            this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
            this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
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
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
        this.setGlowing(true);
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }
}