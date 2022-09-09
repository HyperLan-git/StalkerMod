package fr.hyper.stalk.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookAtWithoutMovingGoal;

public class StalkFromAfarGoal extends LookAtWithoutMovingGoal {
	private float minDistance;

	public StalkFromAfarGoal(MobEntity owner, Class<? extends LivingEntity> looked, float minDistance, float lookDistance,
			float probability) {
		super(owner, looked, lookDistance, probability);
		this.minDistance = minDistance;
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() && lookAt.distanceToSqr(this.mob) > minDistance * minDistance;
	}

	@Override
	public boolean canUse() {
		return super.canUse() && lookAt.distanceToSqr(this.mob) > minDistance * minDistance;
	}
}
