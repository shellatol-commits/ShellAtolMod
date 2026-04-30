package net.mcreator.modshellatol.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class ShellatolitemSpecialInformationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y + 2, z), Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
	}
}