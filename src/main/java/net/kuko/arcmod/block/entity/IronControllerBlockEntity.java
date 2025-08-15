package net.kuko.arcmod.block.entity;

import net.kuko.arcmod.block.IronControllerBlock;
import net.kuko.arcmod.init.ModBlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IronControllerBlockEntity extends BlockEntity {
    public static Boolean isBlockStructured = false;

    public IronControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityInit.IRON_CONTROLLER_BE, pos, state);

        // Check if the block at this state is an IronControllerBlock
        if (state.getBlock() instanceof IronControllerBlock controllerBlock) {
            // Set the static field based on the block's field
            isBlockStructured = controllerBlock.getIsStructured();
        }
    }
}
