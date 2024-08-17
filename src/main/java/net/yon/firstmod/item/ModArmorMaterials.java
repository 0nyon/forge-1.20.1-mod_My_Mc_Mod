package net.yon.firstmod.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.yon.firstmod.FirstMod;

import java.util.function.Supplier;

// click on ArmorMaterial and press cntrl+h to see vanilla values
public enum ModArmorMaterials implements ArmorMaterial {
    //constructing a new armor material        |  protAmounts{ helmet - chest - legs - boots}
    //        name       durabilityMult        V                    enchatValue     equipSound
    RUBY("ruby", 35, new int[]{5,7,5,4},  25, SoundEvents.ARMOR_EQUIP_DIAMOND,
            1f, 0f, ()-> Ingredient.of(ModItems.RUBY.get())), //if want to add another material, add a "," after the parentheses and add a new material
    //        thoughness   knockbackResistance        repair Ingredient

    INVIS("invis", 50, new int[]{0,0,0,0}, 0, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN,
            0f, 0f, ()-> Ingredient.of(Items.GLASS));




    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound; // sound that is called when armor is equipped
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmount, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmount;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override // the durability depends on the type of armor (helmet, chestplate exc..)
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return FirstMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
