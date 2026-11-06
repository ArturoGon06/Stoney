package dev.stoney.mixin;

import dev.stoney.Stoney;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

@Mixin(LiquidBlock.class)
abstract class LiquidBlockMixin {
    @ModifyExpressionValue(method = "shouldSpreadLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;", ordinal = 0))
    private BlockState stoney$cobblestone(BlockState state, Level level, BlockPos pos, BlockState liquid) {
        return state.is(Blocks.COBBLESTONE) ? Stoney.generatedState(level, pos, state) : state;
    }
}

