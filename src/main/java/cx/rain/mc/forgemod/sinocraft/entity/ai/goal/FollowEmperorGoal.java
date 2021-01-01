package cx.rain.mc.forgemod.sinocraft.entity.ai.goal;

import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityEmperor;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;

/**
 * @author jirufengyu
 */
public class FollowEmperorGoal extends Goal {
    private final TameableEntity tameable;
    private final EntityEmperor emperor;
    private LivingEntity owner;
    private final IWorldReader world;
    private final double followSpeed;
    private final PathNavigator navigator;
    private int timeToRecalcPath;
    private final float maxDist;
    private final float minDist;
    private float oldWaterCost;
    private final boolean teleportToLeaves;


    public FollowEmperorGoal(TameableEntity tameableIn,EntityEmperor emperor, double speedIn, float minDistIn, float maxDistIn, boolean teleportToLeavesIn) {
        this.tameable = tameableIn;
        this.world = tameableIn.world;
        this.emperor=emperor;
        this.followSpeed = speedIn;
        this.navigator = tameableIn.getNavigator();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.teleportToLeaves = teleportToLeavesIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(tameableIn.getNavigator() instanceof GroundPathNavigator) && !(tameableIn.getNavigator() instanceof FlyingPathNavigator)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }
    @Override
    public boolean shouldExecute() {
        LivingEntity livingentity = this.tameable.getOwner();
        if (livingentity == null) {
            return false;
        } else if (livingentity.isSpectator()) {
            return false;
        }  else if (this.tameable.getDistanceSq(livingentity) < (double)(this.minDist * this.minDist)) {
            return false;
        } else {
            this.owner = livingentity;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        if (this.navigator.noPath()) {
            return false;
        }  else {
            return !(this.tameable.getDistanceSq(this.owner) <= (double)(this.maxDist * this.maxDist));
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.getPathPriority(PathNodeType.WATER);
        this.tameable.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.owner = null;
        this.navigator.clearPath();
        this.tameable.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.tameable.getLookController().setLookPositionWithEntity(this.owner, 10.0F, (float)this.tameable.getVerticalFaceSpeed());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tameable.getLeashed() && !this.tameable.isPassenger()) {
                if (this.tameable.getDistanceSq(this.owner) >= 144.0D) {
                    this.tryToTeleportNearEntity();
                } else {
                    this.navigator.tryMoveToEntityLiving(this.owner, this.followSpeed);
                }

            }
        }
    }

    private void tryToTeleportNearEntity() {
        BlockPos blockpos = new BlockPos(owner.getPosX(), owner.getPosY(), owner.getPosZ());

        for(int i = 0; i < 10; ++i) {
            int j = this.getRandomNumber(-3, 3);
            int k = this.getRandomNumber(-1, 1);
            int l = this.getRandomNumber(-3, 3);
            boolean flag = this.tryToTeleportToLocation(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (flag) {
                return;
            }
        }

    }

    private boolean tryToTeleportToLocation(int xIn, int yIn, int zIn) {
        if (Math.abs((double)xIn - this.owner.getPosX()) < 2.0D && Math.abs((double)zIn - this.owner.getPosZ()) < 2.0D) {
            return false;
        } else if (!this.isTeleportFriendlyBlock(new BlockPos(xIn, yIn, zIn))) {
            return false;
        } else {
            this.tameable.setLocationAndAngles((double)((float)xIn + 0.5F), (double)yIn, (double)((float)zIn + 0.5F), this.tameable.rotationYaw, this.tameable.rotationPitch);
            this.navigator.clearPath();
            return true;
        }
    }

    private boolean isTeleportFriendlyBlock(BlockPos posIn) {
        PathNodeType pathnodetype = WalkNodeProcessor.func_237231_a_(this.world, new BlockPos.Mutable(posIn.getX(), posIn.getY(), posIn.getZ()));
        if (pathnodetype != PathNodeType.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.world.getBlockState(posIn.down());
            if (!this.teleportToLeaves && blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = posIn.subtract(new BlockPos(tameable.getPosX(), tameable.getPosY(), tameable.getPosZ()));
                return this.world.hasNoCollisions(this.tameable, this.tameable.getBoundingBox().offset(blockpos));
            }
        }
    }

    private int getRandomNumber(int minIn, int maxIn) {
        return this.tameable.getRNG().nextInt(maxIn - minIn + 1) + minIn;
    }
}
