package net.yon.firstmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties COOKED_TROPICAL_FISH = new FoodProperties.Builder().nutrition(4)
            .saturationMod(1f).effect(()->new MobEffectInstance(MobEffects.CONFUSION, 200), 1f).build();

    public static final FoodProperties FAKE_DIAMOND_PICKAXE = new FoodProperties.Builder().nutrition(4)
            .saturationMod(1f).build();
}
