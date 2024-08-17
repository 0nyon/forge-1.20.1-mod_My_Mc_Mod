package net.yon.firstmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.item.ModItems;
import net.yon.firstmod.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {


    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, FirstMod.MOD_ID);
    }

    @Override
    protected void start() {
        // item -> block
        add("amogus_from_grass", new AddItemModifier(new LootItemCondition[] { // adds item to a loot table of a vanilla block
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS/*block that we add the custom item to its loot table*/).build(),
                LootItemRandomChanceCondition.randomChance(0.05f).build()/*chance item we choose drops*/}, ModItems.AMOGUS.get()/*item we add to the loot table*/));

        //

        // item -> entity                                                             | you can get the names from External Libraries/net.minecraft:client:extra:1.20.1/data/minecraft/loot_tables/entities
        add("cat_from_cat", new AddItemModifier(new LootItemCondition[] { //  V
                new LootTableIdCondition.Builder(new ResourceLocation("entities/cat")).build()/*entity we add the chosen item to its loot table*/
        }, ModItems.CAT.get()/*item we add to the loot table*/));

        // item -> chest                                                               you can get the names from External Libraries/net.minecraft:client:extra:1.20.1/data/minecraft/loot_tables/chests
        add("ruby_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {//V
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build()/*type of chest we add the chosen item to its loot table*/
        }, ModItems.RUBY_DETECTOR.get()/*item we add to the loot table*/));
    }
}
