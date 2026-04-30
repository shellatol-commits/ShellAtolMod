/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.modshellatol.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.modshellatol.ModshellatolMod;

@EventBusSubscriber
public class ModshellatolModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModshellatolMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SHELLATOL_ITEMS = REGISTRY.register("shellatol_items",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.modshellatol.shellatol_items")).icon(() -> new ItemStack(ModshellatolModItems.SHELLATOLITEM_2.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ModshellatolModItems.SHELLATOLITEM.get());
				tabData.accept(ModshellatolModBlocks.SHELLATOLBLOCK.get().asItem());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(ModshellatolModItems.SHELLATOL.get());
			tabData.accept(ModshellatolModItems.SHELL.get());
		}
	}
}