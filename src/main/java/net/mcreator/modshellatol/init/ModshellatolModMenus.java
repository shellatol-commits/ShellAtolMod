/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.modshellatol.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.modshellatol.world.inventory.GuiMenu;
import net.mcreator.modshellatol.world.inventory.AeiouaMenu;
import net.mcreator.modshellatol.network.MenuStateUpdateMessage;
import net.mcreator.modshellatol.ModshellatolMod;

import java.util.Map;

public class ModshellatolModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, ModshellatolMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<GuiMenu>> GUI = REGISTRY.register("gui", () -> IMenuTypeExtension.create(GuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AeiouaMenu>> AEIOUA = REGISTRY.register("aeioua", () -> IMenuTypeExtension.create(AeiouaMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof ModshellatolModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}