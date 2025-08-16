package net.kuko.arcmod.block.entity;

import net.kuko.arcmod.init.ModBlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class PartBlockEntity extends BlockEntity {

    public PartBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityInit.PART_BE, pos, state);
    }
}