package net.kuko.arcmod.block;

import net.kuko.arcmod.block.entity.IronControllerBlockEntity;
import net.kuko.arcmod.block.entity.PartBlockEntity;
import net.kuko.arcmod.init.ModBlockInit;
import net.kuko.arcmod.patternEntries.BigIronPattern;
import net.kuko.arcmod.patternEntries.BigIronPatternEntry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class IronControllerBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static Boolean isStructured = false;

    public IronControllerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
        this.getIsStructured();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new IronControllerBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * Rotate relative coordinates based on facing.
     */
    private BlockPos rotatePos(BlockPos relativePos, Direction facing) {
        int x = relativePos.getX();
        int z = relativePos.getZ();
        int y = relativePos.getY();

        return switch (facing) {
            case NORTH -> new BlockPos(x, y, z);
            case SOUTH -> new BlockPos(-x, y, -z);
            case EAST  -> new BlockPos(-z, y, x);
            case WEST  -> new BlockPos(z, y, -x);
            default -> relativePos;
        };
    }


    @Override
    @SuppressWarnings({"java:S3776", "D"}) // SonarLint/SonarQube warning code for high cognitive complexity
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            if (!isStructured) {
                if (player.getStackInHand(hand).getItem() == Items.STICK) {
                    Direction facing = state.get(FACING);
                    boolean validStructure = true;

                    for (BigIronPatternEntry entry : BigIronPattern.PATTERN) {
                        BlockPos rotatedPos = rotatePos(entry.pos(), facing);
                        BlockPos absolutePos = pos.add(rotatedPos);
                        if (absolutePos.equals(pos)) continue;
                        BlockState actualState = world.getBlockState(absolutePos);

                        if (!actualState.equals(entry.state())) {
                            player.sendMessage(Text.literal("Invalid or missing block at: " + absolutePos)
                                    .formatted(Formatting.RED), true);
                            validStructure = false;
                            break; // stop scanning on first invalid block
                        }
                    }
                    isStructured = validStructure;
                    if (validStructure) {

                        player.sendMessage(Text.literal("Structure is valid!").formatted(Formatting.GREEN), true);

                        // Position behind the controller
                        BlockPos behindPos = pos.offset(facing.getOpposite());

                        for (BigIronPatternEntry entry : BigIronPattern.PATTERN) {
                            BlockPos rotatedPos = rotatePos(entry.pos(), facing);
                            BlockPos absolutePos = pos.add(rotatedPos);

                            if (absolutePos.equals(pos)) continue;
                            world.setBlockState(absolutePos, ModBlockInit.PART_BLOCK.getDefaultState(), 3);
                        }


                        // Optional: do something with the block behind
                        world.setBlockState(behindPos, Blocks.DIAMOND_BLOCK.getDefaultState());
                    }
                    return ActionResult.SUCCESS;

                }
            }
        } else {

        }

        return ActionResult.PASS;
    }

    public boolean getIsStructured() {
        return isStructured;
    }
}
