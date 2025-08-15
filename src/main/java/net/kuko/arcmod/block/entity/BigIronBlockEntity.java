package net.kuko.arcmod.block.entity;

import net.kuko.arcmod.init.ModBlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class BigIronBlockEntity extends BlockEntity {
    public BigIronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityInit.BIG_IRON_BE, pos, state);
    }
}
