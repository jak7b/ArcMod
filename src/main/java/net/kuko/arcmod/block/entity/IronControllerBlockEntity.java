package net.kuko.arcmod.block.entity;

import net.kuko.arcmod.block.IronControllerBlock;
import net.kuko.arcmod.init.ModBlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IronControllerBlockEntity extends BlockEntity {

    private boolean isStructured = false;

    public IronControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityInit.IRON_CONTROLLER_BE, pos, state);
    }

    public boolean isStructured() {
        return isStructured;
    }

    public void setStructured(boolean structured) {
        this.isStructured = structured;
    }

}
