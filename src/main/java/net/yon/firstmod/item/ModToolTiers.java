package net.yon.firstmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.yon.firstmod.FirstMod;
import net.yon.firstmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    // level is 1-4 (stone, iron, diamond, netherite) level means level of tool
    //     (wood) 0  <- 1      2      3         4   (if level bigger than 4 = better than netherite)
    // uses means how much durability the tools will have
    // speed means the speed of mining
    // you can look at the tiers class to see all the vanilla values
    public static final Tier RUBY = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1600, 15f, 3f, 25,
                    ModTags.Blocks.NEEDS_RUBY_TOOL /*here put the tag */ , () -> Ingredient.of(ModItems.RUBY.get()) /*item the tools in this tag use to repair*/),
            new ResourceLocation(FirstMod.MOD_ID, "ruby"), List.of(Tiers.DIAMOND) /*<-- tag of the tools considered lesser than the tools in the tag we created */,
            List.of()) /*<-- tag of the tools considered better than the tools in the tag we created*/;
}
