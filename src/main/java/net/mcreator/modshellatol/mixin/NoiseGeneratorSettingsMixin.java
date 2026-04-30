package net.mcreator.modshellatol.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import net.mcreator.modshellatol.init.ModshellatolModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements ModshellatolModBiomes.ModshellatolModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> modshellatol_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.modshellatol_dimensionTypeReference != null) {
			retval = ModshellatolModBiomes.adaptSurfaceRule(retval, this.modshellatol_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setmodshellatolDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.modshellatol_dimensionTypeReference = dimensionType;
	}
}