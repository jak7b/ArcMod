package net.kuko.arcmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.kuko.arcmod.init.ModBlockInit;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // Basic block drops (drops itself)
        addDrop(ModBlockInit.EXAMPLE_BLOCK);
        addDrop(ModBlockInit.IRON_CONTROLLER_BLOCK);

        // Example: Block that drops multiple items
        // addDrop(ModBlockInit.SOME_ORE_BLOCK, oreDrops(ModBlockInit.SOME_ORE_BLOCK, ModItemInit.SOME_GEM));

        // Example: Block with fortune enchantment support
        // addDrop(ModBlockInit.SOME_ORE_BLOCK, copperLikeOreDrops(ModBlockInit.SOME_ORE_BLOCK, ModItemInit.RAW_SOME_MATERIAL));
    }

    // Helper method for ore-like drops with fortune support
    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return dropsWithSilkTouch(drop, applyExplosionDecay(drop,
                ItemEntry.builder(item)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 5.0f)))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }

    // Helper method for blocks that should drop nothing
    public LootTable.Builder noDrop() {
        return LootTable.builder();
    }

    // Helper method for blocks that drop specific amounts
    public LootTable.Builder multipleDrops(Item item, int min, int max) {
        return LootTable.builder()
                .pool(addSurvivesExplosionCondition(Items.AIR,
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .with(ItemEntry.builder(item)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max))))));
    }
}