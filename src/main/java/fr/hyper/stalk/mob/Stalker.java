package fr.hyper.stalk.mob;

import fr.hyper.stalk.ai.RandomTeleportGoal;
import fr.hyper.stalk.ai.StalkFromAfarGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Stalker extends MonsterEntity {

	public static final class StalkerFactory implements IFactory<Stalker> {
		@Override
		public Stalker create(EntityType<Stalker> type, World theWorld) {
			return new Stalker(type, theWorld);
		}
	}

	public static final AttributeModifierMap MAP = 
			MonsterEntity.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 100)
			.add(Attributes.ATTACK_DAMAGE, 7)
			.build();

	public Stalker(EntityType<Stalker> type, World theWorld) {
		super(type, theWorld);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(100, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(99, new StalkFromAfarGoal(this, PlayerEntity.class, 20, 100, 0.7f));
		this.goalSelector.addGoal(2, new RandomTeleportGoal(this, 0.5f, 50));
		this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
}
