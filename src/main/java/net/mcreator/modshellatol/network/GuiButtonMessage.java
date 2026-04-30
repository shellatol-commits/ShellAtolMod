package net.mcreator.modshellatol.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import net.mcreator.modshellatol.procedures.ShellatolitemSpecialInformationProcedure;
import net.mcreator.modshellatol.procedures.AeiouProcedure;
import net.mcreator.modshellatol.ModshellatolMod;

@EventBusSubscriber
public record GuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<GuiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ModshellatolMod.MODID, "gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, GuiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, GuiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new GuiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<GuiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final GuiButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (buttonID == 0) {

			ShellatolitemSpecialInformationProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			AeiouProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ModshellatolMod.addNetworkMessage(GuiButtonMessage.TYPE, GuiButtonMessage.STREAM_CODEC, GuiButtonMessage::handleData);
	}
}