package net.kuko.arcmod.helper.interfaces;

import net.kuko.arcmod.helper.patternUtils.BigIronPatternEntry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

@FunctionalInterface
public interface BlockPatternAction<T> {
    void apply(BlockPos absolutePos, BlockState actualState, T entry);
}

