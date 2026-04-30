package net.mcreator.modshellatol.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.modshellatol.world.inventory.GuiMenu;
import net.mcreator.modshellatol.network.GuiButtonMessage;
import net.mcreator.modshellatol.init.ModshellatolModScreens;

public class GuiScreen extends AbstractContainerScreen<GuiMenu> implements ModshellatolModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Checkbox ido;
	private Button button_place_a_diamond_block_above_your;
	private Button button_play_enderdragon_death_sfx;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("modshellatol:textures/screens/gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("modshellatol:textures/screens/costume2_4.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("modshellatol:textures/screens/gnome-mail-send.svg.png");

	public GuiScreen(GuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 1 && elementState instanceof Boolean logicState) {
			if (name.equals("ido")) {
				if (ido.selected() != logicState)
					ido.onPress();
			}
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + -165, this.topPos + 86, 0, 0, 162, 120, 162, 120);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 194, this.topPos + -7, 0, 0, 100, 100, 100, 100);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.modshellatol.gui.label_shellatol_menu"), 47, 10, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.modshellatol.gui.label_inventory"), 55, 70, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_place_a_diamond_block_above_your = Button.builder(Component.translatable("gui.modshellatol.gui.button_place_a_diamond_block_above_your"), e -> {
			int x = GuiScreen.this.x;
			int y = GuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new GuiButtonMessage(0, x, y, z));
				GuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + -16, this.topPos + 45, 210, 20).build();
		this.addRenderableWidget(button_place_a_diamond_block_above_your);
		button_play_enderdragon_death_sfx = Button.builder(Component.translatable("gui.modshellatol.gui.button_play_enderdragon_death_sfx"), e -> {
			int x = GuiScreen.this.x;
			int y = GuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new GuiButtonMessage(1, x, y, z));
				GuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 10, this.topPos + 24, 155, 20).build();
		this.addRenderableWidget(button_play_enderdragon_death_sfx);
		ido = Checkbox.builder(Component.translatable("gui.modshellatol.gui.ido"), this.font).pos(this.leftPos + -51, this.topPos + -25).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "ido", value, false);
		}).build();
		this.addRenderableWidget(ido);
	}
}