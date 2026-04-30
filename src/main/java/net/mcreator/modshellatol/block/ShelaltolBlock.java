package net.mcreator.modshellatol.block;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.core.BlockPos;

public class ShelaltolBlock extends Block {
	public ShelaltolBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.SCAFFOLDING).strength(1f, 10f));
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
		return true;
	}
}