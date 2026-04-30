/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.modshellatol.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.modshellatol.block.ShellatolPortalBlock;
import net.mcreator.modshellatol.block.ShellPortalBlock;
import net.mcreator.modshellatol.block.ShelaltolBlock;
import net.mcreator.modshellatol.ModshellatolMod;

import java.util.function.Function;

public class ModshellatolModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(ModshellatolMod.MODID);
	public static final DeferredBlock<Block> SHELLATOL_PORTAL;
	public static final DeferredBlock<Block> SHELL_PORTAL;
	public static final DeferredBlock<Block> SHELLATOLBLOCK;
	static {
		SHELLATOL_PORTAL = register("shellatol_portal", ShellatolPortalBlock::new);
		SHELL_PORTAL = register("shell_portal", ShellPortalBlock::new);
		SHELLATOLBLOCK = register("shellatolblock", ShelaltolBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}