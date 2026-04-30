/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.modshellatol.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.modshellatol.item.ShellatolitemItem;
import net.mcreator.modshellatol.item.Shellatolitem2Item;
import net.mcreator.modshellatol.item.ShellatolItem;
import net.mcreator.modshellatol.item.ShellItem;
import net.mcreator.modshellatol.ModshellatolMod;

import java.util.function.Function;

public class ModshellatolModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(ModshellatolMod.MODID);
	public static final DeferredItem<Item> SHELLATOLITEM;
	public static final DeferredItem<Item> SHELLATOL;
	public static final DeferredItem<Item> SHELLATOLITEM_2;
	public static final DeferredItem<Item> SHELL;
	public static final DeferredItem<Item> SHELLATOLBLOCK;
	static {
		SHELLATOLITEM = register("shellatolitem", ShellatolitemItem::new);
		SHELLATOL = register("shellatol", ShellatolItem::new);
		SHELLATOLITEM_2 = register("shellatolitem_2", Shellatolitem2Item::new);
		SHELL = register("shell", ShellItem::new);
		SHELLATOLBLOCK = block(ModshellatolModBlocks.SHELLATOLBLOCK, new Item.Properties().fireResistant());
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}
}