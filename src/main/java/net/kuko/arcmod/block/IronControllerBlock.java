package net.kuko.arcmod.block;

import net.kuko.arcmod.block.entity.IronControllerBlockEntity;
import net.kuko.arcmod.init.ModBlockInit;
import net.kuko.arcmod.helper.patternUtils.BigIronPattern;
import net.kuko.arcmod.helper.patternUtils.BigIronPatternEntry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
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

public class IronControllerBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty INVISIBLE = BooleanProperty.of("invisible");

    public IronControllerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(INVISIBLE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, INVISIBLE);
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
        return state.get(INVISIBLE) ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
    }

    private BlockPos rotatePos(BlockPos relativePos, Direction facing) {
        int x = relativePos.getX();
        int y = relativePos.getY();
        int z = relativePos.getZ();

        return switch (facing) {
            case NORTH -> new BlockPos(x, y, z);
            case SOUTH -> new BlockPos(-x, y, -z);
            case EAST -> new BlockPos(-z, y, x);
            case WEST -> new BlockPos(z, y, -x);
            default -> relativePos;
        };
    }

    @Override
    @SuppressWarnings({"java:S3776", "D"})
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity entity = world.getBlockEntity(pos);

        if (!(entity instanceof IronControllerBlockEntity controllerEntity)) return ActionResult.PASS;
        if (!world.isClient) {

            if (!controllerEntity.isStructured()) {
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
                            break;
                        }
                    }

                    controllerEntity.setStructured(validStructure);

                    if (validStructure) {

                        player.sendMessage(Text.literal("Structure is valid!").formatted(Formatting.GREEN), true);

                        BlockPos behindPos = pos.offset(facing.getOpposite());

                        for (BigIronPatternEntry entry : BigIronPattern.PATTERN) {
                            BlockPos rotatedPos = rotatePos(entry.pos(), facing);
                            BlockPos absolutePos = pos.add(rotatedPos);
                            if (absolutePos.equals(pos)) continue;

                            world.setBlockState(absolutePos, ModBlockInit.PART_BLOCK.getDefaultState(), 3);
                        }

                        world.setBlockState(behindPos, Blocks.DIAMOND_BLOCK.getDefaultState());

                        // Make the controller itself invisible
                        world.setBlockState(pos, state.with(INVISIBLE, true), 3);
                    }

                    return ActionResult.SUCCESS;
                }
            } else {
                // This is on Server AND if structured is true.
                return ActionResult.PASS;
            }
        } else if (controllerEntity.isStructured()) {
            // this is on Client AND if structured is true.
            return ActionResult.PASS;
        }

        return ActionResult.PASS;
    }

}
