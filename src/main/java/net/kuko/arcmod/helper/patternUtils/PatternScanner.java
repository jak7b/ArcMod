package net.kuko.arcmod.helper.patternUtils;

import net.kuko.arcmod.helper.interfaces.BlockPatternAction;
import net.kuko.arcmod.helper.interfaces.PatternEntry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class PatternScanner {

    public static <T extends PatternEntry> void scanPattern(World world, BlockPos origin, Direction facing,
                                                            List<T> pattern,
                                                            BlockPatternAction<T> action, boolean ignoreItself) {
        for (T entry : pattern) {
            BlockPos rotatedPos = rotatePos(entry.pos(), facing);
            BlockPos absolutePos = origin.add(rotatedPos);
            if (ignoreItself && absolutePos.equals(origin)) continue;

            BlockState actualState = world.getBlockState(absolutePos);
            action.apply(absolutePos, actualState, entry);
        }
    }

    // Convenience method for BigIronPatternEntry specifically
    public static void scanPattern(World world, BlockPos origin, Direction facing,
                                   BlockPatternAction<BigIronPatternEntry> action, boolean ignoreItself) {
        scanPattern(world, origin, facing, Patterns.PATTERN_LIST, action, ignoreItself);
    }

    // optional: static rotatePos helper
    public static BlockPos rotatePos(BlockPos relativePos, Direction facing) {
        int x = relativePos.getX();
        int y = relativePos.getY();
        int z = relativePos.getZ();

        return switch (facing) {
            case NORTH -> new BlockPos(x, y, z);
            case SOUTH -> new BlockPos(-x, y, -z);
            case EAST  -> new BlockPos(-z, y, x);
            case WEST  -> new BlockPos(z, y, -x);
            default    -> relativePos;
        };
    }
}

