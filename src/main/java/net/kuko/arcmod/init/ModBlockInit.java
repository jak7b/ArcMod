package net.kuko.arcmod.init;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.kuko.arcmod.block.ExampleBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ModBlockInit implements BlockRegistryContainer {
    public static final Block EXAMPLE_BLOCK = new ExampleBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));



    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Settings());
    }
}
