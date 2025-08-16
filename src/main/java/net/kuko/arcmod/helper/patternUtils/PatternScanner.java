package net.kuko.arcmod.helper.patternUtils;

import net.kuko.arcmod.helper.interfaces.BlockPatternAction;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PatternScanner {

    public static void scanPattern(World world, BlockPos origin, Direction facing, BlockPatternAction action, boolean ignoreItself) {
        for (BigIronPatternEntry entry : BigIronPattern.PATTERN) {
            BlockPos rotatedPos = rotatePos(entry.pos(), facing);  // you can make rotatePos static too
            BlockPos absolutePos = origin.add(rotatedPos);
            if (ignoreItself) {  if (absolutePos.equals(origin)) continue; }


            BlockState actualState = world.getBlockState(absolutePos);
            action.apply(absolutePos, actualState, entry);
        }
    }

    // optional: static rotatePos helper
    private static BlockPos rotatePos(BlockPos relativePos, Direction facing) {
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

