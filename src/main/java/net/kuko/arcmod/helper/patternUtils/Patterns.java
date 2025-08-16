package net.kuko.arcmod.helper.patternUtils;

import net.kuko.arcmod.init.ModBlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

public class Patterns {
    private static List<BigIronPatternEntry> patternListIron = null;
    private static List<BigIronPatternEntry> patternListPart = null;

    public static List<BigIronPatternEntry> getPatternListIron() {
        if (patternListIron == null) {
            Block iron_block = Blocks.IRON_BLOCK;

            BigIronPatternEntry[] pattern = {
                    new BigIronPatternEntry(new BlockPos(-1,-1,2), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,-1,2), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,-1,2), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,-1,1), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,-1,1), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,-1,1), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,-1,0), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,-1,0), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,-1,0), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,0,2), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,0,2), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,0,2), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,0,1), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,0,1), Blocks.AIR.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,0,1), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,0,0), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,0,0), ModBlockInit.IRON_CONTROLLER_BLOCK.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,0,0), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,1,2), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,1,2), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,1,2), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,1,1), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,1,1), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,1,1), iron_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,1,0), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,1,0), iron_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,1,0), iron_block.getDefaultState())
            };

            patternListIron = Arrays.asList(pattern);
        }
        return patternListIron;
    }

    public static List<BigIronPatternEntry> getPatternListPart() {
        if (patternListPart == null) {
            Block part_block = ModBlockInit.PART_BLOCK;

            BigIronPatternEntry[] pattern = {
                    new BigIronPatternEntry(new BlockPos(-1,-1,2), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,-1,2), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,-1,2), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,-1,1), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,-1,1), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,-1,1), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,-1,0), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,-1,0), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,-1,0), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,0,2), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,0,2), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,0,2), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,0,1), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,0,1), Blocks.AIR.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,0,1), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,0,0), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,0,0), ModBlockInit.IRON_CONTROLLER_BLOCK.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,0,0), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,1,2), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,1,2), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,1,2), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,1,1), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,1,1), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,1,1), part_block.getDefaultState()),

                    new BigIronPatternEntry(new BlockPos(-1,1,0), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(0,1,0), part_block.getDefaultState()),
                    new BigIronPatternEntry(new BlockPos(1,1,0), part_block.getDefaultState())
            };

            patternListPart = Arrays.asList(pattern);
        }
        return patternListPart;
    }

    // Maintain backward compatibility
    public static final List<BigIronPatternEntry> PATTERN_LIST = getPatternListIron();
    public static final List<BigIronPatternEntry> PATTERN_LIST_PART = getPatternListPart();
}