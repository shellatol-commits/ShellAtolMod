package net.mcreator.modshellatol.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.modshellatol.world.inventory.AeiouaMenu;
import net.mcreator.modshellatol.network.AeiouaButtonMessage;
import net.mcreator.modshellatol.init.ModshellatolModScreens;

public class AeiouaScreen extends AbstractContainerScreen<AeiouaMenu> implements ModshellatolModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox resource;
	private Button button_open_aka_gui;
	private ImageButton imagebutton_gnomemailsendsvg;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("modshellatol:textures/screens/aeioua.png");

	public AeiouaScreen(AeiouaMenu container, Inventory inventory, Component text) {
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
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("resource"))
				resource.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		resource.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (resource.isFocused())
			return resource.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String resourceValue = resource.getValue();
		super.resize(minecraft, width, height);
		resource.setValue(resourceValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.modshellatol.aeioua.label_shellatol_menu_2"), 46, 9, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		resource = new EditBox(this.font, this.leftPos + 31, this.topPos + 58, 118, 18, Component.translatable("gui.modshellatol.aeioua.resource"));
		resource.setMaxLength(8192);
		resource.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "resource", content, false);
		});
		resource.setHint(Component.translatable("gui.modshellatol.aeioua.resource"));
		this.addWidget(this.resource);
		button_open_aka_gui = Button.builder(Component.translatable("gui.modshellatol.aeioua.button_open_aka_gui"), e -> {
			int x = AeiouaScreen.this.x;
			int y = AeiouaScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new AeiouaButtonMessage(0, x, y, z));
				AeiouaButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 47, this.topPos + 29, 85, 20).build();
		this.addRenderableWidget(button_open_aka_gui);
		imagebutton_gnomemailsendsvg = new ImageButton(this.leftPos + 39, this.topPos + 71, 100, 100,
				new WidgetSprites(ResourceLocation.parse("modshellatol:textures/screens/gnome-mail-send.svg.png"), ResourceLocation.parse("modshellatol:textures/screens/gnome-mail-send.svg.png")), e -> {
					int x = AeiouaScreen.this.x;
					int y = AeiouaScreen.this.y;
					if (true) {
						ClientPacketDistributor.sendToServer(new AeiouaButtonMessage(1, x, y, z));
						AeiouaButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_gnomemailsendsvg);
	}
}