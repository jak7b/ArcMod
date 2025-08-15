package net.kuko.arcmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.kuko.arcmod.block.renderer.ExampleBlockEntityRenderer;
import net.kuko.arcmod.block.renderer.IronControllerEntityRenderer;
import net.kuko.arcmod.init.ModBlockEntityInit;

public class ArcModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.register(ModBlockEntityInit.EXAMPLE_BLOCK_BE, ExampleBlockEntityRenderer::new);
		BlockEntityRendererRegistry.register(ModBlockEntityInit.IRON_CONTROLLER_BE, IronControllerEntityRenderer::new);
	//	BlockEntityRendererRegistry.register(ModBlockEntityInit.BIG_IRON_BE, BigIronBlockEntityRenderer::new);
	}
}