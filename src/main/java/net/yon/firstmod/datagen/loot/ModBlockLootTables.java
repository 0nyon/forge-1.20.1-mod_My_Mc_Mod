package net.yon.firstmod.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.yon.firstmod.block.ModBlocks;
import net.yon.firstmod.block.custom.StarfruitCropBlock;
import net.yon.firstmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.BELL_BLOCK.get());
        this.dropSelf(ModBlocks.RUBY_BLOCK.get());

        this.dropSelf(ModBlocks.RUBY_STAIRS.get());
        this.dropSelf(ModBlocks.RUBY_BUTTON.get());
        this.dropSelf(ModBlocks.RUBY_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.RUBY_TRAPDOOR.get());
        this.dropSelf(ModBlocks.RUBY_FENCE.get());
        this.dropSelf(ModBlocks.RUBY_FENCE_GATE.get());
        this.dropSelf(ModBlocks.RUBY_WALL.get());

        /*                         |----------------------------------|
        *                          V                                  |
        * this.add(ModBlocks.(THIS_BLOCK)).get(),                     V
        *       block -> createCustomBlockDrops(ModBlocks.(HAS_TO_MATCH_THIS_BLOCK)).get(), ModItems.RUBY_FRAGMENT.get(), 2f, 3f));
        *
        * this.add(ModBlocks.(Block we add the loot table to)).get(),                     V
         *       block -> createCustomBlockDrops(ModBlocks.(Block we add the loot table to)).get(),(The item the block drops)), 2f, 3f));
        *
        *
        * */

        this.add(ModBlocks.RUBY_ORE_STONE.get(),
                block -> createCustomBlockDrops(ModBlocks.RUBY_ORE_STONE.get(), ModItems.RUBY_FRAGMENT.get(), 2f, 3f));
        this.add(ModBlocks.RUBY_ORE_DEEPSLATE.get(),
                block -> createCustomBlockDrops(ModBlocks.RUBY_ORE_DEEPSLATE.get(), ModItems.RUBY_FRAGMENT.get(), 2f, 3f));

        this.add(ModBlocks.RUBY_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.RUBY_SLAB.get()));
        this.add(ModBlocks.RUBY_DOOR.get(),
                block -> createDoorTable(ModBlocks.RUBY_DOOR.get()));

        //if the starfruits age is 4 drop a starfruit,
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STARFRUIT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StarfruitCropBlock.AGE, 4));
        //drop seeds
        this.add(ModBlocks.STARFRUIT_CROP.get(), createCropDrops(ModBlocks.STARFRUIT_CROP.get(), ModItems.STARFRUIT.get(),
                ModItems.STARFRUIT_SEEDS.get(), lootitemcondition$builder));

    }

    //If forge doesn't launch... its probably this method 😬😬😬
    //I made it, a method that creates a loot table, takes the block the loot table is for, the Item the block should Drop, and the min->max amount of drops
    protected LootTable.Builder createCustomBlockDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        return createSilkTouchDispatchTable(pBlock, (LootPoolEntryContainer.Builder)
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override /*high level overview: this method says that all of the blocks that were registered with the BLOCKS register and don't have the noLootTable property
    need to have a custom loot table*/
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get) :: iterator;
    }
}
