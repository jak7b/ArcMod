package net.kuko.arcmod.block.renderer;

import net.kuko.arcmod.block.entity.ExampleBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class ExampleBlockEntityRenderer implements BlockEntityRenderer<ExampleBlockEntity> {

    // Add the required constructor that takes a Context parameter
    public ExampleBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        // Context can be used to access things like block render manager, entity model loader, etc.
        // For now, we'll leave it empty since you're not using any of those
    }

    @Override
    public void render(ExampleBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }

    @Override
    public boolean rendersOutsideBoundingBox(ExampleBlockEntity blockEntity) {
        return BlockEntityRenderer.super.rendersOutsideBoundingBox(blockEntity);
    }
}