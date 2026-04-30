package net.mcreator.modshellatol.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.modshellatol.ModshellatolMod;

public class ShellAtoledMobEffect extends MobEffect {
	public ShellAtoledMobEffect() {
		super(MobEffectCategory.NEUTRAL, -14221057, mobEffectInstance -> ParticleTypes.RAIN);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("block.amethyst_block.place")));
		this.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, ResourceLocation.fromNamespaceAndPath(ModshellatolMod.MODID, "effect.shell_atoled_0"), 999, AttributeModifier.Operation.ADD_VALUE);
		this.addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(ModshellatolMod.MODID, "effect.shell_atoled_1"), 999, AttributeModifier.Operation.ADD_VALUE);
		this.addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(ModshellatolMod.MODID, "effect.shell_atoled_2"), 999, AttributeModifier.Operation.ADD_VALUE);
		this.addAttributeModifier(Attributes.LUCK, ResourceLocation.fromNamespaceAndPath(ModshellatolMod.MODID, "effect.shell_atoled_3"), 999, AttributeModifier.Operation.ADD_VALUE);
		this.addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, ResourceLocation.fromNamespaceAndPath(ModshellatolMod.MODID, "effect.shell_atoled_4"), 999, AttributeModifier.Operation.ADD_VALUE);
	}
}