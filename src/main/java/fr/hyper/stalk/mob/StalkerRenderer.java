package fr.hyper.stalk.mob;

import fr.hyper.stalk.StalkerMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class StalkerRenderer extends MobRenderer<Stalker, StalkerModel> {

	public static final ModelResourceLocation LAYER_LOCATION = new ModelResourceLocation(
			new ResourceLocation(StalkerMod.MODID, "stalker"), "main");

	public StalkerRenderer(EntityRendererManager manager, StalkerModel model, float shadowRadius) {
		super(manager, model, shadowRadius);
	}

	@Override
	public ResourceLocation getTextureLocation(Stalker entity) {
		return new ResourceLocation(StalkerMod.MODID, "textures/stalker.png");
	}

}
