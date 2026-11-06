package dev.stoney.mixin;

import dev.stoney.Stoney;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

@Mixin(LavaFluid.class)
abstract class LavaFluidMixin {
    @ModifyExpressionValue(method = "spreadTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"))
    private BlockState stoney$stone(BlockState state, LevelAccessor level, BlockPos pos, BlockState replaced, Direction direction, FluidState fluid) {
        return Stoney.generatedState(level, pos, state);
    }
}

