package net.kuko.arcmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kuko.arcmod.init.ModBlockInit;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // Add block items to relevant tags

        // Example: Add to building blocks creative tab
        // getOrCreateTagBuilder(ItemTags.BUILDING_BLOCKS)
        //         .add(ModBlockInit.EXAMPLE_BLOCK.asItem())
        //         .add(ModBlockInit.IRON_CONTROLLER_BLOCK.asItem());

        // Example: Add to beacon payment items (if it's a valuable block)
        // getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
        //         .add(ModBlockInit.EXAMPLE_BLOCK.asItem());

        // Copy block tags to item tags where needed

        // Custom item tags for mod compatibility
        // getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "storage_blocks")))
        //         .add(ModBlockInit.EXAMPLE_BLOCK.asItem());

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlockInit.IRON_CONTROLLER_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlockInit.IRON_CONTROLLER_BLOCK);
    }
}