/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.modshellatol.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.modshellatol.client.gui.GuiScreen;
import net.mcreator.modshellatol.client.gui.AeiouaScreen;

@EventBusSubscriber(Dist.CLIENT)
public class ModshellatolModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(ModshellatolModMenus.GUI.get(), GuiScreen::new);
		event.register(ModshellatolModMenus.AEIOUA.get(), AeiouaScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}