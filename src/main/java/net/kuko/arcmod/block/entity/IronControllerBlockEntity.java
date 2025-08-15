package net.kuko.arcmod.block.entity;

import net.kuko.arcmod.init.ModBlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class IronControllerBlockEntity extends BlockEntity {
    public IronControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityInit.IRON_CONTROLLER_BE, pos, state);
    }
}
