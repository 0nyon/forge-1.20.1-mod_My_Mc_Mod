package net.yon.firstmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.block.ModBlocks;
import net.yon.firstmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FirstMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    /*
    * this.tag(custom tag/vanilla tag)
    * .add(custom block.get());
    *
    * instead of spamming .add just
    *
    * .add(custom block.get(),
    *       other block.get(),
    *       another block.get());
    * */

    // custom

    //add here blocks to the Ruby detector valuables tag
    this.tag(ModTags.Blocks.RUBY_DETECTOR_VALUABLES)
            .add(ModBlocks.RUBY_ORE_DEEPSLATE.get())
            .add(ModBlocks.RUBY_ORE_STONE.get());

    //Tool Level

    //add here to make it need the tool level of stone
    this.tag(BlockTags.NEEDS_STONE_TOOL);

    //add here to make it need the tool level of iron
    this.tag(BlockTags.NEEDS_IRON_TOOL);

    //add here to make it need the tool level of diamond
    this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.RUBY_ORE_DEEPSLATE.get(),
                    ModBlocks.RUBY_ORE_STONE.get(),
                    ModBlocks.RUBY_BLOCK.get(),
                    ModBlocks.BELL_BLOCK.get(),
                    ModBlocks.RUBY_STAIRS.get(),
                    ModBlocks.RUBY_SLAB.get(),
                    ModBlocks.RUBY_BUTTON.get(),
                    ModBlocks.RUBY_PRESSURE_PLATE.get(),
                    ModBlocks.RUBY_FENCE_GATE.get(),
                    ModBlocks.RUBY_FENCE.get(),
                    ModBlocks.RUBY_WALL.get(),
                    ModBlocks.RUBY_DOOR.get(),
                    ModBlocks.RUBY_TRAPDOOR.get());

    //add here to make it need the tool level of netherite
    this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

    //add here to make it need the tool level of ruby
    this.tag(ModTags.Blocks.NEEDS_RUBY_TOOL)
            .add(ModBlocks.BELL_BLOCK.get());


    //Tools

    //add here to make the correct tool axe
    this.tag(BlockTags.MINEABLE_WITH_AXE);

    //add here to make the correct tool pickaxe
    this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.RUBY_ORE_DEEPSLATE.get(),
                    ModBlocks.RUBY_ORE_STONE.get(),
                    ModBlocks.RUBY_BLOCK.get(),
                    ModBlocks.BELL_BLOCK.get(),
                    ModBlocks.RUBY_STAIRS.get(),
                    ModBlocks.RUBY_SLAB.get(),
                    ModBlocks.RUBY_BUTTON.get(),
                    ModBlocks.RUBY_PRESSURE_PLATE.get(),
                    ModBlocks.RUBY_FENCE_GATE.get(),
                    ModBlocks.RUBY_FENCE.get(),
                    ModBlocks.RUBY_WALL.get(),
                    ModBlocks.RUBY_DOOR.get(),
                    ModBlocks.RUBY_TRAPDOOR.get());

    //add here to make the correct tool shovel
    this.tag(BlockTags.MINEABLE_WITH_SHOVEL);

    //add here to make the correct tool hoe
    this.tag(BlockTags.MINEABLE_WITH_HOE);

    //add fences here (if you don't they won't connect)
    this.tag(BlockTags.FENCES)
            .add(ModBlocks.RUBY_FENCE.get());

    //add fence gates here (if you don't they won't connect)
    this.tag(BlockTags.FENCE_GATES)
            .add(ModBlocks.RUBY_FENCE_GATE.get());

    //add walls here (if you don't they won't connect)
    this.tag(BlockTags.WALLS)
            .add(ModBlocks.RUBY_WALL.get());







    }
}
