package net.kuko.arcmod.helper.patternUtils;

import net.kuko.arcmod.init.ModBlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class BigIronPattern {
    public static final BigIronPatternEntry[] PATTERN = {
                new BigIronPatternEntry(new BlockPos(-1,-1,2), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,-1,2), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,-1,2), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,-1,1), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,-1,1), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,-1,1), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,-1,0), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,-1,0), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,-1,0), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,0,2), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,0,2), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,0,2), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,0,1), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,0,1), Blocks.AIR.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,0,1), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,0,0), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,0,0), ModBlockInit.IRON_CONTROLLER_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,0,0), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,1,2), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,1,2), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,1,2), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,1,1), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,1,1), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,1,1), Blocks.IRON_BLOCK.getDefaultState()),

                new BigIronPatternEntry(new BlockPos(-1,1,0), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(0,1,0), Blocks.IRON_BLOCK.getDefaultState()),
                new BigIronPatternEntry(new BlockPos(1,1,0), Blocks.IRON_BLOCK.getDefaultState())
        };
}
