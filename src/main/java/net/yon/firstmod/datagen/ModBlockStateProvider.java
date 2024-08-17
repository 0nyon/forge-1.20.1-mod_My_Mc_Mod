package net.yon.firstmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FirstMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BELL_BLOCK);
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.RUBY_ORE_DEEPSLATE);
        blockWithItem(ModBlocks.RUBY_ORE_STONE);

        //always make sure that: (don't forget accidentally)
        //this |---------- is the same as ---------|
        //     |                                   |
        //     |                                   |
        //     V                                   V
        stairsBlock(((StairBlock) ModBlocks.RUBY_STAIRS.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.RUBY_SLAB.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.RUBY_BUTTON.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.RUBY_PRESSURE_PLATE.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.RUBY_FENCE.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.RUBY_FENCE_GATE.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.RUBY_WALL.get()), blockTexture(ModBlocks.RUBY_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.RUBY_DOOR.get()), modLoc("block/ruby_door_bottom"),
                modLoc("block/ruby_door_top"), "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.RUBY_TRAPDOOR.get()), modLoc("block/ruby_trapdoor"),
                true, "cutout");

    }

    // helper method that makes a block and an item for a block RegistryObject
    public void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
