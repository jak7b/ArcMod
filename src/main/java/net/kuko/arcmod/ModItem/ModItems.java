package net.kuko.arcmod.ModItem;


import net.kuko.arcmod.ArcMod;
import net.minecraft.item.Item;

public class ModItems {
    public static final class_5321<Item> EXAMPLE_ITEM_KEY;
    public static final Item EXAMPLE_ITEM;

    public static void initialize() {
        ArcMod.LOGGER.info("Registering Mod Items for examplemod");
    }

    static {
        EXAMPLE_ITEM_KEY = class_5321.method_29179(class_7924.field_41197, class_2960.method_60655("examplemod", "example_item"));
        EXAMPLE_ITEM = (Item)class_2378.method_39197(class_7923.field_41178, EXAMPLE_ITEM_KEY, new Item((new class_1793()).method_63686(EXAMPLE_ITEM_KEY)));
    }
}
