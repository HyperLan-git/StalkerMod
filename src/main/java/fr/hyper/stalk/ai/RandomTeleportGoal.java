package fr.hyper.stalk.ai;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RandomTeleportGoal extends Goal {
	private MobEntity mob;

	private float probability;

	private int maxDistance;

	public RandomTeleportGoal(MobEntity mob, float probability, int maxD) {
		this.mob = mob;
		this.probability = probability;
		this.maxDistance = maxD;
	}

	@Override
	public boolean canUse() {
		return this.mob.getRandom().nextFloat() >= this.probability;
	}

	@Override
	public void start() {
		World world = mob.getCommandSenderWorld();
		Random r = this.mob.getRandom(); 
		int d2 = maxDistance*2;
		BlockPos.Mutable blockpos = new BlockPos.Mutable(mob.getX() + r.nextInt(d2) - maxDistance,
				mob.getY() + r.nextInt(d2) - maxDistance, mob.getZ() + r.nextInt(d2) - maxDistance);

		while(blockpos.getY() > 0 && !world.getBlockState(blockpos).getMaterial().blocksMotion()) {
			blockpos.move(Direction.DOWN);
		}

		BlockState blockstate = world.getBlockState(blockpos);
		boolean flag = blockstate.getMaterial().blocksMotion();
		if (flag) {
			//TODO an event
			//net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
			//if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
			//boolean flag2 = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
			boolean flag2 = this.attemptTeleport(blockpos.getX(), blockpos.getY(), blockpos.getZ(), true);
			if (flag2) {
				world.playSound((PlayerEntity)null, mob.xOld, mob.yOld, mob.zOld, SoundEvents.ENDERMAN_TELEPORT, mob.getSoundSource(), 1.0F, 1.0F);
				mob.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
			}
		}
	}


	public boolean attemptTeleport(double p_213373_1_, double p_213373_3_, double p_213373_5_, boolean p_213373_7_) {
		double d0 = mob.getX();
		double d1 = mob.getY();
		double d2 = mob.getZ();
		double d3 = p_213373_3_;
		boolean flag = false;
		BlockPos blockpos = new BlockPos(p_213373_1_, p_213373_3_, p_213373_5_);
		World world = mob.getCommandSenderWorld();
		if (world.isLoaded(blockpos)) {
			boolean flag1 = false;

			while(!flag1 && blockpos.getY() > 0) {
				BlockPos blockpos1 = blockpos.below();
				BlockState blockstate = world.getBlockState(blockpos1);
				if (blockstate.getMaterial().blocksMotion()) {
					flag1 = true;
				} else {
					--d3;
					blockpos = blockpos1;
				}
			}

			if (flag1) {
				mob.setPos(p_213373_1_, d3, p_213373_5_);
				if (world.noCollision(mob.getBoundingBox()) && !world.containsAnyLiquid(mob.getBoundingBox())) {
					flag = true;
				}
			}
		}

		if (!flag) {
			mob.setPos(d0, d1, d2);
			return false;
		} else {

			if (mob instanceof CreatureEntity) {
				((CreatureEntity)mob).getNavigation().recomputePath();
			}

			return true;
		}
	}
}
