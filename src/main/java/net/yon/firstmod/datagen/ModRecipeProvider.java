package net.yon.firstmod.datagen;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.block.ModBlocks;
import net.yon.firstmod.item.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    //all things that can be
    private static final List<ItemLike> RUBY_SMELTABLES = List.of(
            ModBlocks.RUBY_ORE_STONE.get(),  ModBlocks.RUBY_ORE_DEEPSLATE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        return super.run(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreBlasting(consumer, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.25f, 100, "ruby");
        oreSmelting(consumer, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 0.25f, 100, "ruby");

        //cooked tropical fish
        SimpleCookingRecipeBuilder.smelting( Ingredient.of/*returns ingrediant given item */(Items.TROPICAL_FISH) /*this is the ingredient of the recipe*/,
                        RecipeCategory.MISC /*category*/, ModItems.COOKED_TROPICAL_FISH.get()/*result of recipe*/,
                        0.3f, 200) // amoount of exp , cook time in ticks
                        .unlockedBy(getHasName(Items.TROPICAL_FISH), has(Items.TROPICAL_FISH))
                        .save(consumer);

        //cooked tropical fish
        SimpleCookingRecipeBuilder.smoking( Ingredient.of/*returns ingrediant given item */(Items.TROPICAL_FISH) /*this is the ingredient of the recipe*/,
                        RecipeCategory.MISC /*category*/, ModItems.COOKED_TROPICAL_FISH.get()/*result of recipe*/,
                        0.15f, 150) // amoount of ex , cook time in ticks
                .unlockedBy(getHasName(Items.TROPICAL_FISH), has(Items.TROPICAL_FISH))
                .save(consumer, "cooked_tropical_fish_from_smoking");

        //ruby stuff
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("RRR") // {
                .pattern("RRR") //   pattern of recipe
                .pattern("RRR") // }
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get())) // what item unlocks this recipe in the recipe book
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get() /*result of the crafting recipe*/, 9 /*amount of the result that will be gained*/)
                .requires(ModBlocks.RUBY_BLOCK.get()) // thing the crafting recipe needs
                .unlockedBy(getHasName(ModBlocks.RUBY_BLOCK.get()), has(ModBlocks.RUBY_BLOCK.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("   ") // {
                .pattern(" FF") //   pattern of recipe
                .pattern(" FF") // }
                .define('F', ModItems.RUBY_FRAGMENT.get()) // keys
                .unlockedBy(getHasName(ModItems.RUBY_FRAGMENT.get()), has(ModItems.RUBY_FRAGMENT.get())) // what item unlocks this recipe in the recipe book
                .save(consumer, "ruby_from_ruby_fragments");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY_FRAGMENT.get() /*result of the crafting recipe*/, 4 /*amount of the result that will be gained*/)
                .requires(ModItems.RUBY.get()) // thing the crafting recipe needs
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        //coal powder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COAL /*result of the recipe*/) // Shaped Recipe
                .pattern("PPP") // {
                .pattern("PPP") //   pattern of recipe
                .pattern("PPP") // }
                .define('P', ModItems.COAL_POWDER.get()) // keys
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL)) // what item unlocks this recipe in the recipe book
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COAL_POWDER.get() /*result of the crafting recipe*/, 9 /*amount of the result that will be gained*/)
                .requires(Items.COAL) // thing the crafting recipe needs
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(consumer);

        //bell block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BELL_BLOCK.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("QQQ") // {
                .pattern("QGQ") //   pattern of recipe
                .pattern("QQQ") // }
                .define('Q', Items.QUARTZ) // keys
                .define('G', Items.GOLD_INGOT) // keys
                .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        // ruby detector
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_DETECTOR.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("rrr") // {
                .pattern("IRI") //   pattern of recipe
                .pattern("III") // }
                .define('I', Items.IRON_INGOT) // keys
                .define('R', ModItems.RUBY.get()) // keys
                .define('r', Items.REDSTONE) // keys
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        // fake diamond pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FAKE_DIAMOND_PICKAXE.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("DDD") // {
                .pattern(" S ") //   pattern of recipe
                .pattern(" S ") // }
                .define('S', Items.STICK) // keys
                .define('D', Items.DIRT) // keys
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.DIRT), has(Items.DIRT))
                .save(consumer);

        //ruby sword
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_SWORD.get() /*result of the recipe*/) // Shaped Recipe
                .pattern(" R ") // {
                .pattern(" R ") //   pattern of recipe
                .pattern(" S ") // }
                .define('S', Items.STICK) // keys
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        //ruby pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_PICKAXE.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("RRR") // {
                .pattern(" S ") //   pattern of recipe
                .pattern(" S ") // }
                .define('S', Items.STICK) // keys
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        //ruby axe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_AXE.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("RR ") // {
                .pattern("RS ") //   pattern of recipe
                .pattern(" S ") // }
                .define('S', Items.STICK) // keys
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        //ruby shovel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_SHOVEL.get() /*result of the recipe*/) // Shaped Recipe
                .pattern(" R ") // {
                .pattern(" S ") //   pattern of recipe
                .pattern(" S ") // }
                .define('S', Items.STICK) // keys
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        //ruby hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_HOE.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("RR ") // {
                .pattern(" S ") //   pattern of recipe
                .pattern(" S ") // }
                .define('S', Items.STICK) // keys
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(consumer);

        //ruby hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CAT.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("   ") // {
                .pattern("FWF") //   pattern of recipe
                .pattern("PPP") // }
                .define('W', Items.WITHER_SKELETON_SKULL) // keys
                .define('P', ModItems.COAL_POWDER.get()) // keys
                .define('F', ModItems.COOKED_TROPICAL_FISH.get()) // keys
                .unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
                .unlockedBy(getHasName(ModItems.COOKED_TROPICAL_FISH.get()), has(ModItems.COOKED_TROPICAL_FISH.get()))
                .unlockedBy(getHasName(ModItems.COAL_POWDER.get()), has(ModItems.COAL_POWDER.get()))
                .save(consumer);

        //ruby helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_HELMET.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("RRR") // {
                .pattern("R R") //   pattern of recipe
                .pattern("   ") // }
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get())) // what item unlocks this recipe in the recipe book
                .save(consumer);

        //ruby chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_CHESTPLATE.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("R R") // {
                .pattern("RRR") //   pattern of recipe
                .pattern("RRR") // }
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get())) // what item unlocks this recipe in the recipe book
                .save(consumer);

        //ruby leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_LEGGINGS.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("RRR") // {
                .pattern("R R") //   pattern of recipe
                .pattern("R R") // }
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get())) // what item unlocks this recipe in the recipe book
                .save(consumer);

        //ruby boots
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_BOOTS.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("R R") // {
                .pattern("R R") //   pattern of recipe
                .pattern("   ") // }
                .define('R', ModItems.RUBY.get()) // keys
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get())) // what item unlocks this recipe in the recipe book
                .save(consumer);

        //wizard hat
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIZARD_HAT.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("GGG") // {
                .pattern("GEG") //   pattern of recipe
                .pattern(" I ") // }
                .define('G', Items.TINTED_GLASS) // keys
                .define('E', Items.ENCHANTING_TABLE) // keys
                .define('I', Items.ENCHANTED_BOOK) // keys
                .unlockedBy(getHasName(Items.TINTED_GLASS), has(Items.TINTED_GLASS)) // what item unlocks this recipe in the recipe book
                .unlockedBy(getHasName(Items.ENCHANTING_TABLE), has(Items.ENCHANTING_TABLE)) // what item unlocks this recipe in the recipe book
                .unlockedBy(getHasName(Items.ENCHANTED_BOOK), has(Items.ENCHANTED_BOOK)) // what item unlocks this recipe in the recipe book
                .save(consumer);

        //amogus
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMOGUS.get() /*result of the recipe*/) // Shaped Recipe
                .pattern("   ") // {
                .pattern("ITI") //   pattern of recipe
                .pattern("DRD") // }
                .define('R', ModBlocks.RUBY_DOOR.get()) // keys
                .define('D', Items.DIRT) // keys
                .define('T', Blocks.IRON_TRAPDOOR) // keys
                .define('I', Items.IRON_INGOT) // keys
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.DIRT), has(Items.DIRT))
                .unlockedBy(getHasName(Items.IRON_TRAPDOOR), has(Items.IRON_TRAPDOOR))
                .unlockedBy(getHasName(ModBlocks.RUBY_DOOR.get()), has(ModBlocks.RUBY_DOOR.get()))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                     RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                     List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                     float pExperience, int pCookingTime, String pGroup, String pRecipeName) {

        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, FirstMod.MOD_ID+ ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
