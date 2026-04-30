/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.modshellatol.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import net.mcreator.modshellatol.ModshellatolMod;

public class ModshellatolModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, ModshellatolMod.MODID);
	public static final DeferredHolder<Potion, Potion> SHELLATOL_DRINK = REGISTRY.register("shellatol_drink", () -> new Potion("shellatol_drink", new MobEffectInstance(ModshellatolModMobEffects.SHELL_ATOLED, 3600, 30, false, true)));
}