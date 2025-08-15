package net.kuko.arcmod.init;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kuko.arcmod.block.entity.ExampleBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntityInit implements AutoRegistryContainer<BlockEntityType<?>> {



    public static final BlockEntityType<ExampleBlockEntity> EXAMPLE_BLOCK_BE =
            FabricBlockEntityTypeBuilder.create(ExampleBlockEntity::new, ModBlockInit.EXAMPLE_BLOCK).build();




    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registries.BLOCK_ENTITY_TYPE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
