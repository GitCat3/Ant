package me.give_me_moneyz.theant.core.init;

import me.give_me_moneyz.theant.Theant;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Theant.MOD_ID);

    public static final RegistryObject<BlockItem> ANT = ITEMS.register("ant",
            () -> new BlockItem(BlockInit.ANT.get(),
                    new Item.Properties().group(ItemGroup.MISC)));
}