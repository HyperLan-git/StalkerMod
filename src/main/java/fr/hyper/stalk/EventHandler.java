package fr.hyper.stalk;

import fr.hyper.stalk.mob.Stalker;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = StalkerMod.MODID, bus = Bus.MOD)
public class EventHandler {
	@ObjectHolder("stalkmod:stalker")
	public static final EntityType<Stalker> STALKER_TYPE = null;

	@SubscribeEvent
	public void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {
		event.put(STALKER_TYPE, Stalker.MAP);
	}
}
