package net.kuko.arcmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.kuko.arcmod.datagen.ModLootTableProvider;
import net.kuko.arcmod.datagen.ModBlockTagProvider;
//import net.kuko.arcmod.datagen.ModBlockTagProvider;
//import net.kuko.arcmod.datagen.ModModelProvider;
//import net.kuko.arcmod.datagen.ModRecipeProvider;

public class ArcModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// Add loot table provider
		pack.addProvider(ModLootTableProvider::new);

		// Optional: Add other providers
		pack.addProvider(ModBlockTagProvider::new);
//		pack.addProvider(ModBlockTagProvider::new);
//		pack.addProvider(ModModelProvider::new);
//		pack.addProvider(ModRecipeProvider::new);
	}
}