package fr.hyper.stalk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.hyper.stalk.mob.Stalker;
import fr.hyper.stalk.mob.Stalker.StalkerFactory;
import fr.hyper.stalk.mob.StalkerModel;
import fr.hyper.stalk.mob.StalkerRenderer;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StalkerMod.MODID)
@Mod.EventBusSubscriber(modid = StalkerMod.MODID, bus = Bus.FORGE)
public class StalkerMod {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	public static final DeferredRegister<EntityType<?>> ENTITIES =
			DeferredRegister.create(ForgeRegistries.ENTITIES, StalkerMod.MODID);

	public static final RegistryObject<EntityType<Stalker>> STALKER = ENTITIES.register("stalker", () -> {
		EntityType<Stalker> result = EntityType.Builder.<Stalker>of(new StalkerFactory(), EntityClassification.MONSTER)
				.sized(.5f, 2)
				.build(new ResourceLocation(StalkerMod.MODID, "stalker").toString());
		return result;
	});

	public static final String MODID = "stalkmod";

	public StalkerMod() {
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().register(new EventHandler());

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		Minecraft mc = event.getMinecraftSupplier().get();
		GameSettings settings = mc.options;
		LOGGER.info("Got game settings {}", settings);
		RenderingRegistry.registerEntityRenderingHandler(STALKER.get(), new IRenderFactory<Stalker>() {
			@Override
			public EntityRenderer<? super Stalker> createRenderFor(EntityRendererManager manager) {
				return new StalkerRenderer(manager, new StalkerModel(), .5f);
			}
		});
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}
}
