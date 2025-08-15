package net.kuko.arcmod;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kuko.arcmod.init.ModBlockEntityInit;
import net.kuko.arcmod.init.ModBlockInit;
import net.kuko.arcmod.init.ModItemInit;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArcMod implements ModInitializer {
	public static final String MOD_ID = "arcmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		FieldRegistrationHandler.register(ModItemInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(ModBlockInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(ModBlockEntityInit.class, MOD_ID, false);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.add(ModBlockInit.EXAMPLE_BLOCK); // Replace with your item
			entries.add(ModBlockInit.IRON_CONTROLLER_BLOCK);
		});
	}
}