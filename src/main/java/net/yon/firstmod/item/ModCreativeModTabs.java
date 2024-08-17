package net.yon.firstmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.block.ModBlocks;

public class ModCreativeModTabs {
    //deferred register
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    //object to register (our creative tab)
    public static final RegistryObject<CreativeModeTab> FIRST_MOD_TAB = CREATIVE_MODE_TABS.register("first_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBY.get()))
                    .title(Component.translatable("creativetab.first_mod_tab"))
                    .displayItems((itemDisplayParameters, output) -> {      //adding items to tab:
                        //items
                        output.accept(ModItems.RUBY.get());                 // ruby
                        output.accept(ModItems.RUBY_FRAGMENT.get());        // ruby fragment
                        output.accept(ModItems.RUBY_DETECTOR.get());        // ruby detector
                        output.accept(ModItems.CAT.get());        // cat
                        //foods
                        output.accept(ModItems.COOKED_TROPICAL_FISH.get());        // cooked tropical fish
                        output.accept(ModItems.FAKE_DIAMOND_PICKAXE.get());        // fake diamond pickaxe
                        //fuel
                        output.accept(ModItems.COAL_POWDER.get());              //coal powder
                        //blocks
                        output.accept(ModBlocks.RUBY_BLOCK.get());          // ruby block
                        output.accept(ModBlocks.RUBY_ORE_STONE.get());          // ruby ore block
                        output.accept(ModBlocks.RUBY_ORE_DEEPSLATE.get());          // deepslate ruby ore block
                        output.accept(ModBlocks.BELL_BLOCK.get());          // bell block

                        //non-block blocks
                        output.accept(ModBlocks.RUBY_STAIRS.get());
                        output.accept(ModBlocks.RUBY_SLAB.get());
                        output.accept(ModBlocks.RUBY_BUTTON.get());
                        output.accept(ModBlocks.RUBY_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.RUBY_FENCE.get());
                        output.accept(ModBlocks.RUBY_FENCE_GATE.get());
                        output.accept(ModBlocks.RUBY_WALL.get());
                        output.accept(ModBlocks.RUBY_DOOR.get());
                        output.accept(ModBlocks.RUBY_TRAPDOOR.get());

                        //tools
                        output.accept(ModItems.RUBY_SWORD.get());
                        output.accept(ModItems.RUBY_PICKAXE.get());
                        output.accept(ModItems.RUBY_AXE.get());
                        output.accept(ModItems.RUBY_SHOVEL.get());
                        output.accept(ModItems.RUBY_HOE.get());

                        //armor
                        output.accept(ModItems.RUBY_HELMET.get());
                        output.accept(ModItems.RUBY_CHESTPLATE.get());
                        output.accept(ModItems.RUBY_LEGGINGS.get());
                        output.accept(ModItems.RUBY_BOOTS.get());

                        //amogus
                        output.accept(ModItems.AMOGUS.get());
                    })
                    .build());

    //register method
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
