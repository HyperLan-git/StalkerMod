package fr.hyper.stalk.mob;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class StalkerModel extends SegmentedModel<Stalker> {
	public ModelRenderer body, leftArm, rightArm, leftLeg, rightLeg, head;

	private List<ModelRenderer> parts = new ArrayList<>();

	public StalkerModel() {
		this.texHeight = this.texWidth = 64;
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4, 0, -2, 8.0F, 12.0F, 4.0F).setPos(0, 0, 0);
		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.addBox(-3, 0, -2, 4.0F, 12.0F, 4.0F).setPos(-5.1F, 0, 0.0F);
		leftArm = new ModelRenderer(this, 40, 16);
		leftArm.addBox(-1, 0, -2, 4.0F, 12.0F, 4.0F).setPos(5.1F, 0, 0.0F);
		leftArm.mirror = true;
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(0, 0, -2, 4.0F, 12.0F, 4.0F).setPos(-4, 12, 0);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(0, 0, -2, 4.0F, 12.0F, 4.0F).setPos(0, 12, 0);
		leftLeg.mirror = true;
		head = new ModelRenderer(this, 32, 0);
		head.addBox(-4, -8, -4.1f, 8.0F, 8.0F, 8.0F).setPos(0, 0, 0);
		head.texOffs(0, 0).addBox(-4, -8, -4, 8.0F, 8.0F, 8.0F).setPos(0.0F, 0, 0.0F);
		body.addChild(rightArm);
		body.addChild(leftArm);
		body.addChild(rightLeg);
		body.addChild(leftLeg);
		body.addChild(head);
		parts.add(body);
	}
	
	@Override
	public void prepareMobModel(Stalker entityIn, float limbSwing, float limbSwingAmount, float ageInTicks) {
		setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, 0, (float) (-Math.asin(entityIn.getLookAngle().y)/Math.PI*180));
	}

	public void setRotationAngles(Stalker entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {
		this.head.yRot = netheadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		this.body.yRot = 0.0F;
		float f = 1.0F;

		if (f < 1.0F) {
			f = 1.0F;
		}

		this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.rightArm.zRot = 0.0F;
		this.leftArm.zRot = 0.0F;
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
		this.rightLeg.yRot = 0.0F;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.zRot = 0.0F;
		this.leftLeg.zRot = 0.0F;

		this.rightArm.yRot = 0.0F;
		this.rightArm.zRot = 0.0F;
		this.leftArm.yRot = 0.0F;

		this.rightArm.yRot = 0.0F;

		this.body.xRot = 0.0F;

		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return parts;
	}

	@Override
	public void setupAnim(Stalker p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_,
			float p_225597_5_, float p_225597_6_) {
		//TODO include anim
	}



}
