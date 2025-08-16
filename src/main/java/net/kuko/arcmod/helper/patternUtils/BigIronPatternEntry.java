package net.kuko.arcmod.helper.patternUtils;

import net.kuko.arcmod.helper.interfaces.PatternEntry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public record BigIronPatternEntry(BlockPos pos, BlockState state) implements PatternEntry {}