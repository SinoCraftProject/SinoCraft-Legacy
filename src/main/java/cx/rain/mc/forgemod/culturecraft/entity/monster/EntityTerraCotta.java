package cx.rain.mc.forgemod.culturecraft.entity.monster;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import cx.rain.mc.forgemod.culturecraft.entity.Entities;

import java.util.EnumSet;

/**
 * Terra-Cotta Warriors
 * 兵马俑，可为友军也可为敌人
 * @author jirufengyu
 */
public class EntityTerraCotta extends MonsterEntity {
    protected RandomWalkingGoal wander;
    public EntityTerraCotta(EntityType<? extends EntityTerraCotta> type, World worldIn) {
        super(type, worldIn);
    }
    public EntityTerraCotta(World worldIn){
        super(Entities.ENTITY_TERRACOTTA, worldIn);

    }
    @Override
    protected void registerGoals(){
        MoveTowardsRestrictionGoal movetowardsrestrictiongoal = new MoveTowardsRestrictionGoal(this, 1.0D);

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));

        this.goalSelector.addGoal(5, movetowardsrestrictiongoal);
        this.wander = new RandomWalkingGoal(this, 1.0D, 80);
        this.wander.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        movetowardsrestrictiongoal.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombiePigmanEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));

    }



}
