package net.yon.firstmod.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.block.custom.SoundBlock;
import net.yon.firstmod.item.ModItems;

import java.util.function.Supplier;


public class ModBlocks {

    //register for blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    //creating a block
    //the function we made to create a block, remember that it also makes an item for this block with the same register name
    //                                                             |
    //ruby block                                                   v
    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));

    //----------------------------------------------------------------------------------------------------------------------------------

    //ores
    public static final RegistryObject<Block> RUBY_ORE_STONE = registerBlock("ruby_ore_stone",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(4.0f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3,7)));

    public static final RegistryObject<Block> RUBY_ORE_DEEPSLATE = registerBlock("ruby_ore_deepslate",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).strength(4.0f)
                    .requiresCorrectToolForDrops(),UniformInt.of(3,7)) );

    //----------------------------------------------------------------------------------------------------------------------------------

    //bell block
    public static final RegistryObject<Block> BELL_BLOCK = registerBlock("bell_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(1.2f).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops()));

    //---------------------------------------------------------------------------------------------------------

    //non-block - block (ruby block)
    public static final RegistryObject<Block> RUBY_STAIRS = registerBlock("ruby_stairs",
            () -> new StairBlock(() -> ModBlocks.RUBY_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RUBY_SLAB = registerBlock("ruby_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));

    //----------------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Block> RUBY_BUTTON = registerBlock("ruby_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).requiresCorrectToolForDrops(),
                    BlockSetType.IRON, 200, true));

    public static final RegistryObject<Block> RUBY_PRESSURE_PLATE = registerBlock("ruby_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops(), BlockSetType.IRON));

    //------------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Block> RUBY_FENCE = registerBlock("ruby_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RUBY_FENCE_GATE = registerBlock("ruby_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops(),
                    SoundEvents.ANVIL_FALL /*opening sound*/, SoundEvents.ANVIL_FALL/*closing sound*/));

    public static final RegistryObject<Block> RUBY_WALL = registerBlock("ruby_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops()));

    //-----------------------------------------------------------------------------------------------------------

    public static final RegistryObject<Block> RUBY_DOOR = registerBlock("ruby_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops().noOcclusion(),  /*makes it so you can't see through the world*/
                    BlockSetType.IRON)); /*<---- can go into the BlockSetType class and see the constructor if you want to do some custom stuff, I won't,
                    I just don't want to mess with the registering and all of that*/

    public static final RegistryObject<Block> RUBY_TRAPDOOR = registerBlock("ruby_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).requiresCorrectToolForDrops().noOcclusion(), /*makes it so you can't see through the world*/
                    BlockSetType.IRON)); /*<---- can go into the BlockSetType class and see the constructor if you want to do some custom stuff, I won't,
                    I just don't want to mess with the registering and all of that*/



    //make register given block, and use register block item to link it to an item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //make a block item... when u create a block you need to make an item for it also
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    //register method
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
