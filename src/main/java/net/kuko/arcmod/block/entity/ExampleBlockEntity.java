package net.kuko.arcmod.block.entity;

import net.kuko.arcmod.init.ModBlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ExampleBlockEntity extends BlockEntity {
    public ExampleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityInit.EXAMPLE_BLOCK_BE, pos, state);
    }
}
