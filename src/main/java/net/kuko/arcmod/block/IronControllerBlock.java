package net.kuko.arcmod.block;

import net.kuko.arcmod.block.entity.IronControllerBlockEntity;
import net.kuko.arcmod.helper.patternUtils.InvalidPatternException;
import net.kuko.arcmod.helper.patternUtils.PatternScanner;
import net.kuko.arcmod.init.ModBlockInit;
import net.kuko.arcmod.helper.patternUtils.Patterns;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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

                    try {
                        // Check against the IRON pattern, not the convenience method
                        PatternScanner.scanPattern(world, pos, facing, Patterns.PATTERN_LIST,
                                (absolutePos, actualState, entry) -> {
                            if (!actualState.equals(entry.state())) {
                                player.sendMessage(Text.literal("Invalid or missing block at: " + absolutePos)
                                        .formatted(Formatting.RED), true);
                                controllerEntity.setStructured(false);
                                throw new InvalidPatternException();
                            }
                        }, true);

                        controllerEntity.setStructured(validStructure);
                        player.sendMessage(Text.literal("Structure is valid!").formatted(Formatting.GREEN), true);

                        BlockPos behindPos = pos.offset(facing.getOpposite());


                        // Convert iron blocks to part blocks
                        PatternScanner.scanPattern(world, pos, facing, Patterns.PATTERN_LIST, (absolutePos, actualState, entry) -> {
                            // Skip AIR and controller blocks
                            if (!entry.state().equals(Blocks.AIR.getDefaultState()) &&
                                    !entry.state().equals(ModBlockInit.IRON_CONTROLLER_BLOCK.getDefaultState())) {
                                world.setBlockState(absolutePos, ModBlockInit.PART_BLOCK.getDefaultState(), 3);
                            }
                        }, true);

                    //    world.setBlockState(behindPos, Blocks.DIAMOND_BLOCK.getDefaultState());

                        // Make the controller itself invisible
                        world.setBlockState(pos, state.with(INVISIBLE, true), 3);

                        return ActionResult.SUCCESS;

                    } catch (InvalidPatternException e) {
                        // Structure validation failed, message already sent to player
                        return ActionResult.FAIL;
                    }
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

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)   {
        if (!world.isClient) {
            Direction facing = state.get(FACING);
            BlockEntity entity = world.getBlockEntity(pos);

            if (!(entity instanceof IronControllerBlockEntity controllerEntity)) {
                super.onBreak(world, pos, state, player);
                return;
            }

            // Only try to break the structure if it was actually structured
            if (controllerEntity.isStructured()) {
                try {
                    // Verify the pattern is still valid (should be PART blocks now)
                    PatternScanner.scanPattern(world, pos, facing, Patterns.PATTERN_LIST_PART,
                            (absolutePos, actualState, entry) -> {
                                if (!actualState.equals(entry.state())) {
                                    player.sendMessage(Text.literal("Invalid or missing block at: " + absolutePos)
                                            .formatted(Formatting.RED), true);
                                    throw new InvalidPatternException();
                                }
                            }, true);

                    // Remove all pattern blocks
                    PatternScanner.scanPattern(world, pos, facing, Patterns.PATTERN_LIST_PART, (absolutePos, actualState, entry) -> {
                        // Skip AIR blocks and controller block
                        if (!entry.state().equals(Blocks.AIR.getDefaultState()) &&
                                !entry.state().equals(ModBlockInit.IRON_CONTROLLER_BLOCK.getDefaultState())) {
                            world.setBlockState(absolutePos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }, true);

                    // Drop items
                    ItemStack iron_block = new ItemStack(Items.IRON_BLOCK, 25);
                    ItemEntity ironBlockItem = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, iron_block);

                    world.spawnEntity(ironBlockItem);

                } catch (InvalidPatternException e) {
                    // Pattern is invalid, just break the controller without dropping extra items
                    player.sendMessage(Text.literal("Structure was invalid, only dropping controller block").formatted(Formatting.YELLOW), true);
                }
            }
        }

        super.onBreak(world, pos, state, player);
    }
}
