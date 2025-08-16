package net.kuko.arcmod.helper.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public interface PatternEntry {
    BlockPos pos();
    BlockState state();
}
