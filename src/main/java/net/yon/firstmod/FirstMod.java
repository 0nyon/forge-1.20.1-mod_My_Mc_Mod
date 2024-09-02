package net.yon.firstmod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yon.firstmod.block.ModBlocks;
import net.yon.firstmod.item.ModCreativeModTabs;
import net.yon.firstmod.item.ModItems;
import net.yon.firstmod.loot.ModLootModifiers;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FirstMod.MOD_ID)
public class FirstMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "firstmod"; // ⬅️⬅️⬅️⬅️⬅️⬅️⬅️⬅️
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public FirstMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //register mod creative tabs
        ModCreativeModTabs.register(modEventBus);

        //register mod items
        ModItems.register(modEventBus);

        //register mod blocks
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);


        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
        event.accept(ModItems.RUBY);
        event.accept(ModItems.RUBY_FRAGMENT);
        event.accept(ModBlocks.RUBY_BLOCK);
        event.accept(ModBlocks.RUBY_ORE_STONE);
        event.accept(ModBlocks.RUBY_ORE_DEEPSLATE);
    } else if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
        event.accept(ModItems.COOKED_TROPICAL_FISH);
        event.accept(ModItems.STARFRUIT);
    } else if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS){
        event.accept(ModBlocks.RUBY_PRESSURE_PLATE);
        event.accept(ModBlocks.RUBY_BUTTON);
    } else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
        event.accept(ModItems.RUBY_PICKAXE);
        event.accept(ModItems.RUBY_AXE);
        event.accept(ModItems.RUBY_SHOVEL);
        event.accept(ModItems.RUBY_HOE);
    } else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
        event.accept(ModItems.RUBY_HELMET.get());
        event.accept(ModItems.RUBY_CHESTPLATE.get());
        event.accept(ModItems.RUBY_LEGGINGS.get());
        event.accept(ModItems.RUBY_BOOTS.get());
        event.accept(ModItems.RUBY_SWORD);
        event.accept(ModItems.RUBY_AXE);
    }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
